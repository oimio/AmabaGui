package ch.amaba.client.view.upload;

import java.util.List;

import ch.amaba.client.view.upload.state.UploadProgressState;
import ch.amaba.shared.upload.Event;
import ch.amaba.shared.upload.FileDto;
import ch.amaba.shared.upload.UploadProgressChangeEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public final class ProgressController extends AbstractController {

	public static final ProgressController INSTANCE = new ProgressController();

	private ProgressController() {
	}

	public void findFiles(final int page, final int pageSize) {
		AbstractController.SERVICE.readFiles(page, pageSize, new AsyncCallback<List<FileDto>>() {

			@Override
			public void onFailure(final Throwable t) {
				GWT.log("error find files", t);
			}

			@Override
			public void onSuccess(final List<FileDto> files) {
				UploadProgressState.INSTANCE.setFiles(files);
			}
		});
	}

	private void getEvents() {

		AbstractController.SERVICE.getEvents(new AsyncCallback<List<Event>>() {

			@Override
			public void onFailure(final Throwable t) {
				GWT.log("error get events", t);
			}

			@Override
			public void onSuccess(final List<Event> events) {

				for (final Event event : events) {
					handleEvent(event);
				}
				AbstractController.SERVICE.getEvents(this);
			}

			private void handleEvent(final Event event) {

				if (event instanceof UploadProgressChangeEvent) {
					final UploadProgressChangeEvent uploadPercentChangeEvent = (UploadProgressChangeEvent) event;
					final String filename = uploadPercentChangeEvent.getFilename();
					final Integer percentage = uploadPercentChangeEvent.getPercentage();

					UploadProgressState.INSTANCE.setUploadProgress(filename, percentage);
				}
			}
		});
	}

	public void initialise() {
		AbstractController.SERVICE.initialise(new AsyncCallback<Void>() {

			@Override
			public void onFailure(final Throwable t) {
				GWT.log("error initialise", t);
			}

			@Override
			public void onSuccess(final Void result) {
				getEvents();
			}
		});
	}

	public void countFiles() {
		AbstractController.SERVICE.countFiles(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(final Throwable t) {
				GWT.log("error count files", t);
			}

			@Override
			public void onSuccess(final Integer result) {
				final int pageSize = UploadProgressState.INSTANCE.getPageSize();
				final int pages = (int) Math.ceil((double) result / (double) pageSize);
				UploadProgressState.INSTANCE.setPages(pages);
			}
		});
	}
}