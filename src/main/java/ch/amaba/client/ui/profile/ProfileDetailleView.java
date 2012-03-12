package ch.amaba.client.ui.profile;

import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.ProfileDetaillePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * Vue des messages (nouveaux, recus, envoyes et supprimes) :<br>
 * <ul>
 * <li>- header</li>
 * <li>- panel de la liste des lessages</li>
 * <li>- panel contenant le text d'un message</li>
 * </ul>
 * 
 * @author ROG
 */
public class ProfileDetailleView extends ViewImpl implements ProfileDetaillePresenter.MyView {

	interface ProfileDetailleViewUiBinder extends UiBinder<Widget, ProfileDetailleView> {
	}

	private static ProfileDetailleViewUiBinder uiBinder = GWT.create(ProfileDetailleViewUiBinder.class);

	public Widget widget;

	@UiField
	HTMLPanel htmlPanel;

	@UiField
	Label nom;

	@UiField
	Label prenom;

	@UiField
	Label age;

	@UiField
	Label sexe;

	@UiField
	Image photoPrincipale;

	@UiField
	ScrollPanel favorisPanel;

	@UiField
	Label nombreEnfants;

	@UiField
	Label divorce;

	@UiField
	Label marie;

	@UiField
	Label veuf;

	@UiField
	Label genre;

	@UiField
	Label relationSerieuse;

	@UiField
	Label poids;

	@UiField
	Label taille;

	@UiField
	Label cheveux;

	@UiField
	Label yeux;

	@UiField
	FlowPanel interets;

	@UiField
	FlowPanel musiques;

	@UiField
	FlowPanel religions;

	@UiField
	FlowPanel races;

	@UiField
	FlowPanel sports;
	@UiField
	FlowPanel professions;

	@UiField
	FlowPanel caracteres;

	public ProfileDetailleView() {
		widget = ProfileDetailleView.uiBinder.createAndBindUi(this);
		// messagesTable.getColumnFormatter().setWidth(0, "600px");
	}

	@Override
	public Widget asWidget() {
		return widget;
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

	}

	public void setPhotoPrincipale(Image photoPrincipale) {
		this.photoPrincipale = photoPrincipale;
	}

	@Override
	public Image getPhotoPrincipale() {
		return photoPrincipale;
	}

	@Override
	public ScrollPanel getFavorisPanel() {
		// TODO Auto-generated method stub
		return favorisPanel;
	}

	public HTMLPanel getHtmlPanel() {
		return htmlPanel;
	}

	@Override
	public Label getNom() {
		return nom;
	}

	@Override
	public Label getPrenom() {
		return prenom;
	}

	@Override
	public Label getAge() {
		return age;
	}

	@Override
	public Label getSexe() {
		return sexe;
	}

	@Override
	public Label getNombreEnfants() {
		// TODO Auto-generated method stub
		return nombreEnfants;
	}

	@Override
	public Label getDivorce() {
		// TODO Auto-generated method stub
		return divorce;
	}

	@Override
	public Label getMarie() {
		// TODO Auto-generated method stub
		return marie;
	}

	@Override
	public Label getVeuf() {
		// TODO Auto-generated method stub
		return veuf;
	}

	@Override
	public Label getGenre() {
		// TODO Auto-generated method stub
		return genre;
	}

	@Override
	public Label getRelationSerieuse() {
		// TODO Auto-generated method stub
		return relationSerieuse;
	}

	@Override
	public Label getTaille() {
		return taille;
	}

	@Override
	public Label getPoids() {
		return poids;
	}

	@Override
	public Label getCouleurCheveux() {
		return cheveux;
	}

	@Override
	public Label getCouleurYeux() {
		return yeux;
	}

	@Override
	public FlowPanel getRaces() {
		return races;
	}

	@Override
	public FlowPanel getInterets() {
		return interets;
	}

	@Override
	public FlowPanel getMusiques() {
		return musiques;
	}

	@Override
	public FlowPanel getSports() {
		return sports;
	}

	@Override
	public FlowPanel getReligions() {
		return religions;
	}

	@Override
	public FlowPanel getProfessions() {
		return professions;
	}

	@Override
	public FlowPanel getCaracteres() {
		return caracteres;
	}
}