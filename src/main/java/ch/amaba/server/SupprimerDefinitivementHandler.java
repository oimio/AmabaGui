package ch.amaba.server;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.SupprimerDefinitivementAction;
import ch.amaba.shared.SupprimerDefinitivementResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class SupprimerDefinitivementHandler extends AbstractHandler implements ActionHandler<SupprimerDefinitivementAction, SupprimerDefinitivementResult> {

	@Inject
	SupprimerDefinitivementHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public SupprimerDefinitivementResult execute(SupprimerDefinitivementAction action, ExecutionContext context) throws ActionException {
		final Set<Long> ids = action.getIds();
		final Set<DefaultEntity> entities = new HashSet<DefaultEntity>();
		for (final Long id : ids) {
			final DefaultEntity entity = SupprimerDefinitivementHandler.getEntityMapping(action.getDtoClassName());
			entity.setEntityId(id);
			entities.add(entity);
		}
		try {
			if (!ids.isEmpty()) {
				SpringFactory.get().getDao().supprimerEntities(entities, getUserCriteriaSession().getIdUser());
			}
		} catch (final EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ActionException();
		}

		return new SupprimerDefinitivementResult();
	}

	public static DefaultEntity getEntityMapping(String dtoClassName) {
		DefaultEntity entity = null;
		if (dtoClassName.equals(MessageDTO.class.getName())) {
			entity = new UserMessageEntity();
		}
		return entity;
	}

	@Override
	public Class<SupprimerDefinitivementAction> getActionType() {
		return SupprimerDefinitivementAction.class;
	}

	@Override
	public void undo(SupprimerDefinitivementAction arg0, SupprimerDefinitivementResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
