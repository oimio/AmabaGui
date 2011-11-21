package ch.amaba.server;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.Action;

/**
 * @author ROG
 */
public class DispatchServletModule extends ServletModule {

	
	public void configureServlets() {
		serve("/" + Action.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);
	}

}
