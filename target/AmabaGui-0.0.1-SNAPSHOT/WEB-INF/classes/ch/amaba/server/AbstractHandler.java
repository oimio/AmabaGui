package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.amaba.dao.AmabaDao;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.server.constants.IConstants;

import com.google.inject.Provider;

public class AbstractHandler {
	protected final Provider<HttpServletRequest> requestProvider;
	protected final ServletContext servletContext;
	private ClassPathXmlApplicationContext beanFactory;
	static AmabaDao dao;

	public AbstractHandler(final ServletContext servletContext, final Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;

		if (beanFactory == null) {
			beanFactory = new ClassPathXmlApplicationContext("application-context.xml");
			AbstractHandler.dao = (AmabaDao) beanFactory.getBean("amabaDao");
		}
	}

	/**
	 * Retourne l'instance UserCriteria sauvegardée dans la session après
	 * authentification.
	 * 
	 * @return UserCriteria
	 * */
	public UserCriteria getUserCriteriaSession() {
		UserCriteria userCriteria = null;
		// la session ne doit pas être recréée à la volée
		final HttpSession session = requestProvider.get().getSession(false);
		if (session != null) {
			userCriteria = (UserCriteria) session.getAttribute(IConstants.SESSION_USER_CRITERIA);
		}
		return userCriteria;
	}

	/**
	 * Sauvegarde dans la session l'instance UserCriteria.
	 * 
	 * @param userCriteria
	 *          - le userCriteria à sauver dans la session
	 * */
	public void saveUserCriteriaSession(UserCriteria userCriteria) {
		// La session démarre.
		final HttpSession session = requestProvider.get().getSession(true);
		session.setAttribute(IConstants.SESSION_USER_CRITERIA, userCriteria);
	}

	public Provider<HttpServletRequest> getRequestProvider() {
		return requestProvider;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
}
