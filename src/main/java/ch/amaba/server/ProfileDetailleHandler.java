package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.ProfileDetailleAction;
import ch.amaba.shared.ProfileDetailleResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class ProfileDetailleHandler extends AbstractHandler implements ActionHandler<ProfileDetailleAction, ProfileDetailleResult> {

	@Inject
	ProfileDetailleHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public ProfileDetailleResult execute(ProfileDetailleAction action, ExecutionContext context) throws ActionException {

		final UserCriteria profileDetaille = SpringFactory.get().getDao().getProfileDetaille(action.getIdUser());

		return new ProfileDetailleResult(profileDetaille);
	}

	@Override
	public Class<ProfileDetailleAction> getActionType() {
		return ProfileDetailleAction.class;
	}

	@Override
	public void undo(ProfileDetailleAction arg0, ProfileDetailleResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
