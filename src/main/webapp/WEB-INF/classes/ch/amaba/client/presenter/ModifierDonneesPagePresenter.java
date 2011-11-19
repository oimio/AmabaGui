package ch.amaba.client.presenter;

import java.util.List;

import ch.amaba.client.IConstants;
import ch.amaba.client.NameTokens;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.utils.CantonUtils;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
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
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * Modifier les données personnelles du compte.
 * 
 * @author ROG
 */
public class ModifierDonneesPagePresenter extends Presenter<ModifierDonneesPagePresenter.MyView, ModifierDonneesPagePresenter.MyProxy> {
	/**
	 * {@link ModifierDonneesPagePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.modifierDonnees)
	public interface MyProxy extends Proxy<ModifierDonneesPagePresenter>, Place {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	/**
	 * {@link ModifierDonneesPagePresenter}'s view.
	 */
	public interface MyView extends View {

		TextBox getPasswordAncienTextBox();

		ListBox getCodeSexeListBox();

		TextBox getNomTextBox();

		TextBox getPrenomTextBox();

		TextBox getPasswordTextBox();

		TextBox getPasswordRepeatTextBox();

		TextBox getJourTextBox();

		TextBox getMoisTextBox();

		TextBox getAnneeTextBox();

		Button getModifierButton();

		void resetAndFocus();

		void setError(List<String> messages);

		CantonsListBoxPanel getCantonsListBoxPanel();

		VerticalPanel getErrorPanel();

	}

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	@Inject
	public ModifierDonneesPagePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().getModifierButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// DevenirMembre.devenirMembre(getView(), dispatcher);
			}
		}));
		getView().getCantonsListBoxPanel().getCantonsListBox().clear();
		CantonUtils.populate(getView().getCantonsListBoxPanel().getCantonsListBox(), IConstants.ENUM_TYPE_CANTON);
	}

	@Override
	protected void onReset() {
		super.onReset();
		getView().resetAndFocus();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}

	/**
	 * Send the name from the nameField to the server and wait for a response.
	 */
	private void sendNameToServer() {
		// First, we validate the input.

		// Then, we transmit it to the ResponsePresenter, which will do the server
		// call
		// placeManager.revealPlace(new
		// PlaceRequest(ResponsePresenter.nameToken).with(ResponsePresenter.textToServerParam,
		// textToServer));
	}
}