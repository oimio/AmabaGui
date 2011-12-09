package ch.amaba.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.exception.DuplicateEntityException;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.AjouterFavorisAction;
import ch.amaba.shared.AjouterFavorisResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class AjouterFavorisHandler extends AbstractHandler implements ActionHandler<AjouterFavorisAction, AjouterFavorisResult> {

	@Inject
	AjouterFavorisHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public AjouterFavorisResult execute(AjouterFavorisAction action, ExecutionContext context) throws ActionException {
		try {
			SpringFactory.get().getDao().ajouterFavori(getUserCriteriaSession().getIdUser(), action.getIdAmi());
		} catch (final DuplicateEntityException e) {
			e.printStackTrace();
			throw new ActionException("Ce favori existe d�j�.");
		}
		return new AjouterFavorisResult();
	}

	@Override
	public Class<AjouterFavorisAction> getActionType() {
		return AjouterFavorisAction.class;
	}

	@Override
	public void undo(AjouterFavorisAction arg0, AjouterFavorisResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
