package ch.amaba.client.presenter;

import java.util.Set;

import ch.amaba.client.IConstants;
import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.ui.composite.CantonsListBoxPanel;
import ch.amaba.client.utils.CacheUtils;
import ch.amaba.client.utils.CantonUtils;
import ch.amaba.client.utils.ListBoxUtils;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeCaractereEnum;
import ch.amaba.model.bo.constants.TypeInteretEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeProfessionEnum;
import ch.amaba.model.bo.constants.TypeReligionEnum;
import ch.amaba.model.bo.constants.TypeSportEnum;
import ch.amaba.shared.LoadCantonsAction;
import ch.amaba.shared.LoadCantonsResult;
import ch.amaba.shared.RechercheDetailleeAction;
import ch.amaba.shared.RechercheDetailleeResult;

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

		Button getRechercheDetailleeButton();

		CantonsListBoxPanel getCantonListBoxPanel();

		/** Les données du formulaire. */
		UserCriteria getRecherche();

		ListBox getInteretListBox();

		ListBox getMusicListBox();

		ListBox getProfessionListBox();

		ListBox getReligionListBox();

		ListBox getSportListBox();

		ListBox getCaractereListBox();

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

			@Override
			public void onClick(ClickEvent event) {
				getView().getRechercheDetailleeButton().setEnabled(false);
				dispatcher.execute(new RechercheDetailleeAction(getView().getRecherche()), new AsyncCallback<RechercheDetailleeResult>() {

					@Override
					public void onFailure(Throwable caught) {
						// getView().setServerResponse("An error occured: " +
						// caught.getMessage());
						getView().getRechercheDetailleeButton().setEnabled(true);
					}

					@Override
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
		if (CacheUtils.getCantons() == null) {
			dispatcher.execute(new LoadCantonsAction(false), new AsyncCallback<LoadCantonsResult>() {

				@Override
				public void onFailure(Throwable caught) {
					// getView().setServerResponse("An error occured: " +
					// caught.getMessage());
				}

				@Override
				public void onSuccess(LoadCantonsResult result) {
					final Set<CantonDTO> cantons = result.getCantons();
					CacheUtils.setCantons(cantons);
				}
			});
		} else {
			getView().getCantonListBoxPanel().getCantonsListBox().clear();
		}
		CantonUtils.populate(getView().getCantonListBoxPanel().getCantonsListBox(), IConstants.ENUM_TYPE_CANTON);
		ListBoxUtils.populateAvecTraduction(getView().getInteretListBox(), TypeInteretEnum.class, IConstants.ENUM_TYPE_INTERET);
		ListBoxUtils.populateAvecTraduction(getView().getMusicListBox(), TypeMusiqueEnum.class, IConstants.ENUM_TYPE_MUSIC);
		ListBoxUtils.populateAvecTraduction(getView().getProfessionListBox(), TypeProfessionEnum.class, IConstants.ENUM_TYPE_PROFESSION);
		ListBoxUtils.populateAvecTraduction(getView().getReligionListBox(), TypeReligionEnum.class, IConstants.ENUM_TYPE_RELIGION);
		ListBoxUtils.populateAvecTraduction(getView().getSportListBox(), TypeSportEnum.class, IConstants.ENUM_TYPE_SPORT);
		ListBoxUtils.populateAvecTraduction(getView().getCaractereListBox(), TypeCaractereEnum.class, IConstants.ENUM_TYPE_CARACTERE);
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
