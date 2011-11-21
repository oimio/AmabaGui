package ch.amaba.server;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.server.shared.LoadTraductionsAction;
import ch.amaba.server.shared.LoadTraductionsResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class LoadTraductionsHandler extends AbstractHandler implements ActionHandler<LoadTraductionsAction, LoadTraductionsResult> {

	@Inject
	LoadTraductionsHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	
	public LoadTraductionsResult execute(LoadTraductionsAction action, ExecutionContext context) throws ActionException {
		HashMap<String, HashMap<String, String>> traductions = null;
		try {
			final Locale locale = requestProvider.get().getLocale();
			final String langue = locale.getLanguage().toUpperCase();
			traductions = AbstractHandler.dao.loadTraductions(langue);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new LoadTraductionsResult(traductions);
	}

	
	public Class<LoadTraductionsAction> getActionType() {
		return LoadTraductionsAction.class;
	}

	
	public void undo(LoadTraductionsAction arg0, LoadTraductionsResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
