package ch.amaba.server.shared;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an {@link DispatchAsync} (client-side)
 * corresponding to a {@link com.gwtplatform.dispatch.server.Dispatch}
 * (server-side).
 */
public class LoadFullUserAction extends UnsecuredActionImpl<LoadFullUserResult> {

	private static final long serialVersionUID = 4621412923270714515L;

	private boolean clearCache;

	public LoadFullUserAction(final boolean clearCache) {
		this.clearCache = clearCache;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private LoadFullUserAction() {
	}

	/**
	 * Renvoie true si le cache doit �tre vid�.
	 * */
	public boolean isClearCache() {
		return clearCache;
	}
}
