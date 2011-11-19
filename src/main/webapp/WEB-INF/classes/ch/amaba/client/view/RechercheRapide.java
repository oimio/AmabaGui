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

package ch.amaba.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel de recherche rapide d'un user.
 * 
 * @author ROG
 */
public class RechercheRapide extends Composite {
	interface RechercheRapideUiBinder extends UiBinder<Widget, RechercheRapide> {
	}

	// @UiField(provided = true)
	// SuggestBox suggestBox;

	private static RechercheRapideUiBinder uiBinder = GWT.create(RechercheRapideUiBinder.class);
	private final MultiWordSuggestOracle mySuggestions = new MultiWordSuggestOracle();

	public RechercheRapide() {
		// suggestBox = new SuggestBox(mySuggestions);
		initWidget(RechercheRapide.uiBinder.createAndBindUi(this));
		// mySuggestions.add("Cat");
		// mySuggestions.add("Dog");
		// mySuggestions.add("Avion");
		// mySuggestions.add("Argovie");

	}
}