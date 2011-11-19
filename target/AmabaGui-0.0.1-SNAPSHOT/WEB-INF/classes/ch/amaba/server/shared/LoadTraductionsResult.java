package ch.amaba.server.shared;

import java.util.HashMap;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link AuthentificationAction} action.
 */
public class LoadTraductionsResult implements Result {

	private static final long serialVersionUID = 4621412923270714515L;

	private HashMap<String, HashMap<String, String>> traductions;

	public LoadTraductionsResult(final HashMap<String, HashMap<String, String>> traductions) {
		this.traductions = traductions;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private LoadTraductionsResult() {
	}

	public HashMap<String, HashMap<String, String>> getTraductions() {
		return traductions;
	}

}
