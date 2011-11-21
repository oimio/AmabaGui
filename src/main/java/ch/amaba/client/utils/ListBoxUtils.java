package ch.amaba.client.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ch.amaba.client.IConstants;

import com.google.gwt.user.client.ui.ListBox;

public class ListBoxUtils {

	/**
	 * Remplit la listeBox passée en paramètre.
	 * 
	 * <br/>
	 * Ex:<br/>
	 * INTERET Cinéma<br/>
	 * INTERET Sport
	 * 
	 * @param listBox
	 * @param codeListe
	 *          - la clée dans la map des traductions
	 * @param codeTypeTraduction
	 *          - lien dans la table de traduction
	 * 
	 * */
	public static <T extends Enum<?>> void populate(final ListBox listBox, Class<? extends Enum> enumType, String codeTypeTraduction) {
		final Map<String, Map<String, String>> traductions = CacheUtils.getTraductions();
		final Map<String, String> map = traductions.get(codeTypeTraduction);
		listBox.clear();
		listBox.addItem("-- - --", IConstants.AUCUNE_SELECTION);
		try {
			for (final Enum t : enumType.getEnumConstants()) {
				final String traduction = map.get(t.name());
				listBox.addItem(traduction, Integer.toString(CacheUtils.getEnumId(t)));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/** Retourne dans un Set les values. */
	public static Set<Integer> getValues(final ListBox listBox) {
		final Set<Integer> values = new HashSet<Integer>();
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i) && (listBox.getValue(i) != null) && !IConstants.AUCUNE_SELECTION.equals(listBox.getValue(i))) {
				final Integer value = Integer.valueOf(listBox.getValue(i));
				values.add(value);
			}
		}
		return values;
	}
}
