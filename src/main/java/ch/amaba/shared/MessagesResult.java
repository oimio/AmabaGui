package ch.amaba.shared;

import java.util.Set;

import ch.amaba.model.bo.MessageDTO;

import com.gwtplatform.dispatch.shared.Result;

/**
 * Résultat de {@link MessagesAction} action.
 */
public class MessagesResult implements Result {

	private static final long serialVersionUID = 1L;

	private Set<MessageDTO> messages;

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private MessagesResult() {
	}

	public MessagesResult(final Set<MessageDTO> messages) {
		this.messages = messages;
	}

	public Set<MessageDTO> getMessages() {
		return messages;
	}
}