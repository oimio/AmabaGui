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

package ch.amaba.client.view;

import ch.amaba.client.IConstants;
import ch.amaba.client.presenter.RechercheDetailleePagePresenter.MyView;
import ch.amaba.client.ui.composite.AgePanel;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.ui.composite.ChoixMultiplePanel;
import ch.amaba.client.utils.ListBoxUtils;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeCaractereEnum;
import ch.amaba.model.bo.constants.TypeInteretEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeProfessionEnum;
import ch.amaba.model.bo.constants.TypeReligionEnum;
import ch.amaba.model.bo.constants.TypeSexeEnum;
import ch.amaba.model.bo.constants.TypeSportEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * Panel de recherche rapide d'un user.
 * 
 * @author ROG
 */
public class RechercheDetailleeView extends ViewImpl implements MyView {
	interface RechercheDetailleeViewUiBinder extends UiBinder<Widget, RechercheDetailleeView> {
	}

	@UiField
	Button rechercheDetailleeButton;

	// @UiField(provided = true)
	// SuggestBox suggestBox;

	@UiField
	RadioButton homme;

	@UiField
	RadioButton femme;

	@UiField
	AgePanel agePanel;

	@UiField
	ListBox genreListBox;

	@UiField
	CantonsListBoxPanel cantonsListBoxPanel;

	@UiField
	ListBox marieListBox;

	@UiField(provided = true)
	ChoixMultiplePanel interetPanel;

	@UiField(provided = true)
	ChoixMultiplePanel sportPanel;

	@UiField(provided = true)
	ChoixMultiplePanel caracterePanel;

	@UiField(provided = true)
	ChoixMultiplePanel musicPanel;

	@UiField(provided = true)
	ChoixMultiplePanel religionPanel;

	@UiField(provided = true)
	ChoixMultiplePanel professionPanel;

	private static RechercheDetailleeViewUiBinder uiBinder = GWT.create(RechercheDetailleeViewUiBinder.class);
	private final MultiWordSuggestOracle mySuggestions = new MultiWordSuggestOracle();
	private final Widget widget;

	public RechercheDetailleeView() {
		interetPanel = new ChoixMultiplePanel(TypeInteretEnum.class, IConstants.ENUM_TYPE_INTERET, "Intérêts");
		musicPanel = new ChoixMultiplePanel(TypeMusiqueEnum.class, IConstants.ENUM_TYPE_MUSIC, "Musique");
		professionPanel = new ChoixMultiplePanel(TypeProfessionEnum.class, IConstants.ENUM_TYPE_PROFESSION, "Profession");
		religionPanel = new ChoixMultiplePanel(TypeReligionEnum.class, IConstants.ENUM_TYPE_RELIGION, "Religion");
		sportPanel = new ChoixMultiplePanel(TypeSportEnum.class, IConstants.ENUM_TYPE_SPORT, "Sport");
		caracterePanel = new ChoixMultiplePanel(TypeCaractereEnum.class, IConstants.ENUM_TYPE_CARACTERE, "Caractère");

		// Provided : constructeur dans la classe avec init des datas.
		// suggestBox = new SuggestBox(mySuggestions);
		widget = RechercheDetailleeView.uiBinder.createAndBindUi(this);
		mySuggestions.add("Cat");
		mySuggestions.add("Dog");
		mySuggestions.add("Avion");
		mySuggestions.add("Argovie");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	/** Retourne le bouton de recherche détaillée. */
	@Override
	public Button getRechercheDetailleeButton() {
		return rechercheDetailleeButton;
	}

	@Override
	public CantonsListBoxPanel getCantonListBoxPanel() {
		return cantonsListBoxPanel;
	}

	/**
	 * Retourne les valeurs des critères de recherche.
	 * 
	 * @return UserCriteria - le contenu de la recherche.
	 */
	@Override
	public UserCriteria getRecherche() {
		final UserCriteria userCriteria = new UserCriteria();
		userCriteria.setIdSexe(homme.getValue() ? TypeSexeEnum.MASCULIN.getId() : TypeSexeEnum.FEMININ.getId());
		userCriteria.setAgeMin(Integer.valueOf(agePanel.getAgeMinimum().getValue(agePanel.getAgeMinimum().getSelectedIndex())));
		userCriteria.setAgeMax(Integer.valueOf(agePanel.getAgeMaximum().getValue(agePanel.getAgeMaximum().getSelectedIndex())));
		userCriteria.setIdCantons(ListBoxUtils.getValues(cantonsListBoxPanel.getCantonsListBox()));
		// Profile
		final ProfileCriteria profileCriteria = new ProfileCriteria();
		profileCriteria.setGenre(Short.valueOf(genreListBox.getValue(genreListBox.getSelectedIndex())));
		profileCriteria.setMarie(marieListBox.getSelectedIndex() == 0 ? null : Short.valueOf(marieListBox.getValue(marieListBox.getSelectedIndex())));
		userCriteria.setProfileCriteria(profileCriteria);
		// Interet - sport - music - religion - profession
		userCriteria.setIdInterets(interetPanel.getValues());
		userCriteria.setIdSports(sportPanel.getValues());
		userCriteria.setIdProfessions(professionPanel.getValues());
		userCriteria.setIdReligions(religionPanel.getValues());
		userCriteria.setIdMusiques(musicPanel.getValues());
		userCriteria.setIdCaracteres(caracterePanel.getValues());
		return userCriteria;
	}

	@Override
	public ListBox getInteretListBox() {
		return interetPanel.getListBoxPanel().getListBox();
	}

	@Override
	public ListBox getMusicListBox() {
		return musicPanel.getListBoxPanel().getListBox();
	}

	@Override
	public ListBox getProfessionListBox() {
		return professionPanel.getListBoxPanel().getListBox();
	}

	@Override
	public ListBox getReligionListBox() {
		return religionPanel.getListBoxPanel().getListBox();
	}

	@Override
	public ListBox getSportListBox() {
		return sportPanel.getListBoxPanel().getListBox();
	}

	@Override
	public ListBox getCaractereListBox() {
		return caracterePanel.getListBoxPanel().getListBox();
	}

}
