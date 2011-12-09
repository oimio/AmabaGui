package ch.amaba.server.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
import org.springframework.web.HttpSessionRequiredException;

import ch.amaba.server.utils.ImageUtils;
import ch.amaba.server.utils.SessionUtils;
import ch.amaba.server.utils.SpringFactory;

public final class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadServlet.class);
	private static final String FILE_SEPERATOR = System.getProperty("file.separator");

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			uploadFile(request);
		} catch (final FileUploadException fue) {
			throw new ServletException(fue);
		}
	}

	private void uploadFile(final HttpServletRequest request) throws FileUploadException, IOException, HttpSessionRequiredException {

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

			System.out.println(filePath);
			System.out.println(fileName);

			final UploadProgressListener uploadProgressListener = new UploadProgressListener(fileName, uploadProgress);

			final UploadProgressInputStream inputStream = new UploadProgressInputStream(fileItemStream.openStream(), request.getContentLength());
			inputStream.addListener(uploadProgressListener);
			final String userPhotoDirectory = SessionUtils.get().getUserPhotoDirectory(request);

			final File file = new File(userPhotoDirectory, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fo = null;
			try {
				fo = new FileOutputStream(file);
				Streams.copy(inputStream, fo, true);
				// Resizer l'image
				ImageUtils.resize(file, file, 480, 1f);
				// Creer le thumbnail - un thumnail commence par "_"
				ImageUtils.resize(file, new File(userPhotoDirectory, "_" + fileName), 60, 1f);

				SpringFactory.get().getDao().savePhotos(SessionUtils.get().getUserSessionId(request), new String[] { fileName });

				UploadServlet.LOGGER.info(String.format("uploaded file %s", file.getAbsolutePath()));
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(fo);
			}

		}
	}
}
