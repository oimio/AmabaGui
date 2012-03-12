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

package ch.amaba.client.ui;

import java.util.Date;
import java.util.List;

import ch.amaba.client.IConstants;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.ModifierDonneesPagePresenter;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.ui.composite.ChoixMultiplePanel;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeCaractereEnum;
import ch.amaba.model.bo.constants.TypeCouleurCheveux;
import ch.amaba.model.bo.constants.TypeCouleurYeux;
import ch.amaba.model.bo.constants.TypeInteretEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeProfessionEnum;
import ch.amaba.model.bo.constants.TypeRaceEnum;
import ch.amaba.model.bo.constants.TypeReligionEnum;
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
public class ModifierDonneesView extends ViewImpl implements ModifierDonneesPagePresenter.MyView {

	interface ModifierDonneesViewUiBinder extends UiBinder<Widget, ModifierDonneesView> {
	}

	private static ModifierDonneesViewUiBinder uiBinder = GWT.create(ModifierDonneesViewUiBinder.class);

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

	@UiField(provided = true)
	ChoixMultiplePanel interetPanel;

	@UiField(provided = true)
	ChoixMultiplePanel sportPanel;

	@UiField(provided = true)
	ChoixMultiplePanel musicPanel;

	@UiField(provided = true)
	ChoixMultiplePanel caracterePanel;

	@UiField(provided = true)
	ChoixMultiplePanel religionPanel;

	@UiField(provided = true)
	ChoixMultiplePanel professionPanel;

	@UiField(provided = true)
	ChoixMultiplePanel couleurCheveuxPanel;

	@UiField(provided = true)
	ChoixMultiplePanel couleurYeuxPanel;

	@UiField(provided = true)
	ChoixMultiplePanel racePanel;

	VerticalPanel errorPanel;

	public ModifierDonneesView() {
		interetPanel = new ChoixMultiplePanel(TypeInteretEnum.class, IConstants.ENUM_TYPE_INTERET, "Intérêts");
		musicPanel = new ChoixMultiplePanel(TypeMusiqueEnum.class, IConstants.ENUM_TYPE_MUSIC, "Musique");
		professionPanel = new ChoixMultiplePanel(TypeProfessionEnum.class, IConstants.ENUM_TYPE_PROFESSION, "Profession");
		religionPanel = new ChoixMultiplePanel(TypeReligionEnum.class, IConstants.ENUM_TYPE_RELIGION, "Religion");
		sportPanel = new ChoixMultiplePanel(TypeSportEnum.class, IConstants.ENUM_TYPE_SPORT, "Sport");
		caracterePanel = new ChoixMultiplePanel(TypeCaractereEnum.class, IConstants.ENUM_TYPE_CARACTERE, "Caractère");
		couleurCheveuxPanel = new ChoixMultiplePanel(TypeCouleurCheveux.class, IConstants.ENUM_TYPE_COULEUR_CHEVEUX, "Couleur cheveux", 1, 1);
		couleurYeuxPanel = new ChoixMultiplePanel(TypeCouleurYeux.class, IConstants.ENUM_TYPE_COULEUR_YEUX, "Couleur yeux", 1, 1);
		racePanel = new ChoixMultiplePanel(TypeRaceEnum.class, IConstants.ENUM_TYPE_RACE, "Type", 1, 1);
		widget = ModifierDonneesView.uiBinder.createAndBindUi(this);
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

			if (userCriteria.getIdSports() != null) {
				for (final Integer idSport : userCriteria.getIdSports()) {
					final TypeSportEnum enumById = TypeSportEnum.getEnumById(idSport);
					sportPanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
				}
			}
			if (userCriteria.getIdReligions() != null) {
				for (final Integer id : userCriteria.getIdReligions()) {
					final TypeReligionEnum enumById = TypeReligionEnum.getEnumById(id);
					religionPanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
				}
			}
			if (userCriteria.getIdInterets() != null) {
				for (final Integer id : userCriteria.getIdInterets()) {
					final TypeInteretEnum enumById = TypeInteretEnum.getEnumById(id);
					interetPanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
				}
			}
			if (userCriteria.getIdProfessions() != null) {
				for (final Integer id : userCriteria.getIdProfessions()) {
					final TypeProfessionEnum enumById = TypeProfessionEnum.getEnumById(id);
					professionPanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
				}
			}
			if (userCriteria.getIdMusiques() != null) {
				for (final Integer id : userCriteria.getIdMusiques()) {
					final TypeMusiqueEnum enumById = TypeMusiqueEnum.getEnumById(id);
					musicPanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
				}
			}
			if (userCriteria.getIdCaracteres() != null) {
				for (final Integer idCaractere : userCriteria.getIdCaracteres()) {
					final TypeCaractereEnum enumById = TypeCaractereEnum.getEnumById(idCaractere);
					caracterePanel.ajouterPreference(enumById.name(), Integer.toString(enumById.getId()));
				}
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

	@Override
	public CantonsListBoxPanel getCantonsListBoxPanel() {
		return cantonsListBoxPanel;
	}

	@Override
	public Button getModifierButton() {
		return modifierButton;
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
	public ListBox getCodeSexeListBox() {
		return codeSexeListBox;
	}

	@Override
	public VerticalPanel getErrorPanel() {
		return errorPanel;
	}

	@Override
	public TextBox getPasswordAncienTextBox() {
		return passwordAncienTextBox;
	}

	public ChoixMultiplePanel getSportPanel() {
		return sportPanel;
	}

}
