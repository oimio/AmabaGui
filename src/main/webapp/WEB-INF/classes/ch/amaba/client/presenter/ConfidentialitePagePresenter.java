package ch.amaba.client.presenter;

import ch.amaba.client.NameTokens;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
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
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * Modifier les données personnelles du compte.
 * 
 * @author ROG
 */
public class ConfidentialitePagePresenter extends Presenter<ConfidentialitePagePresenter.MyView, ConfidentialitePagePresenter.MyProxy> {
	/**
	 * {@link ConfidentialitePagePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.confidentialite)
	public interface MyProxy extends Proxy<ConfidentialitePagePresenter>, Place {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	/**
	 * {@link ConfidentialitePagePresenter}'s view.
	 */
	public interface MyView extends View {

	}

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	@Inject
	public ConfidentialitePagePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	
	protected void onBind() {
		super.onBind();
		// registerHandler(getView().getModifierButton().addClickHandler(new
		// ClickHandler() {
		// 
		// public void onClick(ClickEvent event) {
		// // DevenirMembre.devenirMembre(getView(), dispatcher);
		// }
		// }));
	}

	
	protected void onReset() {
		super.onReset();

	}

	
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}

}