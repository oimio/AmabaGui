package ch.amaba.server;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.server.utils.SpringFactory;
import ch.amaba.shared.LoadTraductionsAction;
import ch.amaba.shared.LoadTraductionsResult;

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

	@Override
	public LoadTraductionsResult execute(LoadTraductionsAction action, ExecutionContext context) throws ActionException {
		Map<String, Map<String, String>> traductions = null;
		try {
			final Locale locale = requestProvider.get().getLocale();
			final String langue = locale.getLanguage().toUpperCase();
			traductions = SpringFactory.get().getDao().loadTraductions(langue);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new ActionException("Une erreur interne est survenue, merci de ressayer plus tard.");
		}
		return new LoadTraductionsResult(traductions);
	}

	@Override
	public Class<LoadTraductionsAction> getActionType() {
		return LoadTraductionsAction.class;
	}

	@Override
	public void undo(LoadTraductionsAction arg0, LoadTraductionsResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
