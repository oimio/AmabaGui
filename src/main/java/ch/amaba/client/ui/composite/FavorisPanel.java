package ch.amaba.client.ui.composite;

import ch.amaba.client.utils.CantonUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FavorisPanel extends Composite {

	interface FavorisPanelUiBinder extends UiBinder<Widget, FavorisPanel> {
	}

	@UiField
	Image fermerImage;

	@UiField
	Label prenomLabel;

	@UiField
	Label ageLabel;

	@UiField
	Label cantonLabel;

	@UiField(provided = true)
	MyImage photo;

	@UiField
	Image messageImage;

	@UiField
	Image profileDetailleImage;

	String id;

	private static FavorisPanelUiBinder uiBinder = GWT.create(FavorisPanelUiBinder.class);

	public FavorisPanel(final String prenom, Integer age, Integer idCanton, String id, final String imageUrl) {
		photo = new MyImage(imageUrl != null ? "amaba/download?id=" + id + "&file=_" + imageUrl : "images/no_photo.png");
		initWidget(FavorisPanel.uiBinder.createAndBindUi(this));
		prenomLabel.setText(prenom);
		ageLabel.setText(age + " ans");
		cantonLabel.setText(CantonUtils.getCantonTraduction(idCanton, true));
		this.id = id;
	}

	public Image getFermerImage() {
		return fermerImage;
	}

	public Label getPrenomLabel() {
		return prenomLabel;
	}

	public Label getAgeLabel() {
		return ageLabel;
	}

	public Label getCantonLabel() {
		return cantonLabel;
	}

	public Image getMessageImage() {
		return messageImage;
	}

	public Image getProfileDetailleImage() {
		return profileDetailleImage;
	}
}