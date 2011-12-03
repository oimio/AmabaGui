package ch.amaba.server;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ch.amaba.model.bo.CantonDTO;
import ch.amaba.shared.LoadCantonsAction;
import ch.amaba.shared.LoadCantonsResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author ROG
 */
public class LoadCantonsHandler extends AbstractHandler implements ActionHandler<LoadCantonsAction, LoadCantonsResult> {

	@Inject
	LoadCantonsHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
		super(servletContext, requestProvider);
	}

	
	public LoadCantonsResult execute(LoadCantonsAction action, ExecutionContext context) throws ActionException {
		Set<CantonDTO> cantons = null;
		try {
			cantons = AbstractHandler.dao.loadCantons();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new LoadCantonsResult(cantons);
	}

	
	public Class<LoadCantonsAction> getActionType() {
		return LoadCantonsAction.class;
	}

	
	public void undo(LoadCantonsAction arg0, LoadCantonsResult arg1, ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}

}
