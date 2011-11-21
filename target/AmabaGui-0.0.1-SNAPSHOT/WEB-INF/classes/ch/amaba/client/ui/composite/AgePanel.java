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

package ch.amaba.client.ui.composite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple menu that can be reused.
 * 
 * @author ROG
 * 
 */

public class AgePanel extends Composite {
	interface AgePanelUiBinder extends UiBinder<Widget, AgePanel> {
	}

	@UiField
	ListBox ageMinimum;

	@UiField
	ListBox ageMaximum;

	private static AgePanelUiBinder uiBinder = GWT.create(AgePanelUiBinder.class);

	public AgePanel() {
		initWidget(AgePanel.uiBinder.createAndBindUi(this));
	}

	public ListBox getAgeMinimum() {
		return ageMinimum;
	}

	public ListBox getAgeMaximum() {
		return ageMaximum;
	}

}