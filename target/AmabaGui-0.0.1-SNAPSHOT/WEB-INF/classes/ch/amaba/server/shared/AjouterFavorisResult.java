/**
 * Copyright 2011 ArcBees Inc.
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

package ch.amaba.server.shared;

import java.util.Set;

import ch.amaba.model.bo.CantonDTO;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link AuthentificationAction} action.
 */
public class AjouterFavorisResult implements Result {

	private static final long serialVersionUID = 4621412923270714515L;

	private Set<CantonDTO> cantons;

	public AjouterFavorisResult(final Set<CantonDTO> cantons) {
		this.cantons = cantons;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private AjouterFavorisResult() {
	}

	public Set<CantonDTO> getCantons() {
		return cantons;
	}

}
