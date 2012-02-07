package ch.amaba.server.upload;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.amaba.client.view.upload.UploadProgressService;
import ch.amaba.dao.AmabaDao;
import ch.amaba.dao.model.UserPhotoEntity;
import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.HttpSessionRequiredException;
import ch.amaba.server.utils.SessionUtils;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.upload.Event;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public final class UploadProgressServlet extends RemoteServiceServlet implements UploadProgressService {

	private final Logger logger = LoggerFactory.getLogger(AmabaDao.class);
	private static final long serialVersionUID = 1L;
	private static final int EVENT_WAIT = 30 * 1000;
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadProgressServlet.class);

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void initialise() {
		// getThreadLocalRequest().getSession(false);
	}

	@Override
	public Set<PhotoDTO> readFiles(final int page, final int pageSize) {
		Set<PhotoDTO> listFiles = null;
		final Set<PhotoDTO> photoExistsOnDisk = new HashSet<PhotoDTO>();
		// File[] listFiles;
		try {
			listFiles = SpringFactory.get().getDao().loadPhotosByUser(SessionUtils.get().getUserSessionId(getThreadLocalRequest()));
			for (final PhotoDTO photoDTO : listFiles) {
				if (new File(SessionUtils.get().getUserPhotoDirectory(getThreadLocalRequest()), photoDTO.getFileName()).exists()) {
					// existe en DB mais pas dans la base
					photoExistsOnDisk.add(photoDTO);
				} else {
					logger.warn("Le fichier " + SessionUtils.get().getUserPhotoDirectory(getThreadLocalRequest()) + File.separator + photoDTO.getFileName()
					    + " existe en base mais pas sur le disk.");
				}
			}
		} catch (final HttpSessionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photoExistsOnDisk;
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
		int count = 0;
		try {
			final Set<PhotoDTO> loadPhotosByUser = SpringFactory.get().getDao().loadPhotosByUser(SessionUtils.get().getUserSessionId(getThreadLocalRequest()));
			count = loadPhotosByUser.size();
			// count =
			// readFiles(SessionUtils.get().getUserPhotoDirectory(getThreadLocalRequest())).length;
		} catch (final HttpSessionRequiredException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * @param idPhoto
	 *          - id à supprimée
	 * @return idPhoto, id de la photo supprimée
	 */
	@Override
	public Long supprimerPhoto(Long idPhoto) throws EntityNotFoundException {
		final UserPhotoEntity userPhotoEntity = new UserPhotoEntity();
		userPhotoEntity.setEntityId(idPhoto);
		try {
			SpringFactory.get().getDao().supprimer(userPhotoEntity);
		} catch (final EntityNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return idPhoto;
	}

	@Override
	public Set<PhotoDTO> flagPhotoAsPrincipale(Long idPhoto) throws HttpSessionRequiredException {
		SpringFactory.get().getDao().flagPhotoPrincipale(SessionUtils.get().getUserSessionId(getThreadLocalRequest()), idPhoto);
		return readFiles(-1, -1);
	}

}