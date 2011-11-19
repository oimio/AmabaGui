/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ch.amaba.client.presenter;

import ch.amaba.client.NameTokens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * @author ROG
 */
public class ResponsePresenter extends Presenter<ResponsePresenter.MyView, ResponsePresenter.MyProxy> {
	/**
	 * {@link ResponsePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(ResponsePresenter.nameToken)
	public interface MyProxy extends Proxy<ResponsePresenter>, Place {
	}

	/**
	 * {@link ResponsePresenter}'s view.
	 */
	public interface MyView extends View {
		Button getCloseButton();

		void setServerResponse(String serverResponse);

		void setTextToServer(String textToServer);
	}

	public static final String nameToken = "response";

	public static final String textToServerParam = "textToServer";

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	private String textToServer;

	@Inject
	public ResponsePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		textToServer = request.getParameter(ResponsePresenter.textToServerParam, null);
	}

	
	protected void onBind() {
		super.onBind();
		registerHandler(getView().getCloseButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				placeManager.revealPlace(new PlaceRequest(NameTokens.login));
			}
		}));
	}

	
	protected void onReset() {
		super.onReset();
		getView().setTextToServer(textToServer);
		getView().setServerResponse("Waiting for response...");

	}

	
	protected void revealInParent() {
		final PlaceRequest myRequest = new PlaceRequest(DockLayoutPagePresenter.nameToken);
		// If needed, add URL parameters in this way:
		// myRequest = myRequest.with( "key1", "param1" ).with( "key2", "param2" );
		placeManager.revealPlace(myRequest);

		// RevealRootLayoutContentEvent.fire(getEventBus(),
		// DockLayoutPagePresenter.TYPE_SetMainContent);

	}

	//
	// protected void revealInParent() {
	// RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent,
	// this);
	// // RevealRootContentEvent.fire(this, this);
	// // RevealContentEvent.fire(this, LoginPagePresenter.TYPE_SetMainContent,
	// // this);
	// }
}
