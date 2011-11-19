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

package ch.amaba.client.ui;

import java.util.List;

import ch.amaba.client.presenter.LoginPagePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author ROG
 */
public class LoginPageView extends ViewImpl implements LoginPagePresenter.MyView {

	interface LoginPageViewUiBinder extends UiBinder<Widget, LoginPageView> {
	}

	private static LoginPageViewUiBinder uiBinder = GWT.create(LoginPageViewUiBinder.class);

	public Widget widget;

	@UiField
	TextBox emailAuthTextBox;

	@UiField
	PasswordTextBox passwordAuthTextBox;

	@UiField
	HTMLPanel htmlPanel;

	@UiField
	VerticalPanel errorPanel;

	@UiField
	ListBox genreListBox;

	@UiField
	TextBox nomTextBox;

	@UiField
	TextBox prenomTextBox;

	@UiField
	TextBox emailTextBox;

	@UiField
	TextBox emailRepeatTextBox;

	@UiField
	TextBox passwordTextBox;

	@UiField
	TextBox passwordRepeatTextBox;

	@UiField
	TextBox jourTextBox;

	@UiField
	TextBox moisTextBox;

	@UiField
	TextBox anneeTextBox;

	@UiField
	Button sendButton;

	@UiField
	ListBox cantonListBox;

	@UiField
	Button devenirMembreButton;

	public LoginPageView() {
		widget = LoginPageView.uiBinder.createAndBindUi(this);

		// MOCK to remove !!!!!!!
		// supprimer ces lignes !!!!!!!!!!
		// JUST FOR TEST
		getEmailAuthTextBox().setText("hugo@gmail.com");
		getPasswordAuthTextBox().setText("123");
	}

	public Widget asWidget() {
		return widget;
	}

	public void setError(List<String> messages) {
		errorPanel.clear();
		for (final String message : messages) {
			final Label l = new Label(message);
			DOM.setElementAttribute(l.getElement(), "id", "my-button-id");
			errorPanel.add(l);
		}
	}

	public TextBox getEmailTextBox() {
		return emailTextBox;
	}

	public Button getSendButton() {
		return sendButton;
	}

	public void resetAndFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == LoginPagePresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		htmlPanel.clear();

		if (content != null) {
			htmlPanel.add(content);
		}
	}

	public ListBox getCantonListBox() {
		return cantonListBox;
	}

	public Button getDevenirMembreButton() {
		return devenirMembreButton;
	}

	public TextBox getEmailRepeatTextBox() {
		return emailRepeatTextBox;
	}

	public TextBox getPasswordTextBox() {
		return passwordTextBox;
	}

	public TextBox getPasswordRepeatTextBox() {
		return passwordRepeatTextBox;
	}

	public TextBox getNomTextBox() {
		return nomTextBox;
	}

	public TextBox getPrenomTextBox() {
		return prenomTextBox;
	}

	public TextBox getJourTextBox() {
		return jourTextBox;
	}

	public TextBox getMoisTextBox() {
		return moisTextBox;
	}

	public TextBox getAnneeTextBox() {
		return anneeTextBox;
	}

	public ListBox getGenreListBox() {
		return genreListBox;
	}

	public VerticalPanel getErrorPanel() {
		return errorPanel;
	}

	public TextBox getEmailAuthTextBox() {
		return emailAuthTextBox;
	}

	public PasswordTextBox getPasswordAuthTextBox() {
		return passwordAuthTextBox;
	}
}
