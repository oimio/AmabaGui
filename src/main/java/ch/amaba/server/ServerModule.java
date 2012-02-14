package ch.amaba.server;

import ch.amaba.shared.AjouterFavorisAction;
import ch.amaba.shared.AuthentificationAction;
import ch.amaba.shared.ChangerStatutMessageAction;
import ch.amaba.shared.DeconnectionAction;
import ch.amaba.shared.DevenirMembreAction;
import ch.amaba.shared.EnvoyerMessagesAction;
import ch.amaba.shared.ListeFavorisAction;
import ch.amaba.shared.LoadCantonsAction;
import ch.amaba.shared.LoadFullUserAction;
import ch.amaba.shared.LoadTraductionsAction;
import ch.amaba.shared.MessageTextAction;
import ch.amaba.shared.MessagesAction;
import ch.amaba.shared.RechercheDetailleeAction;
import ch.amaba.shared.SupprimerDefinitivementAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * Module qui bind handlers et configurations.
 * 
 * @author ROG
 */
public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(AuthentificationAction.class, AuthentificationHandler.class);
		bindHandler(LoadCantonsAction.class, LoadCantonsHandler.class);
		bindHandler(DevenirMembreAction.class, DevenirMembreHandler.class);
		bindHandler(DeconnectionAction.class, DeconnectionHandler.class);
		bindHandler(LoadTraductionsAction.class, LoadTraductionsHandler.class);
		bindHandler(AjouterFavorisAction.class, AjouterFavorisHandler.class);
		bindHandler(LoadFullUserAction.class, LoadFullUserHandler.class);
		bindHandler(RechercheDetailleeAction.class, RechercheDetailleeHandler.class);
		bindHandler(ListeFavorisAction.class, ListeFavorisHandler.class);
		bindHandler(MessagesAction.class, MessagesHandler.class);
		bindHandler(EnvoyerMessagesAction.class, EnvoyerMessagesHandler.class);
		bindHandler(SupprimerDefinitivementAction.class, SupprimerDefinitivementHandler.class);
		bindHandler(ChangerStatutMessageAction.class, ChangerStatutMessageHandler.class);
		bindHandler(MessageTextAction.class, GetMessageTextHandler.class);
	}
}
