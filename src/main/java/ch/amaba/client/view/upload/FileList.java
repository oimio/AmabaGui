package ch.amaba.client.view.upload;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

import ch.amaba.client.ui.composite.PhotoPanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.client.view.upload.state.UploadProgressState;
import ch.amaba.model.bo.PhotoDTO;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class FileList extends Composite {

	private final FlexTable filesTable;

	public FileList() {

		filesTable = new FlexTable();
		filesTable.setWidth("200px");
		filesTable.addStyleName("table");
		// filesTable.getRowFormatter().addStyleName(0, "FileListHead");

		final Panel filesPanel = new VerticalPanel();
		filesPanel.setStyleName("FileList");
		filesPanel.add(filesTable);

		initWidget(filesPanel);

		UploadProgressState.INSTANCE.addPropertyChangeListener("page", new PageListener());
		UploadProgressState.INSTANCE.addPropertyChangeListener("files", new FilesListener());
	}

	@Override
	protected void onLoad() {
		ProgressController.INSTANCE.countFiles();
		ProgressController.INSTANCE.findFiles(UploadProgressState.INSTANCE.getPage(), UploadProgressState.INSTANCE.getPageSize());
	}

	private final class FilesListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent event) {

			final Set<PhotoDTO> files = (Set<PhotoDTO>) event.getNewValue();

			filesTable.clear(true);

			filesTable.setText(0, 0, "Upload File");
			filesTable.setText(0, 1, "Upload Date");
			int row = 0;
			int col = 0;
			for (final PhotoDTO photoDTO : files) {
				final PhotoPanel photoPanel = new PhotoPanel("amaba/download?file=_" + photoDTO.getFileName(), DateUtils.getDate(photoDTO.getDateUpload()),
				    photoDTO.getBusinessObjectId());
				filesTable.setWidget(row, col, photoPanel);
				col++;
				if (col % 5 == 0) {
					row++;
					col = 0;
				}
			}

		}
	}

	private static final class PageListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent event) {
			ProgressController.INSTANCE.findFiles(UploadProgressState.INSTANCE.getPage(), UploadProgressState.INSTANCE.getPageSize());
		}
	}
}
