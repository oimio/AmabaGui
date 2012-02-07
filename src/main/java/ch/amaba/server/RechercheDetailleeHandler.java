package ch.amaba.server;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.RechercheDetailleeAction;
import ch.amaba.shared.RechercheDetailleeResult;

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

	@Override
	public RechercheDetailleeResult execute(RechercheDetailleeAction action, ExecutionContext context) throws ActionException {
		final Set<UserCriteria> matches = new HashSet<UserCriteria>();
		final RechercheDetailleeResult result = new RechercheDetailleeResult(matches);
		final UserCriteria userCriteria = action.getRecherche();

		final Set<UserEntity> findUserBycriteria = SpringFactory.get().getDao().findUserBycriteria(userCriteria);
		for (final UserEntity userEntity : findUserBycriteria) {
			// On devra filter les donnees à remonter en fonction
			// de la confidentialité du user.
			final UserCriteria match = new UserCriteria();
			match.setIdUser(userEntity.getEntityId());
			match.setPrenom(userEntity.getPrenom());
			match.setDateNaissance(userEntity.getDateNaissance());
			final Integer idCanton = userEntity.getIdCanton();
			final HashSet<Integer> canton = new HashSet<Integer>();
			canton.add(idCanton);
			match.setIdCantons(canton);

			// Photo principale
			final PhotoDTO photoPrincipale = userEntity.getPhotoPrincipale();
			if (photoPrincipale != null) {
				match.addPhoto(photoPrincipale);
			}
			matches.add(match);
		}

		return result;
	}

	@Override
	public Class<RechercheDetailleeAction> getActionType() {
		return RechercheDetailleeAction.class;
	}

	@Override
	public void undo(RechercheDetailleeAction arg0, RechercheDetailleeResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
