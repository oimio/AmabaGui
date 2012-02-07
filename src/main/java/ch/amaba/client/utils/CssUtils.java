package ch.amaba.client.utils;

import ch.amaba.client.resources.IMyCss;
import ch.amaba.client.resources.IResources;

public class CssUtils {
	private static IMyCss css;
	static {
		CssUtils.css = IResources.INSTANCE.css();
		CssUtils.css.ensureInjected();
	}

	public static IMyCss getCss() {
		return CssUtils.css;
	}
}
