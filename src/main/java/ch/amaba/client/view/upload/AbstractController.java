package ch.amaba.client.view.upload;

import com.google.gwt.core.client.GWT;

public abstract class AbstractController {

	protected static final UploadProgressServiceAsync SERVICE = GWT.create(UploadProgressService.class);
}
