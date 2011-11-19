package ch.amaba.server;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ch.amaba.dao.AmabaDao;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.server.shared.AjouterFavorisAction;
import ch.amaba.server.shared.AjouterFavorisResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class AjouterFavorisHandler implements ActionHandler<AjouterFavorisAction, AjouterFavorisResult> {

	private final Provider<HttpServletRequest> requestProvider;
	private final ServletContext servletContext;
	ClassPathXmlApplicationContext beanFactory;
	AmabaDao dao;
	DriverManagerDataSource basicDataSource;

	@Inject
	AjouterFavorisHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	public AjouterFavorisResult execute(AjouterFavorisAction action, ExecutionContext context) throws ActionException {
		Set<CantonDTO> cantons = null;
		try {
			beanFactory = new ClassPathXmlApplicationContext("application-context.xml");
			dao = (AmabaDao) beanFactory.getBean("amabaDao");
			basicDataSource = (DriverManagerDataSource) beanFactory.getBean("basicDataSource");

			cantons = dao.loadCantons();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new AjouterFavorisResult(cantons);
	}

	public Class<AjouterFavorisAction> getActionType() {
		return AjouterFavorisAction.class;
	}

	public void undo(AjouterFavorisAction arg0, AjouterFavorisResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
