package ch.amaba.shared;

import java.util.Set;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class SupprimerDefinitivementAction extends UnsecuredActionImpl<SupprimerDefinitivementResult> {

	private static final long serialVersionUID = 1L;

	private String dtoClassName;

	private Set<Long> ids;

	/**
	 * Ne pas supprimer : pour la serialization
	 * */
	@SuppressWarnings("unused")
	private SupprimerDefinitivementAction() {
	}

	public SupprimerDefinitivementAction(Set<Long> ids, String dtoClassName) {
		this.ids = ids;
		this.dtoClassName = dtoClassName;
	}

	public String getDtoClassName() {
		return dtoClassName;
	}

	public Set<Long> getIds() {
		return ids;
	}
}