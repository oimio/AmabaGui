package ch.amaba.client.presenter;

import ch.amaba.client.NameTokens;
import ch.amaba.client.presenter.impl.AfficherFavoris;
import ch.amaba.client.presenter.impl.ProfileDetailleImpl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
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
public class ProfileDetaillePresenter extends Presenter<ProfileDetaillePresenter.MyView, ProfileDetaillePresenter.MyProxy> {
	/**
	 * {@link ProfileDetaillePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.profileDetaille)
	public interface MyProxy extends Proxy<ProfileDetaillePresenter>, Place {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	/**
	 * {@link ProfileDetaillePresenter}'s view.
	 */
	public interface MyView extends IViewAmis {
		public Image getPhotoPrincipale();

		public Label getNom();

		public Label getPrenom();

		public Label getAge();

		public Label getSexe();

		public Label getNombreEnfants();

		public Label getDivorce();

		public Label getMarie();

		public Label getVeuf();

		public Label getGenre();

		public Label getRelationSerieuse();

		public Label getTaille();

		public Label getPoids();

		public Label getCouleurCheveux();

		public Label getCouleurYeux();

		public FlowPanel getRaces();

		public FlowPanel getCaracteres();

		public FlowPanel getInterets();

		public FlowPanel getMusiques();

		public FlowPanel getSports();

		public FlowPanel getReligions();

		public FlowPanel getProfessions();
	}

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	@Inject
	public ProfileDetaillePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();

		// getView().getSupprimerButton().addClickHandler(new
		// ChangeMessageStatutHandler(dispatcher, getSelectionIds(), getView(),
		// TypeMessageStatutEnum.SUPPRIME));
	}

	@Override
	protected void onReset() {
		super.onReset();
		ProfileDetailleImpl.process(dispatcher, getView());
		// TODO le profile détaillé du user courant : à améliorer !!!
		// ContextUI.get().setProfileDetailleId(ContextUI.get().getUserCourant().getIdUser());
		AfficherFavoris.process(dispatcher, getView(), placeManager, 5);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}

}