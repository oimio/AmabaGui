package ch.amaba.server;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ch.amaba.dao.AmabaDao;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.server.shared.DeconnectionAction;
import ch.amaba.server.shared.DeconnectionResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class DeconnectionHandler implements ActionHandler<DeconnectionAction, DeconnectionResult> {

	private final Provider<HttpServletRequest> requestProvider;
	private final ServletContext servletContext;
	ClassPathXmlApplicationContext beanFactory;
	AmabaDao dao;
	DriverManagerDataSource basicDataSource;

	@Inject
	DeconnectionHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	
	public DeconnectionResult execute(DeconnectionAction action, ExecutionContext context) throws ActionException {
		Set<CantonDTO> cantons = null;
		try {
			beanFactory = new ClassPathXmlApplicationContext("application-context.xml");
			dao = (AmabaDao) beanFactory.getBean("amabaDao");
			basicDataSource = (DriverManagerDataSource) beanFactory.getBean("basicDataSource");

			cantons = dao.loadCantons();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new DeconnectionResult(cantons);
	}

	
	public Class<DeconnectionAction> getActionType() {
		return DeconnectionAction.class;
	}

	
	public void undo(DeconnectionAction arg0, DeconnectionResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
