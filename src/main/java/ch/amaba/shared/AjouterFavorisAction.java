package ch.amaba.shared;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class AjouterFavorisAction extends UnsecuredActionImpl<AjouterFavorisResult> {

	private static final long serialVersionUID = 4621412923270714515L;

	private Long idAmi;

	public AjouterFavorisAction(final Long idAmi) {
		this.idAmi = idAmi;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private AjouterFavorisAction() {
	}

	/**
	 * Retourne l'id de l'ami ajouter.
	 * */
	public Long getIdAmi() {
		return idAmi;
	}
}
