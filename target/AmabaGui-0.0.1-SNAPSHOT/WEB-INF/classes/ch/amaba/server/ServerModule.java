package ch.amaba.server;

import ch.amaba.server.shared.AjouterFavorisAction;
import ch.amaba.server.shared.AuthentificationAction;
import ch.amaba.server.shared.DeconnectionAction;
import ch.amaba.server.shared.DevenirMembreAction;
import ch.amaba.server.shared.LoadCantonsAction;
import ch.amaba.server.shared.LoadFullUserAction;
import ch.amaba.server.shared.LoadTraductionsAction;
import ch.amaba.server.shared.RechercheDetailleeAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module qui bind handlers et configurations.
 * 
 * @author ROG
 */
public class ServerModule extends HandlerModule {

	
	protected void configureHandlers() {
		bindHandler(AuthentificationAction.class, AuthentificationHandler.class);
		bindHandler(LoadCantonsAction.class, LoadCantonsHandler.class);
		bindHandler(DevenirMembreAction.class, DevenirMembreHandler.class);
		bindHandler(DeconnectionAction.class, DeconnectionHandler.class);
		bindHandler(LoadTraductionsAction.class, LoadTraductionsHandler.class);
		bindHandler(AjouterFavorisAction.class, AjouterFavorisHandler.class);
		bindHandler(LoadFullUserAction.class, LoadFullUserHandler.class);
		bindHandler(RechercheDetailleeAction.class, RechercheDetailleeHandler.class);
	}
}
