package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.shared.DevenirMembreAction;
import ch.amaba.shared.DevenirMembreResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class DevenirMembreHandler extends AbstractHandler implements ActionHandler<DevenirMembreAction, DevenirMembreResult> {

	@Inject
	DevenirMembreHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	
	public DevenirMembreResult execute(DevenirMembreAction action, ExecutionContext context) throws ActionException {
		final UserCriteria userCriteria = action.getUserCriteria();
		userCriteria.setIp(getRequestProvider().get().getRemoteAddr());
		try {
			AbstractHandler.dao.devenirMembre(userCriteria);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new DevenirMembreResult();
	}

	
	public Class<DevenirMembreAction> getActionType() {
		return DevenirMembreAction.class;
	}

	
	public void undo(DevenirMembreAction arg0, DevenirMembreResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
