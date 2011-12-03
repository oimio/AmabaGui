package ch.amaba.client.utils;

import ch.amaba.client.resources.IMyCss;
import ch.amaba.client.resources.IResources;

public class CssUtils {

	private static IMyCss css;

	public static IMyCss getCss() {
		if (CssUtils.css == null) {
			CssUtils.css = IResources.INSTANCE.css();
			CssUtils.css.ensureInjected();
		}
		return CssUtils.css;
	}
}
