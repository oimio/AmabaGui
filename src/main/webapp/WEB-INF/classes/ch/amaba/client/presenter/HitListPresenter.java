package ch.amaba.client.presenter;

import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.ui.composite.ProfilPanel;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.shared.AjouterFavorisAction;
import ch.amaba.shared.AjouterFavorisResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		//
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
		if ((searchResult != null) && !searchResult.isEmpty()) {
			final FlexTable table = getView().getHitListFlowPanel();
			int row = 0;
			int col = 0;
			for (final UserCriteria userCriteria : searchResult) {
				final ProfilPanel pp = new ProfilPanel();
				pp.getImage().setUrl("images/012.jpg");
				pp.getPrenom().setText(userCriteria.getPrenom());
				pp.getAge().setText(DateUtils.getAge(userCriteria.getDateNaissance()) + " ans");
				pp.getCanton().setText(CantonUtils.getCantonTraduction(userCriteria.getIdCantons()));
				pp.getAjouterImage().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dispatcher.execute(new AjouterFavorisAction(userCriteria.getIdUser()), new AsyncCallback<AjouterFavorisResult>() {

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
				});

				table.setWidget(row, col, pp);
				col++;
				if (col % 6 == 0) {
					row++;
					col = 0;
				}
			}
		}
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}
}