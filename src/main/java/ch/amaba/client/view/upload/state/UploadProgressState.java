package ch.amaba.client.view.upload.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.amaba.model.bo.PhotoDTO;

public final class UploadProgressState extends PageableState {

	public static final UploadProgressState INSTANCE = new UploadProgressState();
	private final Map<String, Integer> uploadProgress;
	private Set<PhotoDTO> files;

	private UploadProgressState() {
		uploadProgress = new HashMap<String, Integer>();
	}

	public Set<PhotoDTO> getFiles() {
		return files;
	}

	public void setFiles(final Set<PhotoDTO> files) {
		final Set<PhotoDTO> old = this.files;
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
