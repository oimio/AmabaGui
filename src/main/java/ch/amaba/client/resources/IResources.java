package ch.amaba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface IResources extends ClientBundle {
	public static final IResources INSTANCE = GWT.create(IResources.class);

	@Source("my.css")
	public IMyCss css();

}
