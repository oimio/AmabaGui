package ch.amaba.client.view.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class UploadProgressView extends Composite {

	interface UploadProgressViewUiBinder extends UiBinder<Widget, UploadProgressView> {
	}

	private static UploadProgressViewUiBinder uiBinder = GWT.create(UploadProgressViewUiBinder.class);
	@UiField
	FileSubmit fileSubmit;
	@UiField
	UploadProgress uploadProgress;
	@UiField(provided = true)
	PhotoListPanel fileList;
	@UiField
	Paging paging;

	public UploadProgressView() {
		fileList = PhotoListPanel.get();
		initWidget(UploadProgressView.uiBinder.createAndBindUi(this));

	}
}
