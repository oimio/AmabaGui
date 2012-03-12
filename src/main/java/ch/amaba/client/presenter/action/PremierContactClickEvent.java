package ch.amaba.client.presenter.action;

import ch.amaba.shared.EnvoyerMessagesAction;
import ch.amaba.shared.EnvoyerMessagesResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;

/**
 * Handler sur l'action d'envoyer une première prise de contact avec une
 * personne.
 * */
public class PremierContactClickEvent implements ClickHandler {

	private final Long idUser;
	private final DispatchAsync dispatcher;

	public PremierContactClickEvent(final DispatchAsync dispatcher, final Long idUser) {
		this.dispatcher = dispatcher;
		this.idUser = idUser;
	}

	@Override
	public void onClick(ClickEvent event) {
		dispatcher.execute(new EnvoyerMessagesAction(idUser), new AsyncCallback<EnvoyerMessagesResult>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(EnvoyerMessagesResult result) {
				Window.alert("Votre prise de contact a bien été envoyée.");
			}
		});
	}
}