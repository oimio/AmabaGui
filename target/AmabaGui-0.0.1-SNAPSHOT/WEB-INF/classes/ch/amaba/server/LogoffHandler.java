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
import javax.servlet.http.HttpSession;

import ch.amaba.server.shared.LogoffAction;
import ch.amaba.server.shared.LogoffResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class LogoffHandler extends AbstractHandler implements ActionHandler<LogoffAction, LogoffResult> {

	@Inject
	LogoffHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	
	public LogoffResult execute(LogoffAction request, ExecutionContext context) throws ActionException {
		final HttpSession session = requestProvider.get().getSession();
		if (session != null) {
			session.invalidate();
		}

		return new LogoffResult();
	}

	
	public Class<LogoffAction> getActionType() {
		return LogoffAction.class;
	}

	
	public void undo(LogoffAction action, LogoffResult result, ExecutionContext context) throws ActionException {
		// Not undoable
	}

}
