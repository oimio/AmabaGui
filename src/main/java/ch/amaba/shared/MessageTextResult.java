package ch.amaba.shared;

import ch.amaba.model.bo.MessageDTO;

import com.gwtplatform.dispatch.shared.Result;

public class MessageTextResult implements Result {

	private static final long serialVersionUID = 1L;

	private MessageDTO message;

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private MessageTextResult() {
	}

	public MessageTextResult(final MessageDTO message) {
		this.message = message;
	}

	public MessageDTO getMessage() {
		return message;
	}
}