package ch.amaba.shared;

import ch.amaba.model.bo.UserCriteria;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link AuthentificationAction} action.
 */
public class LogoffResult implements Result {

	private static final long serialVersionUID = 1L;

	private UserCriteria userCriteria;

	public LogoffResult(final UserCriteria userCriteria) {
		this.userCriteria = userCriteria;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	public LogoffResult() {
	}

	public UserCriteria getUserCriteria() {
		return userCriteria;
	}

}
