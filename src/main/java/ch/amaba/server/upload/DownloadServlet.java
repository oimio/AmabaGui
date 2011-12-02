package ch.amaba.server.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DownloadServlet extends HttpServlet {

	private static final String PROPERTIES_FILE = "WEB-INF/classes/uploadprogress.properties";
	private static final Logger LOGGER = LoggerFactory.getLogger(DownloadServlet.class);
	private String uploadDirectory;

	@Override
	public void init() throws ServletException {
		final Properties properties = new Properties();
		InputStream fi = null;
		try {
			final File file = new File(DownloadServlet.PROPERTIES_FILE);
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
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		downloadFile(request, response);
	}

	private void downloadFile(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("file");
		fileName = URLDecoder.decode(fileName);

		final boolean invalidFileName = (null == fileName) || fileName.isEmpty() || fileName.contains("\\") || fileName.contains("/") || fileName.contains("..");

		if (invalidFileName) {
			throw new IOException(String.format("error downloading file %s", fileName));
		}

		final ServletOutputStream outputStream = response.getOutputStream();
		final ServletContext context = getServletConfig().getServletContext();
		final String mimetype = context.getMimeType(fileName);

		final File file = new File(uploadDirectory, fileName);
		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));

		Streams.copy(new FileInputStream(file), outputStream, true);

		DownloadServlet.LOGGER.info(String.format("downloaded file %s", file.getAbsolutePath()));
	}
}