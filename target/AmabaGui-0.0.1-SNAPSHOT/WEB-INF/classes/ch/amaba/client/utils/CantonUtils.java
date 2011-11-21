package ch.amaba.client.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ch.amaba.client.presenter.Cache;
import ch.amaba.model.bo.CantonDTO;

import com.google.gwt.user.client.ui.ListBox;

public class CantonUtils<T> {

	public static void populate(final ListBox cantonsListBox) {
		final Set<CantonDTO> cantons = Cache.getCantons();
		final HashMap<String, HashMap<String, String>> traductions = Cache.getTraductions();
		final HashMap<String, String> map = traductions.get("CANTON");

		cantonsListBox.addItem("-- - --", "-1");
		for (final CantonDTO cantonDTO : cantons) {
			final String traduction = map.get(cantonDTO.getCodeCanton());
			cantonsListBox.addItem(traduction + " (" + cantonDTO.getCodeCanton() + ")", Long.toString(cantonDTO.getBusinessObjectId()));
		}
	}

	/**
	 * Sélectionne l'item de la liste correspondant à la value passée en
	 * paramètre.
	 * 
	 * @param value
	 *          - value de l'item
	 */
	public static void setSelectedIndex(final ListBox listBox, final Object value) {
		if (value != null) {
			final int itemCount = listBox.getItemCount();
			for (int i = 0; i < itemCount; i++) {
				if (value.toString().equals(listBox.getValue(i))) {
					listBox.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	/** Retourne dans un Set les values. */
	public static Set<Integer> getValues(final ListBox cantonsListBox) {
		final Set<Integer> values = new HashSet<Integer>();
		for (int i = 0; i < cantonsListBox.getItemCount(); i++) {
			if (cantonsListBox.isItemSelected(i)) {
				values.add(Integer.valueOf(cantonsListBox.getValue(i)));
			}
		}
		return values;
	}
}
