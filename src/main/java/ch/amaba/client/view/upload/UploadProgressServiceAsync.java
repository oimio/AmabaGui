package ch.amaba.client.view.upload;

import java.util.List;

import ch.amaba.shared.upload.Event;
import ch.amaba.shared.upload.FileDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UploadProgressServiceAsync {

	void initialise(AsyncCallback<Void> asyncCallback);

	void countFiles(AsyncCallback<Integer> asyncCallback);

	void readFiles(int page, int pageSize, AsyncCallback<List<FileDto>> asyncCallback);

	void getEvents(AsyncCallback<List<Event>> asyncCallback);
}
