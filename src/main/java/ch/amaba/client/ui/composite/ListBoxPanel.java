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

import ch.amaba.client.IConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * ListBox des sports
 * 
 * @author ROG
 */
public class ListBoxPanel extends Composite {
	interface ListBoxUiBinder extends UiBinder<Widget, ListBoxPanel> {
	}

	private static ListBoxUiBinder uiBinder = GWT.create(ListBoxUiBinder.class);

	@UiField
	ListBox listBox;

	@UiField
	Label headerLabel;

	public ListBoxPanel() {
		initWidget(ListBoxPanel.uiBinder.createAndBindUi(this));
		listBox.setWidth(IConstants.CHOIX_MULTIPLE_WIDTH);
	}

	public ListBox getListBox() {
		return listBox;
	}

	public void setHeaderText(String text) {
		headerLabel.setText(text);
	}
}