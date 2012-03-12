package ch.amaba.client.utils;

public class NumberUtils {

	public static boolean isInteger(final String value) {
		boolean is = false;
		try {
			Integer.parseInt(value);
			is = true;
		} catch (final Exception e) {
			//
		}
		return is;
	}

}
