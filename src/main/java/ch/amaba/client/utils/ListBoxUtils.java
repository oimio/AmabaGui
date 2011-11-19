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
	 * 
	 * */
	// public static void populerListBox(final ListBox listBox, String codeListe)
	// {
	// if (listBox != null) {
	// // Vider la liste
	// listBox.clear();
	// final Map<String, String> map = Cache.getTraductions().get(codeListe);
	// final Set<Entry<String, String>> entrySet = map.entrySet();
	//
	// for (final Entry<String, String> entry : entrySet) {
	// listBox.addItem(entry.getValue(), entry.getKey());
	// }
	// }
	// }

	public static <T extends Enum<?>> void populate(final ListBox cantonsListBox, Class<? extends Enum> enumType, String type) {
		final Map<String, Map<String, String>> traductions = CacheUtils.getTraductions();
		final Map<String, String> map = traductions.get(type);
		cantonsListBox.addItem("-- - --", "-1");
		try {
			for (final Enum t : enumType.getEnumConstants()) {
				final String traduction = map.get(t.name());
				cantonsListBox.addItem(traduction, t.name());
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
				// listBox.getValue(i);
				final Integer value = Integer.valueOf(listBox.getValue(i));
				values.add(value);
			}
		}
		return values;
	}
}
