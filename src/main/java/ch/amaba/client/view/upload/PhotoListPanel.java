package ch.amaba.client.view.upload;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.amaba.client.ui.composite.PhotoPanel;
import ch.amaba.client.view.upload.state.UploadProgressState;
import ch.amaba.model.bo.PhotoDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public final class PhotoListPanel extends Composite {

	private final FlexTable photosTable;
	private static PhotoListPanel INSTANCE;
	/**
	 * Mapping idPhoto / widget (PhotoPanel)
	 * */
	private final Map<Long, Widget> registres = new HashMap<Long, Widget>();

	public static PhotoListPanel get() {
		if (PhotoListPanel.INSTANCE == null) {
			PhotoListPanel.INSTANCE = new PhotoListPanel();
		}
		return PhotoListPanel.INSTANCE;
	}

	private PhotoListPanel() {

		photosTable = new FlexTable();
		photosTable.setWidth("200px");
		photosTable.addStyleName("table");
		// filesTable.getRowFormatter().addStyleName(0, "FileListHead");

		final Panel filesPanel = new VerticalPanel();
		filesPanel.setStyleName("FileList");
		filesPanel.add(photosTable);

		initWidget(filesPanel);

		UploadProgressState.INSTANCE.addPropertyChangeListener("page", new PageListener());
		UploadProgressState.INSTANCE.addPropertyChangeListener("files", new FilesListener());
	}

	@Override
	protected void onLoad() {
		// ProgressController.INSTANCE.countFiles();
		ProgressController.INSTANCE.findFiles(UploadProgressState.INSTANCE.getPage(), UploadProgressState.INSTANCE.getPageSize());
	}

	private final class FilesListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent event) {

			final Set<PhotoDTO> files = (Set<PhotoDTO>) event.getNewValue();

			photosTable.clear(true);

			photosTable.setText(0, 0, "Upload File");
			photosTable.setText(0, 1, "Upload Date");
			int row = 0;
			int col = 0;
			for (final PhotoDTO photoDTO : files) {
				final PhotoPanel photoPanel = new PhotoPanel(photoDTO);
				photoPanel.getSupprimerImage().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (!photoDTO.isPrincipale()) {
							ProgressController.INSTANCE.supprimerPhoto(photoDTO.getBusinessObjectId());
						} else {
							Window
							    .alert("Vous ne pouvez pas supprimer la photo principale de votre profil. Définissez une autre photo de profil et revenez supprimer cette photo.");
						}
					}
				});
				photoPanel.getFlagPrincipaleImage().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						ProgressController.INSTANCE.flagPhotoAsPrincipale(photoDTO.getBusinessObjectId());
					}
				});
				if (photoDTO.isPrincipale()) {
					photoPanel.addStyleName("photoPrincipale");
				}
				photosTable.setWidget(row, col, photoPanel);
				registres.put(photoDTO.getBusinessObjectId(), photoPanel);
				col++;
				if (col % 5 == 0) {
					row++;
					col = 0;
				}
			}
		}
	}

	/** Supprimer du tableau la photo. */
	public void supprimerPhotoGUI(Long idPhoto) {
		final Widget widget = registres.get(idPhoto);
		if (widget != null) {
			photosTable.remove(widget);
			registres.remove(widget);
		}
	}

	private static final class PageListener implements PropertyChangeListener {
		@Override
		public void propertyChange(final PropertyChangeEvent event) {
			ProgressController.INSTANCE.findFiles(UploadProgressState.INSTANCE.getPage(), UploadProgressState.INSTANCE.getPageSize());
		}
	}
}
