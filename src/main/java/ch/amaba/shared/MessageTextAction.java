package ch.amaba.shared;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class MessageTextAction extends UnsecuredActionImpl<MessageTextResult> {

	private static final long serialVersionUID = 1L;

	private Long idMessage;

	/**
	 * Ne pas supprimer : pour la serialization
	 * */
	@SuppressWarnings("unused")
	private MessageTextAction() {
	}

	public MessageTextAction(Long idMessage) {
		this.idMessage = idMessage;
	}

	public Long getIdMessage() {
		return idMessage;
	}

}
