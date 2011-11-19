package ch.amaba.client.ui.composite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * ListBox des sports
 * 
 * @author ROG
 */
public class ProfilPanel extends Composite {
	interface ProfilPanelUiBinder extends UiBinder<Widget, ProfilPanel> {
	}

	private static ProfilPanelUiBinder uiBinder = GWT.create(ProfilPanelUiBinder.class);

	@UiField
	Image image;

	@UiField
	Label prenom;

	@UiField
	Label age;

	@UiField
	Label canton;

	@UiField
	Image messagePriveImage;

	@UiField
	Image infosImage;

	@UiField
	Image ajouterImage;

	public ProfilPanel() {
		initWidget(ProfilPanel.uiBinder.createAndBindUi(this));
	}

	public Image getImage() {
		return image;
	}

	public Label getPrenom() {
		return prenom;
	}

	public Label getAge() {
		return age;
	}

	public Label getCanton() {
		return canton;
	}

	public Image getMessagePriveImage() {
		return messagePriveImage;
	}

	public Image getInfosImage() {
		return infosImage;
	}

	public Image getAjouterImage() {
		return ajouterImage;
	}

}