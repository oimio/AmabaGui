package ch.amaba.client.view.upload;

import java.util.List;
import java.util.Set;

import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.HttpSessionRequiredException;
import ch.amaba.shared.upload.Event;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("uploadprogress")
public interface UploadProgressService extends RemoteService {

	void initialise();

	int countFiles();

	Set<PhotoDTO> readFiles(int page, int pageSize);

	List<Event> getEvents();

	Long supprimerPhoto(Long idPhoto) throws EntityNotFoundException;

	Set<PhotoDTO> flagPhotoAsPrincipale(Long idPhoto) throws HttpSessionRequiredException;
}
