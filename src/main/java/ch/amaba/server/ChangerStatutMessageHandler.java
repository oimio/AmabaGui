package ch.amaba.server;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.ChangerStatutMessageAction;
import ch.amaba.shared.ChangerStatutMessageResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class ChangerStatutMessageHandler extends AbstractHandler implements ActionHandler<ChangerStatutMessageAction, ChangerStatutMessageResult> {

	@Inject
	ChangerStatutMessageHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public ChangerStatutMessageResult execute(ChangerStatutMessageAction action, ExecutionContext context) throws ActionException {
		final Set<Long> ids = action.getIds();
		try {
			if (!ids.isEmpty()) {
				SpringFactory.get().getDao().changerMessagesStatut(ids, getUserCriteriaSession().getIdUser(), action.getTypeMessageStatutEnum());
			}
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ActionException(e != null ? e.getMessage() : null);
		}

		return new ChangerStatutMessageResult(action.getTypeMessageStatutEnum());
	}

	public static DefaultEntity getEntityMapping(String dtoClassName) {
		DefaultEntity entity = null;
		if (dtoClassName.equals(MessageDTO.class.getName())) {
			entity = new UserMessageEntity();
		}
		return entity;
	}

	@Override
	public Class<ChangerStatutMessageAction> getActionType() {
		return ChangerStatutMessageAction.class;
	}

	@Override
	public void undo(ChangerStatutMessageAction arg0, ChangerStatutMessageResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
