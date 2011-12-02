package ch.amaba.client.view.upload;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import ch.amaba.client.view.upload.state.UploadProgressState;
import ch.amaba.shared.upload.FileDto;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class FileList extends Composite {

	private final FlexTable filesTable;

	public FileList() {

		filesTable = new FlexTable();
		filesTable.getRowFormatter().addStyleName(0, "FileListHead");

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

			final List<FileDto> files = (List<FileDto>) event.getNewValue();

			filesTable.clear(true);

			filesTable.setText(0, 0, "Upload File");
			filesTable.setText(0, 1, "Upload Date");

			for (int i = 0; i < files.size(); i++) {
				final FileDto file = files.get(i);
				final String fileName = file.getFilename();

				final Anchor anchor = new Anchor(fileName, "amaba/download?file=" + fileName);

				final int row = i + 1;
				filesTable.setWidget(row, 0, anchor);
				filesTable.setText(row, 1, DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_SHORT).format(file.getDateUploaded()));
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
