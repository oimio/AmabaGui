package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.MessageDTO;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.MessageTextAction;
import ch.amaba.shared.MessageTextResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class GetMessageTextHandler extends AbstractHandler implements ActionHandler<MessageTextAction, MessageTextResult> {

	@Inject
	GetMessageTextHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public MessageTextResult execute(MessageTextAction action, ExecutionContext context) throws ActionException {
		final MessageDTO message = SpringFactory.get().getDao().getMessageContentById(getUserCriteriaSession().getIdUser(), action.getIdMessage());
		return new MessageTextResult(message);
	}

	@Override
	public Class<MessageTextAction> getActionType() {
		return MessageTextAction.class;
	}

	@Override
	public void undo(MessageTextAction arg0, MessageTextResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
