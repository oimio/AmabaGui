package ch.amaba.client.view.upload.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.amaba.shared.upload.FileDto;

public final class UploadProgressState extends PageableState {

	public static final UploadProgressState INSTANCE = new UploadProgressState();
	private final Map<String, Integer> uploadProgress;
	private List<FileDto> files;

	private UploadProgressState() {
		uploadProgress = new HashMap<String, Integer>();
	}

	public List<FileDto> getFiles() {
		return files;
	}

	public void setFiles(final List<FileDto> files) {
		final List<FileDto> old = this.files;
		this.files = files;
		firePropertyChange("files", old, files);
	}

	public Integer getUploadProgress(final String filename) {
		return uploadProgress.get(filename);
	}

	public void setUploadProgress(final String filename, final Integer percentage) {
		final Integer old = uploadProgress.get(filename);
		uploadProgress.put(filename, percentage);
		firePropertyChange("uploadProgress", old, uploadProgress);
	}
}
