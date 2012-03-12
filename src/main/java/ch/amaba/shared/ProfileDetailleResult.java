package ch.amaba.shared;

import ch.amaba.model.bo.UserCriteria;

import com.gwtplatform.dispatch.shared.Result;

/**
 * Résultat de {@link MessagesAction} action.
 */
public class ProfileDetailleResult implements Result {

	private static final long serialVersionUID = 1L;

	private UserCriteria userCriteria;

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private ProfileDetailleResult() {
	}

	public ProfileDetailleResult(final UserCriteria userCriteria) {
		setUserCriteria(userCriteria);
	}

	public void setUserCriteria(UserCriteria userCriteria) {
		this.userCriteria = userCriteria;
	}

	public UserCriteria getUserCriteria() {
		return userCriteria;
	}

}