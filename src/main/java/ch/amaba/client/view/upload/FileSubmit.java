package ch.amaba.client.view.upload;

import ch.amaba.client.view.upload.state.UploadProgressState;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Panel de sélection et submit d'une photo.
 * */
public final class FileSubmit extends Composite {

	/**
	 * <b>IMPORTANT</b><br/>
	 * 
	 * URL pour la servlet upload.
	 * 
	 * 'amaba' : voir Amaba.gwt.xml, <b>module-rename-to</b>.
	 * */
	private static final String URL = "amaba/upload";
	private final Button submit;
	private final FileUpload file;
	private final FormPanel form;

	public FileSubmit() {
		file = new FileUpload();
		file.setName("file");
		file.setTitle("Sélectionnez une photo");
		file.setWidth("250px");

		submit = new Button("Ajouter");
		submit.setTitle("Ajoutez une photo");

		final HorizontalPanel uploadPanel = new HorizontalPanel();
		uploadPanel.setSpacing(10);
		uploadPanel.setStyleName("FileSubmit");
		uploadPanel.add(file);
		uploadPanel.add(submit);

		form = new FormPanel();
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.setAction(FileSubmit.URL);
		form.setWidget(uploadPanel);

		initWidget(form);

		submit.addClickHandler(new SubmitClickHandler());
		form.addSubmitHandler(new FormSubmitHandler());
		form.addSubmitCompleteHandler(new FormSubmitCompleteHandler());
	}

	@Override
	protected void onLoad() {
		ProgressController.INSTANCE.initialise();
	}

	/**
	 * Handler pour désactiver le bouton submit.
	 * */
	private class FormSubmitHandler implements SubmitHandler {
		@Override
		public void onSubmit(final SubmitEvent event) {
			submit.setEnabled(false);
		}
	}

	/**
	 * Handler après le submit.
	 * */
	private class FormSubmitCompleteHandler implements SubmitCompleteHandler {
		@Override
		public void onSubmitComplete(final SubmitCompleteEvent event) {
			form.reset();
			submit.setEnabled(true);
			ProgressController.INSTANCE.countFiles();
			ProgressController.INSTANCE.findFiles(UploadProgressState.INSTANCE.getPage(), UploadProgressState.INSTANCE.getPageSize());
		}
	}

	/**
	 * Handler sur le click pour déclencher le submit.
	 * */
	private final class SubmitClickHandler implements ClickHandler {
		@Override
		public void onClick(final ClickEvent event) {
			final String filename = file.getFilename();

			if (filename.isEmpty()) {
				Window.alert("Selectionnez une photo");
				return;
			}
			form.submit();
		}
	}
}
