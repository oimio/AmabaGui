package ch.amaba.server.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

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
import org.springframework.web.HttpSessionRequiredException;

import ch.amaba.server.utils.SessionUtils;

public final class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(DownloadServlet.class);

	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		downloadFile(request, response);
	}

	private void downloadFile(final HttpServletRequest request, final HttpServletResponse response) throws IOException, HttpSessionRequiredException {
		final String userPhotoDirectory = SessionUtils.get().getUserPhotoDirectory(request);
		String fileName = request.getParameter("file");
		fileName = URLDecoder.decode(fileName);

		final boolean invalidFileName = (null == fileName) || fileName.isEmpty() || fileName.contains("\\") || fileName.contains("/") || fileName.contains("..");

		if (invalidFileName) {
			throw new IOException(String.format("error downloading file %s", fileName));
		}

		ServletOutputStream outputStream = null;
		final ServletContext context = getServletConfig().getServletContext();
		final String mimetype = context.getMimeType(fileName);

		final File file = new File(userPhotoDirectory, fileName);
		if (file.exists()) {
			response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
			FileInputStream fi = null;
			try {
				outputStream = response.getOutputStream();
				fi = new FileInputStream(file);
				Streams.copy(fi, outputStream, true);
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(outputStream);
				IOUtils.closeQuietly(fi);
			}
			DownloadServlet.LOGGER.info(String.format("downloaded file %s", file.getAbsolutePath()));
		}
	}
}