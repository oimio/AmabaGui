package ch.amaba.server.upload;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.amaba.client.view.upload.UploadProgressService;
import ch.amaba.shared.upload.Event;
import ch.amaba.shared.upload.FileDto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public final class UploadProgressServlet extends RemoteServiceServlet implements UploadProgressService {

	private static final long serialVersionUID = 1L;
	private static final int EVENT_WAIT = 30 * 1000;
	private static final String PROPERTIES_FILE = "WEB-INF/classes/uploadprogress.properties";
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadProgressServlet.class);
	private String uploadDirectory;

	@Override
	public void init() throws ServletException {
		final Properties properties = new Properties();
		InputStream fi = null;
		try {
			final File file = new File(UploadProgressServlet.PROPERTIES_FILE);
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
	public void initialise() {
		getThreadLocalRequest().getSession(true);
	}

	@Override
	public List<FileDto> readFiles(final int page, final int pageSize) {

		final File[] listFiles = readFiles(uploadDirectory);
		sortFiles(listFiles);

		final int firstFile = pageSize * (page - 1);
		int lastFile = firstFile + pageSize;

		final int fileCount = listFiles.length;
		if (fileCount < lastFile) {
			lastFile = fileCount;
		}

		if (firstFile < fileCount) {
			final List<FileDto> files = new ArrayList<FileDto>();

			for (int i = firstFile; i < lastFile; i++) {

				final File file = listFiles[i];
				final FileDto fileDto = new FileDto();
				fileDto.setFilename(file.getName());
				fileDto.setDateUploaded(new Date(file.lastModified()));
				files.add(fileDto);
			}
			return files;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public List<Event> getEvents() {

		final HttpSession session = getThreadLocalRequest().getSession();
		final UploadProgress uploadProgress = UploadProgress.getUploadProgress(session);

		List<Event> events = null;
		if (null != uploadProgress) {
			if (uploadProgress.isEmpty()) {
				try {
					synchronized (uploadProgress) {
						UploadProgressServlet.LOGGER.debug("waiting...");
						uploadProgress.wait(UploadProgressServlet.EVENT_WAIT);
					}
				} catch (final InterruptedException ie) {
					UploadProgressServlet.LOGGER.debug("interrupted...");
				}
			}

			synchronized (uploadProgress) {
				events = uploadProgress.getEvents();
				uploadProgress.clear();
			}
		}

		return events;
	}

	@Override
	public int countFiles() {
		return readFiles(uploadDirectory).length;
	}

	private File[] readFiles(final String directory) {
		final File uploadDirectory = new File(directory);
		return uploadDirectory.listFiles(new FileFilter() {

			@Override
			public boolean accept(final File file) {
				return null == file ? false : file.isFile();
			}
		});
	}

	private void sortFiles(final File[] listFiles) {
		Arrays.sort(listFiles, new Comparator<File>() {

			@Override
			public int compare(final File f1, final File f2) {
				return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			}
		});
	}
}
