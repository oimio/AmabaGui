package ch.amaba.shared;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class ListeFavorisAction extends UnsecuredActionImpl<ListeFavorisResult> {

	private static final long serialVersionUID = 1L;

	private Long idAmi;

	public ListeFavorisAction(final Long idAmi) {
		this.idAmi = idAmi;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private ListeFavorisAction() {
	}

	/**
	 * Retourne l'id de l'ami � ajouter.
	 * */
	public Long getIdAmi() {
		return idAmi;
	}
}
