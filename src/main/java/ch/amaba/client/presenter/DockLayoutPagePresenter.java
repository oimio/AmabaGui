package ch.amaba.client.presenter;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.impl.AfficherFavoris;
import ch.amaba.shared.LoadFullUserAction;
import ch.amaba.shared.LoadFullUserResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * @author ROG
 */
public class DockLayoutPagePresenter extends Presenter<DockLayoutPagePresenter.MyView, DockLayoutPagePresenter.MyProxy> {
	/**
	 * {@link LoginPagePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(DockLayoutPagePresenter.nameToken)
	public interface MyProxy extends Proxy<DockLayoutPagePresenter>, Place {
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
		Button getSendButton();

		Label getRechercheDetaillee();

		Label getBodyLabel();

		Label getModifierDonneesLabel();

		Label getConfidentialiteLabel();

		ScrollPanel getFavorisPanel();
	}

	public static final String nameToken = "dock";

	private final PlaceManager placeManager;
	private final DispatchAsync dispatcher;

	@Inject
	public DockLayoutPagePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().getRechercheDetaillee().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// RevealContentEvent.fire(DockLayoutPagePresenter.this,
				// DockLayoutPagePresenter.TYPE_SetMainContent,
				// DockLayoutPagePresenter.this);
				// getView().getBodyLabel().setText("Recherche d�taill�e...........");
				final PlaceRequest myRequest = new PlaceRequest(NameTokens.rechercheDetaillee);
				placeManager.revealPlace(myRequest);

			}
		}));
		registerHandler(getView().getConfidentialiteLabel().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				final PlaceRequest myRequest = new PlaceRequest(NameTokens.confidentialite);
				placeManager.revealPlace(myRequest);
			}
		}));
		registerHandler(getView().getModifierDonneesLabel().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// final PlaceRequest myRequest = new
				// PlaceRequest(NameTokens.modifierDonnees);
				// placeManager.revealPlace(myRequest);
				dispatcher.execute(new LoadFullUserAction(false), new AsyncCallback<LoadFullUserResult>() {

					public void onFailure(Throwable caught) {
						// getView().setServerResponse("An error occured: " +
						// caught.getMessage());
						Window.alert(caught.getMessage());
					}

					public void onSuccess(LoadFullUserResult result) {
						// Après modification, mise à jour des données du user modifié.
						ContextUI.get().setUserCourant(result.getUserCriteria());
						final PlaceRequest myRequest = new PlaceRequest(NameTokens.modifierDonnees);
						placeManager.revealPlace(myRequest);

					}
				});

			}
		}));

	}

	@Override
	protected void onReset() {
		super.onReset();
		AfficherFavoris.process(dispatcher, getView());
	}

	@Override
	protected void revealInParent() {

		RevealRootContentEvent.fire(this, this);
	}

}