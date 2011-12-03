/**
 * Copyright 2011 Amaba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ch.amaba.shared;

import ch.amaba.model.bo.UserCriteria;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link AuthentificationAction} action.
 */
public class AuthentificationResult implements Result {

	private static final long serialVersionUID = 4621412923270714515L;

	private UserCriteria userCriteria;

	public AuthentificationResult(final UserCriteria userCriteria) {
		this.userCriteria = userCriteria;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private AuthentificationResult() {
	}

	public UserCriteria getUserCriteria() {
		return userCriteria;
	}

}
