package ch.amaba.shared;

import java.util.Map;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link AuthentificationAction} action.
 */
public class LoadTraductionsResult implements Result {

	private static final long serialVersionUID = 4621412923270714515L;

	private Map<String, Map<String, String>> traductions;

	public LoadTraductionsResult(final Map<String, Map<String, String>> traductions) {
		this.traductions = traductions;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private LoadTraductionsResult() {
	}

	public Map<String, Map<String, String>> getTraductions() {
		return traductions;
	}

}
