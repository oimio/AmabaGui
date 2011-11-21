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

import java.util.List;
import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.presenter.impl.Authentification;
import ch.amaba.client.presenter.impl.DevenirMembre;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.server.shared.LoadCantonsAction;
import ch.amaba.server.shared.LoadCantonsResult;
import ch.amaba.server.shared.LoadTraductionsAction;
import ch.amaba.server.shared.LoadTraductionsResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * @author ROG
 */
public class LoginPagePresenter extends Presenter<LoginPagePresenter.MyView, LoginPagePresenter.MyProxy> {
	/**
	 * {@link LoginPagePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.login)
	public interface MyProxy extends Proxy<LoginPagePresenter>, Place {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	/**
	 * {@link LoginPagePresenter}'s view.
	 */
	public interface MyView extends View {

		TextBox getEmailAuthTextBox();

		TextBox getPasswordAuthTextBox();

		ListBox getGenreListBox();

		TextBox getNomTextBox();

		TextBox getPrenomTextBox();

		TextBox getEmailTextBox();

		TextBox getEmailRepeatTextBox();

		TextBox getPasswordTextBox();

		TextBox getPasswordRepeatTextBox();

		TextBox getJourTextBox();

		TextBox getMoisTextBox();

		TextBox getAnneeTextBox();

		Button getSendButton();

		void resetAndFocus();

		void setError(List<String> messages);

		ListBox getCantonListBox();

		Button getDevenirMembreButton();

		VerticalPanel getErrorPanel();

	}

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	@Inject
	public LoginPagePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();

		registerHandler(getView().getDevenirMembreButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				DevenirMembre.devenirMembre(getView(), dispatcher);
			}
		}));
		registerHandler(getView().getSendButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Authentification.authentification(dispatcher, getView(), placeManager);
			}
		}));
		registerHandler(getView().getEmailTextBox().addFocusHandler(new FocusHandler() {

			public void onFocus(FocusEvent event) {
				if (getView().getEmailTextBox().getText().indexOf("@") == -1) {
					getView().getEmailTextBox().setText("");
				}
			}
		}));
		if (Cache.getTraductions() == null) {
			dispatcher.execute(new LoadTraductionsAction(false), new AsyncCallback<LoadTraductionsResult>() {

				public void onFailure(Throwable caught) {
					// getView().setServerResponse("An error occured: " +
					// caught.getMessage());
				}

				public void onSuccess(LoadTraductionsResult result) {
					Cache.setTraductions(result.getTraductions());
					loadCantons();
				}
			});
		}

	}

	private void loadCantons() {
		if (Cache.getCantons() == null) {
			dispatcher.execute(new LoadCantonsAction(false), new AsyncCallback<LoadCantonsResult>() {

				public void onFailure(Throwable caught) {
					// getView().setServerResponse("An error occured: " +
					// caught.getMessage());
				}

				public void onSuccess(LoadCantonsResult result) {
					final Set<CantonDTO> cantons = result.getCantons();
					Cache.setCantons(cantons);
					getView().getCantonListBox().clear();
					CantonUtils.populate(getView().getCantonListBox());
				}
			});
		}
	}

	@Override
	protected void onReset() {
		super.onReset();
		getView().resetAndFocus();

	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
}