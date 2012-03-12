package ch.amaba.client.presenter.impl;

import java.util.Set;

import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.IViewAmis;
import ch.amaba.client.presenter.action.PremierContactClickEvent;
import ch.amaba.client.presenter.action.ProfileDetailleClickEvent;
import ch.amaba.client.ui.composite.FavorisPanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.AmiDTO;
import ch.amaba.shared.ListeFavorisAction;
import ch.amaba.shared.ListeFavorisResult;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class AfficherFavoris {

	public static void process(final DispatchAsync dispatcher, final IViewAmis view, final PlaceManager placeManager, final int modulo) {
		dispatcher.execute(new ListeFavorisAction(ContextUI.get().getProfileDetailleId()), new AsyncCallback<ListeFavorisResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// getView().setServerResponse("An error occured: " +
				// caught.getMessage());
				caught.printStackTrace();
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ListeFavorisResult result) {
				final ScrollPanel favorisPanel = view.getFavorisPanel();
				final FlexTable t = new FlexTable();
				favorisPanel.setWidget(t);
				int row = 0;
				int col = 0;
				final Set<AmiDTO> listeFavoris = result.getListeFavoris();
				for (final AmiDTO userCriteria : listeFavoris) {
					final FavorisPanel userPanel = new FavorisPanel(userCriteria.getPrenom(),

					DateUtils.getAge(userCriteria.getDateNaissance()),

					userCriteria.getIdCantons().iterator().next(),

					Long.toString(userCriteria.getBusinessObjectId()),

					userCriteria.getPhotoPrincipaleFileName());
					userPanel.getMessageImage().addClickHandler(new PremierContactClickEvent(dispatcher, userCriteria.getBusinessObjectId()));
					userPanel.getProfileDetailleImage().addClickHandler(new ProfileDetailleClickEvent(placeManager, userCriteria.getBusinessObjectId()));
					t.setWidget(row, col, userPanel);
					col++;
					if ((col) % modulo == 0) {
						col = 0;
						row++;
					}
				}
			}
		});
	}
}
