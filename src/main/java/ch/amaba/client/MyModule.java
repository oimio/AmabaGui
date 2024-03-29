package ch.amaba.client;

import ch.amaba.client.presenter.ConfidentialitePagePresenter;
import ch.amaba.client.presenter.DockLayoutPagePresenter;
import ch.amaba.client.presenter.HitListPresenter;
import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.MesPhotosPresenter;
import ch.amaba.client.presenter.MessagesPresenter;
import ch.amaba.client.presenter.ModifierDonneesPagePresenter;
import ch.amaba.client.presenter.ProfileDetaillePresenter;
import ch.amaba.client.presenter.RechercheDetailleePagePresenter;
import ch.amaba.client.presenter.ResponsePresenter;
import ch.amaba.client.ui.ConfidentialitePageView;
import ch.amaba.client.ui.LoginPageView;
import ch.amaba.client.ui.MesPhotosView;
import ch.amaba.client.ui.ModifierDonneesView;
import ch.amaba.client.ui.message.MessagesView;
import ch.amaba.client.ui.profile.ProfileDetailleView;
import ch.amaba.client.view.DockLayoutPageView;
import ch.amaba.client.view.HitListView;
import ch.amaba.client.view.RechercheDetailleeView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

/**
 * @author ROG
 */
public class MyModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// Default implementation of standard resources
		install(new DefaultModule(MyPlaceManager.class));

		// Presenters
		bindPresenter(LoginPagePresenter.class, LoginPagePresenter.MyView.class, LoginPageView.class, LoginPagePresenter.MyProxy.class);

		bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class, ResponseView.class, ResponsePresenter.MyProxy.class);
		bindPresenter(DockLayoutPagePresenter.class, DockLayoutPagePresenter.MyView.class, DockLayoutPageView.class, DockLayoutPagePresenter.MyProxy.class);
		bindPresenter(RechercheDetailleePagePresenter.class, RechercheDetailleePagePresenter.MyView.class, RechercheDetailleeView.class,
		    RechercheDetailleePagePresenter.MyProxy.class);
		bindPresenter(HitListPresenter.class, HitListPresenter.MyView.class, HitListView.class, HitListPresenter.MyProxy.class);
		bindPresenter(ModifierDonneesPagePresenter.class, ModifierDonneesPagePresenter.MyView.class, ModifierDonneesView.class,
		    ModifierDonneesPagePresenter.MyProxy.class);
		bindPresenter(ConfidentialitePagePresenter.class, ConfidentialitePagePresenter.MyView.class, ConfidentialitePageView.class,
		    ConfidentialitePagePresenter.MyProxy.class);
		bindPresenter(MesPhotosPresenter.class, MesPhotosPresenter.MyView.class, MesPhotosView.class, MesPhotosPresenter.MyProxy.class);

		bindPresenter(MessagesPresenter.class, MessagesPresenter.MyView.class, MessagesView.class, MessagesPresenter.MyProxy.class);

		bindPresenter(ProfileDetaillePresenter.class, ProfileDetaillePresenter.MyView.class, ProfileDetailleView.class, ProfileDetaillePresenter.MyProxy.class);
	}
}