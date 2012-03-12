package ch.amaba.client.presenter;

import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.action.PremierContactClickEvent;
import ch.amaba.client.presenter.action.ProfileDetailleClickEvent;
import ch.amaba.client.ui.composite.ProfilPanel;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.UserCriteria;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
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
		System.out.println(this.getClass().getName() + " onBind(...) ");
		super.onBind();

	}

	@Override
	protected void onReset() {
		super.onReset();
		System.out.println("onReset(...) " + this.getClass().getName());
		/** Le résultat de la recherche. */
		final Set<UserCriteria> searchResult = ContextUI.get().getSearchResult();
		final FlexTable table = getView().getHitListFlowPanel();
		table.clear();
		if ((searchResult != null) && !searchResult.isEmpty()) {
			int row = 0;
			int col = 0;
			for (final UserCriteria userCriteria : searchResult) {
				final ProfilPanel pp = new ProfilPanel();
				pp.getImage().setUrl("amaba/download?id=" + userCriteria.getIdUser() + "&file=_" + userCriteria.getPhotoPrincipaleFileName());
				pp.getPrenom().setText(userCriteria.getPrenom());
				pp.getAge().setText(DateUtils.getAge(userCriteria.getDateNaissance()) + " ans");
				pp.getCanton().setText(CantonUtils.getCantonTraduction(userCriteria.getIdCantons()));
				pp.getAjouterImage().addClickHandler(new PremierContactClickEvent(dispatcher, userCriteria.getIdUser()));
				pp.getMessagePriveImage().addClickHandler(new PremierContactClickEvent(dispatcher, userCriteria.getIdUser()));
				// pp.getProfileDetailleImage().addClickHandler(new ClickHandler() {
				// @Override
				// public void onClick(ClickEvent event) {
				// ContextUI.get().setProfileDetailleId(userCriteria.getIdUser());
				// final PlaceRequest myRequest = new
				// PlaceRequest(NameTokens.profileDetaille);
				// placeManager.revealPlace(myRequest);
				// }
				// });

				pp.getInfosImage().addClickHandler(new ProfileDetailleClickEvent(placeManager, userCriteria.getIdUser()));
				table.setWidget(row, col, pp);
				col++;
				if (col % 6 == 0) {
					row++;
					col = 0;
				}
			}
		} else {
			table.setWidget(0, 0, new Label("Aucune personne ne correspond à vos recherches : supprimez des filtres de recherches"));
		}
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}
}