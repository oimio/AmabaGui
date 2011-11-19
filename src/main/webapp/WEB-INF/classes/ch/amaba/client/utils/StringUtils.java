package ch.amaba.client.utils;

public class StringUtils {

	public static boolean isBlank(String input) {
		return ((input == null) || "".equals(input.trim()));
	}
}
