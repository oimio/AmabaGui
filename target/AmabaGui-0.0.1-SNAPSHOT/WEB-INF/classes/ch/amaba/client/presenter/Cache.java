package ch.amaba.client.presenter;

import java.util.HashMap;
import java.util.Set;

import ch.amaba.model.bo.CantonDTO;

public class Cache {

	private static Set<CantonDTO> cantons;

	private static HashMap<String, HashMap<String, String>> traductions;

	public static Set<CantonDTO> getCantons() {
		return Cache.cantons;
	}

	public static void setCantons(Set<CantonDTO> cantons) {
		Cache.cantons = cantons;
	}

	public static HashMap<String, HashMap<String, String>> getTraductions() {
		return Cache.traductions;
	}

	public static void setTraductions(HashMap<String, HashMap<String, String>> traductions) {
		Cache.traductions = traductions;
	}
}
