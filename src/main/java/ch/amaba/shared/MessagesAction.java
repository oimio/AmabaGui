package ch.amaba.shared;

import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class MessagesAction extends UnsecuredActionImpl<MessagesResult> {

	private static final long serialVersionUID = 1L;

	TypeMessageStatutEnum typeMessageStatutEnum;

	/**
	 * Ne pas supprimer : pour la serialization
	 * */
	@SuppressWarnings("unused")
	private MessagesAction() {
	}

	public MessagesAction(TypeMessageStatutEnum typeMessageStatutEnum) {
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

	public TypeMessageStatutEnum getMessageAction() {
		return typeMessageStatutEnum;
	}

	public void setMessageAction(TypeMessageStatutEnum typeMessageStatutEnum) {
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

}
