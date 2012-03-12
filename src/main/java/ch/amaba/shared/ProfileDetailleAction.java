package ch.amaba.shared;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class ProfileDetailleAction extends UnsecuredActionImpl<ProfileDetailleResult> {

	private static final long serialVersionUID = 1L;
	private Long idUser;

	/**
	 * Ne pas supprimer : pour la serialization
	 * */
	@SuppressWarnings("unused")
	private ProfileDetailleAction() {
	}

	public ProfileDetailleAction(final Long idUser) {
		this.idUser = idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdUser() {
		return idUser;
	}
}
