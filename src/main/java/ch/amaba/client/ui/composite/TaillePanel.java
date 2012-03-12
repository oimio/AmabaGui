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
 * License for the specific langutaille governing permissions and limitations under
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
 * ListeBox des tailles.
 * 
 * @author ROG
 * 
 */
public class TaillePanel extends Composite {
	interface TaillePanelUiBinder extends UiBinder<Widget, TaillePanel> {
	}

	@UiField
	ListBox tailleListBox;

	private static TaillePanelUiBinder uiBinder = GWT.create(TaillePanelUiBinder.class);

	public TaillePanel() {
		initWidget(TaillePanel.uiBinder.createAndBindUi(this));
	}

	public ListBox gettailleListBox() {
		return tailleListBox;
	}
}