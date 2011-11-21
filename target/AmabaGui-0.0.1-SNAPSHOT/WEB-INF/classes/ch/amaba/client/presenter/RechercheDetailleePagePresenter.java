package ch.amaba.client.presenter;

import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.server.shared.LoadCantonsAction;
import ch.amaba.server.shared.LoadCantonsResult;
import ch.amaba.server.shared.RechercheDetailleeAction;
import ch.amaba.server.shared.RechercheDetailleeResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

/**
 * @author ROG
 */
public class RechercheDetailleePagePresenter extends Presenter<RechercheDetailleePagePresenter.MyView, RechercheDetailleePagePresenter.MyProxy> {
	/**
	 * {@link RechercheDetailleePagePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.rechercheDetaillee)
	public interface MyProxy extends ProxyPlace<RechercheDetailleePagePresenter> {
	}

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	/**
	 * {@link RechercheDetailleePagePresenter}'s view.
	 */
	public interface MyView extends View {
		ListBox getReligionListeBox();

		Button getRechercheDetailleeButton();

		CantonsListBoxPanel getCantonListBoxPanel();

		/** Les données du formulaire. */
		UserCriteria getRecherche();
	}

	@Inject
	public RechercheDetailleePagePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy, final PlaceManager placeManager,
	    final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;

	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().getRechercheDetailleeButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				getView().getRechercheDetailleeButton().setEnabled(false);
				dispatcher.execute(new RechercheDetailleeAction(getView().getRecherche()), new AsyncCallback<RechercheDetailleeResult>() {

					public void onFailure(Throwable caught) {
						// getView().setServerResponse("An error occured: " +
						// caught.getMessage());
						getView().getRechercheDetailleeButton().setEnabled(true);
					}

					public void onSuccess(RechercheDetailleeResult result) {
						getView().getRechercheDetailleeButton().setEnabled(true);
						// Sauvegarde du résultat de la recherche
						ContextUI.get().setSearchResult(result.getUserCriterias());
						final PlaceRequest myRequest = new PlaceRequest(NameTokens.hitList);
						placeManager.revealPlace(myRequest);
					}
				});

			}
		});
		if (Cache.getCantons() == null) {
			dispatcher.execute(new LoadCantonsAction(false), new AsyncCallback<LoadCantonsResult>() {

				public void onFailure(Throwable caught) {
					// getView().setServerResponse("An error occured: " +
					// caught.getMessage());
				}

				public void onSuccess(LoadCantonsResult result) {
					final Set<CantonDTO> cantons = result.getCantons();
					loadCantonListBox(cantons);
					Cache.setCantons(cantons);
				}
			});
		} else {
			getView().getCantonListBoxPanel().getCantonsListBox().clear();
			CantonUtils.populate(getView().getCantonListBoxPanel().getCantonsListBox());
		}
	}

	private void loadCantonListBox(final Set<CantonDTO> cantons) {
		getView().getCantonListBoxPanel().getCantonsListBox().clear();
		for (final CantonDTO cantonDTO : cantons) {
			getView().getCantonListBoxPanel().getCantonsListBox().addItem(cantonDTO.getCodeCanton(), Long.toString(cantonDTO.getBusinessObjectId()));
		}
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}
}
