package ch.amaba.client.presenter.impl;

import java.util.Set;

import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.DockLayoutPagePresenter;
import ch.amaba.client.ui.composite.FavorisPanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.shared.ListeFavorisAction;
import ch.amaba.shared.ListeFavorisResult;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.gwtplatform.dispatch.shared.DispatchAsync;

public class AfficherFavoris {

	public static void process(DispatchAsync dispatcher, final DockLayoutPagePresenter.MyView view) {
		dispatcher.execute(new ListeFavorisAction(ContextUI.get().getUserCourant().getIdUser()), new AsyncCallback<ListeFavorisResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// getView().setServerResponse("An error occured: " +
				// caught.getMessage());
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ListeFavorisResult result) {
				final ScrollPanel favorisPanel = view.getFavorisPanel();
				final FlexTable t = new FlexTable();
				favorisPanel.setWidget(t);
				int row = 0;
				int col = 0;
				final Set<UserCriteria> listeFavoris = result.getListeFavoris();
				for (final UserCriteria userCriteria : listeFavoris) {
					final FavorisPanel userPanel = new FavorisPanel(userCriteria.getPrenom(), DateUtils.getAge(userCriteria.getDateNaissance()), userCriteria
					    .getIdCantons().iterator().next(), Long.toString(userCriteria.getIdUser()), null);
					t.setWidget(row, col, userPanel);
					col++;
					if ((col) % 2 == 0) {
						col = 0;
						row++;
					}
				}
			}
		});
	}
}
