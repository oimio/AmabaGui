package ch.amaba.shared;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class MessagesAction extends UnsecuredActionImpl<MessagesResult> {

	private static final long serialVersionUID = 1L;

	/**
	 * For serialization only.
	 */
	public MessagesAction() {
	}

}
