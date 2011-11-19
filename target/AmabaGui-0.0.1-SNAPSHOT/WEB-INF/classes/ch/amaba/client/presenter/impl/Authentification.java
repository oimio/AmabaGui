package ch.amaba.client.presenter.impl;

import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.DockLayoutPagePresenter;
import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.server.shared.AuthentificationAction;
import ch.amaba.server.shared.AuthentificationResult;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

/**
 * Process d'authentification.
 * */
public class Authentification {

	public static void authentification(final DispatchAsync dispatcher, LoginPagePresenter.MyView view, final PlaceManager placeManager) {
		view.getSendButton().setEnabled(false);
		dispatcher.execute(new AuthentificationAction(view.getEmailAuthTextBox().getText(), view.getPasswordAuthTextBox().getText()),
		    new AsyncCallback<AuthentificationResult>() {

			    public void onFailure(Throwable caught) {
				    Window.alert("Login failed: " + caught.getMessage());
			    }

			    public void onSuccess(AuthentificationResult result) {
				    ContextUI.get().setUserCourant(result.getUserCriteria());
				    final PlaceRequest myRequest = new PlaceRequest(DockLayoutPagePresenter.nameToken);
				    placeManager.revealPlace(myRequest);
			    }
		    });

	}

}
