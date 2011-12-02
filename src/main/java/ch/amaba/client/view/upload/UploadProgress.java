package ch.amaba.client.view.upload;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import ch.amaba.client.view.upload.state.UploadProgressState;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class UploadProgress extends Composite {

	private final Panel panel;
	private final Map<String, UploadPanel> uploads;

	public UploadProgress() {

		panel = new VerticalPanel();
		panel.setStyleName("UploadProgress");
		uploads = new HashMap<String, UploadPanel>();

		initWidget(panel);

		UploadProgressState.INSTANCE.addPropertyChangeListener("uploadProgress", new UploadProgressListener());
	}

	private final class UploadProgressListener implements PropertyChangeListener {

		private static final int COMPLETE_PERECENTAGE = 100;
		private static final int REMOVE_DELAY = 3000;

		@Override
		public void propertyChange(final PropertyChangeEvent event) {

			final Map<String, Integer> uploadPercentage = (Map<String, Integer>) event.getNewValue();

			for (final Map.Entry<String, Integer> entry : uploadPercentage.entrySet()) {
				final String file = entry.getKey();
				final Integer percentage = entry.getValue();

				final UploadPanel uploadPanel;
				if (!uploads.containsKey(file)) {
					uploadPanel = new UploadPanel(file);
					uploads.put(file, uploadPanel);
					panel.add(uploadPanel);
				} else {
					uploadPanel = uploads.get(file);
				}

				uploadPanel.update(percentage);

				if (percentage == UploadProgressListener.COMPLETE_PERECENTAGE) {
					final Timer timer = new Timer() {

						@Override
						public void run() {
							panel.remove(uploadPanel);
						}
					};
					timer.schedule(UploadProgressListener.REMOVE_DELAY);
				}
			}
		}
	}

	private static final class UploadPanel extends HorizontalPanel {

		private final ProgressBar bar;
		private final Label label;

		public UploadPanel(final String file) {

			setStyleName("UploadPanel");

			bar = new ProgressBar();
			label = new Label(file);

			add(bar);
			add(label);
		}

		public void update(final int percentage) {
			bar.update(percentage);
		}
	}
}
