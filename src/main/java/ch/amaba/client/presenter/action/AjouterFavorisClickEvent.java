package ch.amaba.client.presenter.action;

import ch.amaba.shared.AjouterFavorisAction;
import ch.amaba.shared.AjouterFavorisResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;

public class AjouterFavorisClickEvent implements ClickHandler {

	private final Long idUser;
	private final DispatchAsync dispatcher;

	public AjouterFavorisClickEvent(DispatchAsync dispatcher, Long idUser) {
		this.dispatcher = dispatcher;
		this.idUser = idUser;
	}

	@Override
	public void onClick(ClickEvent event) {
		dispatcher.execute(new AjouterFavorisAction(idUser), new AsyncCallback<AjouterFavorisResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(AjouterFavorisResult result) {
				Window.alert("Vous pourrez d�sormais retrouver facilement ce profil dans votre liste de favoris.");
			}
		});
	}
}