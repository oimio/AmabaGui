package ch.amaba.client;

import ch.amaba.client.presenter.ConfidentialitePagePresenter;
import ch.amaba.client.presenter.DockLayoutPagePresenter;
import ch.amaba.client.presenter.HitListPresenter;
import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.ModifierDonneesPagePresenter;
import ch.amaba.client.presenter.RechercheDetailleePagePresenter;
import ch.amaba.client.presenter.ResponsePresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

/**
 * @author ROG
 */
@GinModules({ DispatchAsyncModule.class, MyModule.class })
public interface MyGinjector extends Ginjector {
	EventBus getEventBus();

	Provider<LoginPagePresenter> getMainPagePresenter();

	PlaceManager getPlaceManager();

	/**
	 * ROG : attention à bien caster du même type que ResponsePresenter, ici
	 * <b>AsyncProvider</b>.
	 * */
	AsyncProvider<ResponsePresenter> getResponsePresenter();

	/**
	 * ROG : attention à bien caster du même type que DockLayoutPagePresenter, ici
	 * <b>StandardProvider</b>.
	 * */
	Provider<DockLayoutPagePresenter> getDockLayoutPagePresenter();

	Provider<RechercheDetailleePagePresenter> getRechercheDetailleePresenter();

	Provider<HitListPresenter> getHitListPresenter();

	Provider<ModifierDonneesPagePresenter> getModifierDonneesPresenter();

	Provider<ConfidentialitePagePresenter> getConfidentialitePresenter();

}