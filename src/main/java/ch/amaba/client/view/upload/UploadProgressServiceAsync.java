package ch.amaba.client.view.upload;

import java.util.List;
import java.util.Set;

import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.shared.upload.Event;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UploadProgressServiceAsync {

	void initialise(AsyncCallback<Void> asyncCallback);

	void countFiles(AsyncCallback<Integer> asyncCallback);

	void readFiles(int page, int pageSize, AsyncCallback<Set<PhotoDTO>> asyncCallback);

	void getEvents(AsyncCallback<List<Event>> asyncCallback);
}
