package ch.amaba.client.presenter.action;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

/**
 * Handler sur les liens sur les messages (envoyes, recus, supprimes).
 * */
public class AfficherMessagesClickEvent implements ClickHandler {

	private final PlaceManager placeManager;
	private final TypeMessageStatutEnum typeMessageStatutEnum;

	public AfficherMessagesClickEvent(final PlaceManager placeManager, final TypeMessageStatutEnum typeMessageStatutEnum) {
		this.placeManager = placeManager;
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

	@Override
	public void onClick(ClickEvent event) {
		ContextUI.get().setMessageAction(typeMessageStatutEnum);
		final PlaceRequest myRequest = new PlaceRequest(NameTokens.messages);
		placeManager.revealPlace(myRequest);
	}
}
