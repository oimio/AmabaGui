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

import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.ui.composite.ProfilPanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.UserCriteria;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

/**
 * @author ROG
 */
public class HitListPresenter extends Presenter<HitListPresenter.MyView, HitListPresenter.MyProxy> {
	/**
	 * {@link HitListPresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.hitList)
	public interface MyProxy extends ProxyPlace<HitListPresenter> {
	}

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	/**
	 * {@link HitListPresenter}'s view.
	 */
	public interface MyView extends View {
		FlexTable getHitListFlowPanel();
	}

	@Inject
	public HitListPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy, final PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;

	}

	@Override
	protected void onBind() {
		super.onBind();
		// getView().getRechercheDetailleeButton().addClickHandler(new
		// ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// getView().getRechercheDetailleeButton().setEnabled(false);
		//
		// }
		// });
	}

	@Override
	protected void onReset() {
		super.onReset();

		/** Le résultat de la recherche. */
		final Set<UserCriteria> searchResult = ContextUI.get().getSearchResult();

		final FlexTable table = getView().getHitListFlowPanel();
		int row = 0;
		int col = 0;
		for (final UserCriteria userCriteria : searchResult) {
			// final HorizontalPanel horizontalPanel = new HorizontalPanel();
			// horizontalPanel.setSize("180px", "110px");
			// horizontalPanel.setBorderWidth(1);
			// horizontalPanel.add(new Image("images/012.jpg"));
			final ProfilPanel pp = new ProfilPanel();
			pp.getImage().setUrl("images/012.jpg");
			pp.getPrenomAge().setText(userCriteria.getPrenom() + ", " + DateUtils.getAge(userCriteria.getDateNaissance()));
			table.setWidget(row, col, pp);
			col++;
			if (col % 6 == 0) {
				row++;
				col = 0;
			}
		}

		// table.add(new Label("teee"));
		// table.getCellFormatter().getElement(0, 0).setInnerText("hello");

		// dispatcher.execute(new SendTextToServer("tototo"), new
		// AsyncCallback<SendTextToServerResult>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// // getView().setServerResponse("An error occured: " +
		// // caught.getMessage());
		// }
		//
		// @Override
		// public void onSuccess(SendTextToServerResult result) {
		// getView().getReligionListeBox().addItem("Nouvelle religion");
		// }
		// });
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}
}