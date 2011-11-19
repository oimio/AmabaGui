package ch.amaba.server.shared;

import ch.amaba.model.bo.UserCriteria;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class RechercheDetailleeAction extends UnsecuredActionImpl<RechercheDetailleeResult> {

	private static final long serialVersionUID = 4621412923270714515L;

	private UserCriteria recherche;

	public RechercheDetailleeAction(final UserCriteria recherche) {
		this.recherche = recherche;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private RechercheDetailleeAction() {
	}

	/**
	 * Retourne les critères de recherche.
	 * */
	public UserCriteria getRecherche() {
		return recherche;
	}
}
