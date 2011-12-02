package ch.amaba.server.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UploadServlet extends HttpServlet {

	private static final String PROPERTIES_FILE = "WEB-INF/classes/uploadprogress.properties";
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadServlet.class);
	private static final String FILE_SEPERATOR = System.getProperty("file.separator");
	private String uploadDirectory;

	@Override
	public void init() throws ServletException {
		final Properties properties = new Properties();
		InputStream fi = null;
		try {
			final File file = new File(UploadServlet.PROPERTIES_FILE);
			fi = new FileInputStream(file);
			properties.load(fi);
		} catch (final IOException ioe) {
			throw new ServletException(ioe);
		} finally {
			IOUtils.closeQuietly(fi);
		}

		uploadDirectory = properties.getProperty("upload.directory", "target");
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			uploadFile(request);
		} catch (final FileUploadException fue) {
			throw new ServletException(fue);
		}
	}

	private void uploadFile(final HttpServletRequest request) throws FileUploadException, IOException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new FileUploadException("error multipart request not found");
		}

		final FileItemFactory fileItemFactory = new DiskFileItemFactory();
		final ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

		final FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(request);

		final HttpSession session = request.getSession();
		final UploadProgress uploadProgress = UploadProgress.getUploadProgress(session);

		while (fileItemIterator.hasNext()) {
			final FileItemStream fileItemStream = fileItemIterator.next();

			final String filePath = fileItemStream.getName();
			final String fileName = filePath.substring(filePath.lastIndexOf(UploadServlet.FILE_SEPERATOR) + 1);

			final UploadProgressListener uploadProgressListener = new UploadProgressListener(fileName, uploadProgress);

			final UploadProgressInputStream inputStream = new UploadProgressInputStream(fileItemStream.openStream(), request.getContentLength());
			inputStream.addListener(uploadProgressListener);

			final File file = new File(uploadDirectory, fileName);

			Streams.copy(inputStream, new FileOutputStream(file), true);

			UploadServlet.LOGGER.info(String.format("uploaded file %s", file.getAbsolutePath()));
		}
	}
}
