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

import java.util.Date;
import java.util.List;
import java.util.Set;

import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.ModifierDonneesPagePresenter;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.ui.composite.ChoixMultiplePanel;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeSportEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author ROG
 */
public class ModifierDonneesPageView extends ViewImpl implements ModifierDonneesPagePresenter.MyView {

	interface MainPageViewUiBinder extends UiBinder<Widget, ModifierDonneesPageView> {
	}

	private static MainPageViewUiBinder uiBinder = GWT.create(MainPageViewUiBinder.class);

	public Widget widget;

	@UiField
	VerticalPanel htmlPanel;

	@UiField
	ListBox codeSexeListBox;

	@UiField
	TextBox nomTextBox;

	@UiField
	TextBox prenomTextBox;

	@UiField
	TextBox passwordAncienTextBox;

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
	Button modifierButton;

	@UiField
	CantonsListBoxPanel cantonsListBoxPanel;

	@UiField
	ChoixMultiplePanel choixMultiplePanel;

	VerticalPanel errorPanel;

	public ModifierDonneesPageView() {
		widget = ModifierDonneesPageView.uiBinder.createAndBindUi(this);
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

	public void resetAndFocus() {
		final UserCriteria userCriteria = ContextUI.get().getUserCourant();
		if (userCriteria != null) {
			// Init des informations du user
			getNomTextBox().setText(userCriteria.getNom());
			getPrenomTextBox().setText(userCriteria.getPrenom());
			final Date dateNaissance = userCriteria.getDateNaissance();
			getJourTextBox().setText(DateUtils.getDay(dateNaissance));
			getMoisTextBox().setText(DateUtils.getMonth(dateNaissance));
			getAnneeTextBox().setText(DateUtils.getYear(dateNaissance));
			getCodeSexeListBox().setSelectedIndex(userCriteria.getIdSexe());
			final Set<Integer> idSports = userCriteria.getIdSports();

			for (final Integer idSport : idSports) {
				final TypeSportEnum enumById = TypeSportEnum.getEnumById(idSport);
				choixMultiplePanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
			}
			// Un user doit forcément avoir un idCanton.
			final Integer idUserCanton = userCriteria.getIdCantons().iterator().next();
			CantonUtils.setSelectedIndex(getCantonsListBoxPanel().getCantonsListBox(), idUserCanton);
		}
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

	public CantonsListBoxPanel getCantonsListBoxPanel() {
		return cantonsListBoxPanel;
	}

	public Button getModifierButton() {
		return modifierButton;
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

	public ListBox getCodeSexeListBox() {
		return codeSexeListBox;
	}

	public VerticalPanel getErrorPanel() {
		return errorPanel;
	}

	public TextBox getPasswordAncienTextBox() {
		return passwordAncienTextBox;
	}

	public ChoixMultiplePanel getChoixMultiplePanel() {
		return choixMultiplePanel;
	}

}
