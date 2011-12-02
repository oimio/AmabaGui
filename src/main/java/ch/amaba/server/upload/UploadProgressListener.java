package ch.amaba.server.upload;

import org.apache.commons.fileupload.ProgressListener;

import ch.amaba.shared.upload.UploadProgressChangeEvent;

public final class UploadProgressListener implements ProgressListener {

	private static final double COMPLETE_PERECENTAGE = 100d;
	private int percentage = -1;
	private final String fileName;
	private final UploadProgress uploadProgress;

	public UploadProgressListener(final String fileName, final UploadProgress uploadProgress) {
		this.fileName = fileName;
		this.uploadProgress = uploadProgress;
	}

	@Override
	public void update(final long bytesRead, final long totalBytes, final int items) {
		final int percentage = (int) Math.floor(((double) bytesRead / (double) totalBytes) * UploadProgressListener.COMPLETE_PERECENTAGE);

		if (this.percentage == percentage) {
			return;
		}

		this.percentage = percentage;

		final UploadProgressChangeEvent event = new UploadProgressChangeEvent();
		event.setFilename(fileName);
		event.setPercentage(percentage);

		synchronized (uploadProgress) {
			uploadProgress.add(event);
			uploadProgress.notifyAll();
		}
	}
}
