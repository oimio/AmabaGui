package ch.amaba.client.presenter.action;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

public class ProfileDetailleClickEvent implements ClickHandler {
	private final PlaceManager placeManager;

	private final Long idUser;

	public ProfileDetailleClickEvent(final PlaceManager placeManager, final Long idUser) {
		this.idUser = idUser;
		this.placeManager = placeManager;
	}

	@Override
	public void onClick(ClickEvent event) {
		ContextUI.get().setProfileDetailleId(idUser);
		final PlaceRequest myRequest = new PlaceRequest(NameTokens.profileDetaille);
		placeManager.revealPlace(myRequest);

	}

}
