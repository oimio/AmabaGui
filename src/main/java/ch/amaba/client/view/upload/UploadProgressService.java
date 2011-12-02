package ch.amaba.client.view.upload;

import java.util.List;

import ch.amaba.shared.upload.Event;
import ch.amaba.shared.upload.FileDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("uploadprogress")
public interface UploadProgressService extends RemoteService {

	void initialise();

	int countFiles();

	List<FileDto> readFiles(int page, int pageSize);

	List<Event> getEvents();
}
