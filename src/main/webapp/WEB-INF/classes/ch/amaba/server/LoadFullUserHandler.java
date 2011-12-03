package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.shared.LoadFullUserAction;
import ch.amaba.shared.LoadFullUserResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class LoadFullUserHandler extends AbstractHandler implements ActionHandler<LoadFullUserAction, LoadFullUserResult> {

	@Inject
	LoadFullUserHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	
	public LoadFullUserResult execute(LoadFullUserAction action, ExecutionContext context) throws ActionException {
		final UserCriteria userCriteriaSession = getUserCriteriaSession();
		final UserCriteria loadFullUserData = AbstractHandler.dao.loadFullUserData(userCriteriaSession);
		return new LoadFullUserResult(loadFullUserData);
	}

	
	public Class<LoadFullUserAction> getActionType() {
		return LoadFullUserAction.class;
	}

	
	public void undo(LoadFullUserAction arg0, LoadFullUserResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
