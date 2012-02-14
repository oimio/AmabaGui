/**
 *
 *  Amaba (C) 2011  <name of author>
 *
 *  Amaba is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package ch.amaba.client.ui;

import java.util.List;

import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.utils.NumberUtils;
import ch.amaba.client.utils.StringUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
		getEmailAuthTextBox().setText("elodie@gmail.com");
		getPasswordAuthTextBox().setText("123");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setError(List<String> messages) {
		errorPanel.clear();
		for (final String message : messages) {
			final Label l = new Label(message);
			DOM.setElementAttribute(l.getElement(), "id", "my-button-id");
			errorPanel.add(l);
		}
	}

	@Override
	public TextBox getEmailTextBox() {
		return emailTextBox;
	}

	@Override
	public Button getSendButton() {
		return sendButton;
	}

	@Override
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

	@Override
	public ListBox getCantonListBox() {
		return cantonListBox;
	}

	@Override
	public Button getDevenirMembreButton() {
		return devenirMembreButton;
	}

	@Override
	public TextBox getEmailRepeatTextBox() {
		return emailRepeatTextBox;
	}

	@Override
	public TextBox getPasswordTextBox() {
		return passwordTextBox;
	}

	@Override
	public TextBox getPasswordRepeatTextBox() {
		return passwordRepeatTextBox;
	}

	@Override
	public TextBox getNomTextBox() {
		return nomTextBox;
	}

	@Override
	public TextBox getPrenomTextBox() {
		return prenomTextBox;
	}

	@Override
	public TextBox getJourTextBox() {
		return jourTextBox;
	}

	@Override
	public TextBox getMoisTextBox() {
		return moisTextBox;
	}

	@Override
	public TextBox getAnneeTextBox() {
		return anneeTextBox;
	}

	@Override
	public ListBox getGenreListBox() {
		return genreListBox;
	}

	@Override
	public VerticalPanel getErrorPanel() {
		return errorPanel;
	}

	@Override
	public TextBox getEmailAuthTextBox() {
		return emailAuthTextBox;
	}

	@Override
	public PasswordTextBox getPasswordAuthTextBox() {
		return passwordAuthTextBox;
	}

	@UiHandler("jourTextBox")
	void handle(BlurEvent e) {
		final String text = jourTextBox.getText();
		if (!StringUtils.isBlank(text) && NumberUtils.isInteger(text)) {
			if (text.length() == 1) {
				jourTextBox.setText(new StringBuffer("0").append(text).toString());
			}
		}
	}

	@UiHandler("jourTextBox")
	void handle(KeyDownEvent e) {
		final int nativeKeyCode = e.getNativeKeyCode();
		if (((nativeKeyCode < 96) || (nativeKeyCode > 108)) && (nativeKeyCode != KeyCodes.KEY_TAB) && (nativeKeyCode != KeyCodes.KEY_DELETE)
		    && (nativeKeyCode != KeyCodes.KEY_BACKSPACE) && (nativeKeyCode != KeyCodes.KEY_LEFT) && (nativeKeyCode != KeyCodes.KEY_RIGHT)
		    && (nativeKeyCode != KeyCodes.KEY_HOME)) {
			e.preventDefault();
			e.stopPropagation();
		}
	}

	@UiHandler("jourTextBox")
	void handle(KeyUpEvent e) {
		if ((e.getNativeKeyCode() != KeyCodes.KEY_TAB) && (e.getNativeKeyCode() != KeyCodes.KEY_SHIFT) && (e.getNativeKeyCode() != KeyCodes.KEY_LEFT)
		    && (e.getNativeKeyCode() != KeyCodes.KEY_RIGHT)) {
			moisTextBox.setFocus(jourTextBox.getText().length() == 2);
		}
	}
}
