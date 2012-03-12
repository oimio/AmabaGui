package ch.amaba.server.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.exception.HttpSessionRequiredException;
import ch.amaba.server.constants.IConstants;

public class SessionUtils {

	private static SessionUtils INSTANCE;

	public static SessionUtils get() {
		if (SessionUtils.INSTANCE == null) {
			SessionUtils.INSTANCE = new SessionUtils();
		}
		return SessionUtils.INSTANCE;
	}

	/** Retourne un Singleton. */
	private SessionUtils() {
	}

	/**
	 * Retourne l'ID du user courant;
	 * */
	public Long getUserSessionId(final HttpServletRequest request) throws HttpSessionRequiredException {
		Long id = null;
		final UserCriteria userCriteriaSession = getUserCriteriaSession(request);
		if (userCriteriaSession != null) {
			id = userCriteriaSession.getIdUser();
		}
		return id;
	}

	/**
	 * Retourne l'instance UserCriteria sauvegardée dans la session après
	 * authentification.
	 * 
	 * @return UserCriteria
	 * */
	public UserCriteria getUserCriteriaSession(final HttpServletRequest request) throws HttpSessionRequiredException {
		return (UserCriteria) getSession(request).getAttribute(IConstants.SESSION_USER_CRITERIA);
	}

	/**
	 * Retourne la session courante, throw une exception
	 * HttpSessionRequiredException si elle est nulle.
	 * 
	 * @param request
	 * @return session
	 * */
	private HttpSession getSession(final HttpServletRequest request) throws HttpSessionRequiredException {
		final HttpSession session = request.getSession(false);
		if (session == null) {
			throw new HttpSessionRequiredException();
		}
		return session;
	}

	public String getUserPhotoDirectory(final HttpServletRequest request) throws HttpSessionRequiredException {
		return IConstants.PHOTO_DIRECTORY + getUserCriteriaSession(request).getIdUser();
	}

	public String getUserPhotoDirectoryHorsSession(Long idUser) throws HttpSessionRequiredException {
		return IConstants.PHOTO_DIRECTORY + idUser;
	}
}
