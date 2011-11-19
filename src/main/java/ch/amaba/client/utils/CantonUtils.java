package ch.amaba.client.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ch.amaba.model.bo.CantonDTO;

import com.google.gwt.user.client.ui.ListBox;

public class CantonUtils<T> {

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

	public static void populate(final ListBox cantonsListBox, final String type) {
		final Set<CantonDTO> cantons = CacheUtils.getCantons();
		final Map<String, Map<String, String>> traductions = CacheUtils.getTraductions();
		final Map<String, String> map = traductions.get(type);

		cantonsListBox.addItem("-- - --", "-1");
		for (final CantonDTO cantonDTO : cantons) {
			final String traduction = map.get(cantonDTO.getCodeCanton());
			cantonsListBox.addItem(traduction + " (" + cantonDTO.getCodeCanton() + ")", Long.toString(cantonDTO.getBusinessObjectId()));
		}
	}

	public static String getCantonTraduction(Set<Integer> idCantons) {
		return CantonUtils.getCantonTraduction(idCantons, false);
	}

	/** Retourne le libellé du canton correspondant à l'id. */
	public static String getCantonTraduction(Set<Integer> idCantons, boolean codeCanton) {
		String cantonTraduction = "";
		final Integer idCanton = idCantons.iterator().next();
		final Set<CantonDTO> cantons = CacheUtils.getCantons();
		String code = null;
		for (final CantonDTO cantonDTO : cantons) {
			if (idCanton.longValue() == cantonDTO.getBusinessObjectId()) {
				code = cantonDTO.getCodeCanton();
				break;
			}
		}

		final Map<String, String> map = CacheUtils.getTraductions().get("CANTON");
		final Set<Entry<String, String>> entrySet = map.entrySet();

		for (final Entry<String, String> entry : entrySet) {
			if (code.equals(entry.getKey())) {
				cantonTraduction = (codeCanton ? code.toUpperCase() : entry.getValue());
				break;
			}
		}
		return cantonTraduction;
	}

	/** Retourne le libellé du canton correspondant à l'id. */
	public static String getCantonTraduction(Integer idCanton) {
		final Set<Integer> canton = new HashSet<Integer>();
		canton.add(idCanton);
		return CantonUtils.getCantonTraduction(canton, false);
	}

	public static String getCantonTraduction(Integer idCanton, boolean codeCanton) {
		final Set<Integer> canton = new HashSet<Integer>();
		canton.add(idCanton);
		return CantonUtils.getCantonTraduction(canton, codeCanton);
	}

}
