package ch.amaba.client.ui.composite;

import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.PhotoDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class PhotoPanel extends Composite {

	interface FavorisPanelUiBinder extends UiBinder<Widget, PhotoPanel> {
	}

	@UiField
	Image supprimerImage;

	@UiField
	Image flagPrincipaleImage;

	@UiField
	Label infosLabel;

	@UiField(provided = true)
	MyImage image;

	Long id;

	/** Full image affichée en pop-up. */
	Image full = null;

	private static FavorisPanelUiBinder uiBinder = GWT.create(FavorisPanelUiBinder.class);

	public PhotoPanel(final PhotoDTO photoDTO) {
		final String url = "amaba/download?file=_" + photoDTO.getFileName();
		image = new MyImage(url);
		final PopupPanel popupPanel = new PopupPanel(true);
		popupPanel.setGlassEnabled(true);
		image.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				full = new Image(url.replaceAll("_", ""));
				popupPanel.setWidget(full);
				popupPanel.show();
				popupPanel.center();
			}
		});
		initWidget(PhotoPanel.uiBinder.createAndBindUi(this));
		getFlagPrincipaleImage().setVisible(!photoDTO.isPrincipale());
		if (photoDTO.isPrincipale()) {
			setTitle("Photo prinpale de votre profil.");
		}
		infosLabel.setText(DateUtils.getDate(photoDTO.getDateUpload()));
		id = photoDTO.getBusinessObjectId();
	}

	public Image getSupprimerImage() {
		return supprimerImage;
	}

	public Image getFlagPrincipaleImage() {
		return flagPrincipaleImage;
	}

	public Label getInfosLabel() {
		return infosLabel;
	}

	public Image getImage() {
		return image;
	}

	public Long getId() {
		return id;
	}
}