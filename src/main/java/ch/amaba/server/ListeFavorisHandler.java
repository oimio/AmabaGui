package ch.amaba.server;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.AmiDTO;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.ListeFavorisAction;
import ch.amaba.shared.ListeFavorisResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class ListeFavorisHandler extends AbstractHandler implements ActionHandler<ListeFavorisAction, ListeFavorisResult> {

	@Inject
	ListeFavorisHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public ListeFavorisResult execute(ListeFavorisAction action, ExecutionContext context) throws ActionException {

		Long idUser = action.getIdAmi();
		if (idUser == null) {
			idUser = getUserCriteriaSession().getIdUser();
		}

		final Set<AmiDTO> listeFavoris = SpringFactory.get().getDao().findAmis(idUser);

		return new ListeFavorisResult(listeFavoris);
	}

	@Override
	public Class<ListeFavorisAction> getActionType() {
		return ListeFavorisAction.class;
	}

	@Override
	public void undo(ListeFavorisAction arg0, ListeFavorisResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
