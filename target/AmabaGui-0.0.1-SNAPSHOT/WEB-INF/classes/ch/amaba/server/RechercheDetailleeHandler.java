package ch.amaba.server;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.server.shared.RechercheDetailleeAction;
import ch.amaba.server.shared.RechercheDetailleeResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class RechercheDetailleeHandler extends AbstractHandler implements ActionHandler<RechercheDetailleeAction, RechercheDetailleeResult> {

	@Inject
	RechercheDetailleeHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	/**
	 * Execute la recherche détaillée.
	 * 
	 * @return RechercheDetailleeResult - contient un Set<Usercriteria>
	 */
	
	public RechercheDetailleeResult execute(RechercheDetailleeAction action, ExecutionContext context) throws ActionException {
		final Set<UserCriteria> matches = new HashSet<UserCriteria>();
		final RechercheDetailleeResult result = new RechercheDetailleeResult(matches);
		final UserCriteria userCriteria = action.getRecherche();
		try {
			final Set<UserEntity> findUserBycriteria = AbstractHandler.dao.findUserBycriteria(userCriteria);
			for (final UserEntity userEntity : findUserBycriteria) {
				// On devra filter les donnees à remonter en fonction
				// de la confidentialité du user.
				final UserCriteria match = new UserCriteria();
				match.setPrenom(userEntity.getPrenom());
				match.setDateNaissance(userEntity.getDateNaissance());
				match.setIdCantons(new HashSet<Integer>(userEntity.getIdCanton()));
				matches.add(match);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public Class<RechercheDetailleeAction> getActionType() {
		return RechercheDetailleeAction.class;
	}

	
	public void undo(RechercheDetailleeAction arg0, RechercheDetailleeResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
