package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.EnvoyerMessagesAction;
import ch.amaba.shared.EnvoyerMessagesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class EnvoyerMessagesHandler extends AbstractHandler implements ActionHandler<EnvoyerMessagesAction, EnvoyerMessagesResult> {

	@Inject
	EnvoyerMessagesHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public EnvoyerMessagesResult execute(EnvoyerMessagesAction action, ExecutionContext context) throws ActionException {
		try {
			final UserMessageEntity envoyerMessage = SpringFactory
			    .get()
			    .getMessageDAO()
			    .envoyerMessage(action.getIdDestinataire(), getUserCriteriaSession().getIdUser(), "Votre profile intéresse",
			        "Votre profile a suscité toute l'attention de ...");

		} catch (final Exception e) {
			// TODO: handle exception
		}
		return new EnvoyerMessagesResult();
	}

	@Override
	public Class<EnvoyerMessagesAction> getActionType() {
		return EnvoyerMessagesAction.class;
	}

	@Override
	public void undo(EnvoyerMessagesAction arg0, EnvoyerMessagesResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
