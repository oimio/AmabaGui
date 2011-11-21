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

import ch.amaba.client.presenter.RechercheDetailleePagePresenter.MyView;
import ch.amaba.client.ui.composite.AgePanel;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeSexeEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SuggestBox;
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

	@UiField(provided = true)
	SuggestBox suggestBox;

	@UiField
	RadioButton homme;

	@UiField
	RadioButton femme;

	@UiField
	AgePanel agePanel;

	@UiField
	ListBox genreListBox;

	@UiField(provided = true)
	ListBox religionListe;

	@UiField
	CantonsListBoxPanel cantonsListBoxPanel;

	@UiField
	ListBox marieListBox;

	private static RechercheDetailleeViewUiBinder uiBinder = GWT.create(RechercheDetailleeViewUiBinder.class);
	private final MultiWordSuggestOracle mySuggestions = new MultiWordSuggestOracle();
	private final Widget widget;

	public RechercheDetailleeView() {
		// Provided : constructeur dans la classe avec init des datas.
		suggestBox = new SuggestBox(mySuggestions);
		religionListe = new ListBox();
		widget = RechercheDetailleeView.uiBinder.createAndBindUi(this);
		mySuggestions.add("Cat");
		mySuggestions.add("Dog");
		mySuggestions.add("Avion");
		mySuggestions.add("Argovie");

		religionListe.addItem("Catholique", "0");

	}

	public Widget asWidget() {
		return widget;
	}

	public ListBox getReligionListeBox() {
		return religionListe;
	}

	public Button getRechercheDetailleeButton() {
		return rechercheDetailleeButton;
	}

	public CantonsListBoxPanel getCantonListBoxPanel() {
		return cantonsListBoxPanel;
	}

	public UserCriteria getRecherche() {
		final UserCriteria userCriteria = new UserCriteria();
		userCriteria.setIdSexe(homme.getValue() ? TypeSexeEnum.MASCULIN.getId() : TypeSexeEnum.FEMININ.getId());
		userCriteria.setAgeMin(Integer.valueOf(agePanel.getAgeMinimum().getValue(agePanel.getAgeMinimum().getSelectedIndex())));
		userCriteria.setAgeMax(Integer.valueOf(agePanel.getAgeMaximum().getValue(agePanel.getAgeMaximum().getSelectedIndex())));
		userCriteria.setIdCantons(CantonUtils.getValues(cantonsListBoxPanel.getCantonsListBox()));
		// Profile
		final ProfileCriteria profileCriteria = new ProfileCriteria();
		profileCriteria.setGenre(Short.valueOf(genreListBox.getValue(genreListBox.getSelectedIndex())));
		profileCriteria.setMarie(marieListBox.getSelectedIndex() == 0 ? null : Short.valueOf(marieListBox.getValue(marieListBox.getSelectedIndex())));
		userCriteria.setProfileCriteria(profileCriteria);
		return userCriteria;
	}
}