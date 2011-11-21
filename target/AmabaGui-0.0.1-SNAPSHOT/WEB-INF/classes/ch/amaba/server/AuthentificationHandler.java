/**
 * Copyright 2011 ArcBees Inc.
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.server.shared.AuthentificationAction;
import ch.amaba.server.shared.AuthentificationResult;

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

	
	public AuthentificationResult execute(AuthentificationAction request, ExecutionContext context) throws ActionException {
		final UserCriteria userCriteria;
		try {
			userCriteria = AbstractHandler.dao.authentification(request.getEmail(), request.getPassword());
			// Ces données vont être envoyées au client, on purge donc les données
			// sensibles
			userCriteria.setPassword(null);
			userCriteria.setEmail(null);
			// Sauvegarde dans la session du userCriteria.
			saveUserCriteriaSession(userCriteria);
		} catch (final LoginFailedException e) {
			throw new ActionException(e);
		}
		return new AuthentificationResult(userCriteria);
	}

	
	public Class<AuthentificationAction> getActionType() {
		return AuthentificationAction.class;
	}

	
	public void undo(AuthentificationAction action, AuthentificationResult result, ExecutionContext context) throws ActionException {
		// Not undoable
	}

}
