/**
 * Copyright 2011 Amaba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ch.amaba.server;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.SerializationUtils;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.exception.CompteBloqueException;
import ch.amaba.model.bo.exception.CompteNonValideException;
import ch.amaba.model.bo.exception.EmailNonValideException;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.AuthentificationAction;
import ch.amaba.shared.AuthentificationResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class AuthentificationHandler extends AbstractHandler implements ActionHandler<AuthentificationAction, AuthentificationResult> {

	@Inject
	AuthentificationHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	@Override
	public AuthentificationResult execute(AuthentificationAction request, ExecutionContext context) throws ActionException {
		UserCriteria userCriteria = null;
		try {
			// Retourne un user incomplet par sécurité (sans email, password, etc..)
			userCriteria = SpringFactory.get().getDao().authentification(request.getEmail(), request.getPassword());
			// Création du répertoire du user
			final File file = new File("d:/data" + File.separator + userCriteria.getIdUser());
			if (!file.exists()) {
				file.mkdirs();
			}
			// Sauvegarde dans la session du userCriteria avec TOUTES les infos
			final UserCriteria complet = (UserCriteria) SerializationUtils.clone(userCriteria);
			complet.setEmail(request.getEmail());
			saveUserCriteriaSession(complet);
			// Ces données vont être envoyées au client, on purge donc les données
			// sensibles
			userCriteria.setPassword(null);
			userCriteria.setEmail(null);
			SpringFactory.get().getDao().saveUserConnection(complet.getIdUser(), getRequestProvider().get().getRemoteAddr());
		} catch (final LoginFailedException e) {
			throw new ActionException("Email ou mot de passe incorrecte.");
		} catch (final EmailNonValideException e) {
			throw new ActionException(
			    "Vous n'avez pas encore validé votre email : cliquez sur le lien contenu dans l'email que nous vous avons envoyé lors de votre inscription.");
		} catch (final CompteBloqueException e) {
			throw new ActionException("Votre compte a été bloqué.");
		} catch (final CompteNonValideException e) {
			throw new ActionException("Votre compte est en attente de validation par nos services. Vous recevrez sous moins de 48H une notification.");
		}
		return new AuthentificationResult(userCriteria);
	}

	@Override
	public Class<AuthentificationAction> getActionType() {
		return AuthentificationAction.class;
	}

	@Override
	public void undo(AuthentificationAction action, AuthentificationResult result, ExecutionContext context) throws ActionException {
		// Not undoable
	}

}
