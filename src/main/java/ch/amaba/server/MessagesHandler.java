package ch.amaba.server;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.MessageDTO;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.MessagesAction;
import ch.amaba.shared.MessagesResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class MessagesHandler extends AbstractHandler implements ActionHandler<MessagesAction, MessagesResult> {

	@Inject
	MessagesHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public MessagesResult execute(MessagesAction action, ExecutionContext context) throws ActionException {

		final Set<MessageDTO> messagesEnvoyes = SpringFactory.get().getDao().getMessagesEnvoyes(getUserCriteriaSession().getIdUser());

		return new MessagesResult(messagesEnvoyes);
	}

	@Override
	public Class<MessagesAction> getActionType() {
		return MessagesAction.class;
	}

	@Override
	public void undo(MessagesAction arg0, MessagesResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
