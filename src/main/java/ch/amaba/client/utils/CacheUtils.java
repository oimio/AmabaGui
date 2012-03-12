package ch.amaba.client.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.amaba.client.IConstants;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.constants.TypeCaractereEnum;
import ch.amaba.model.bo.constants.TypeCouleurCheveux;
import ch.amaba.model.bo.constants.TypeCouleurYeux;
import ch.amaba.model.bo.constants.TypeInteretEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeProfessionEnum;
import ch.amaba.model.bo.constants.TypeRaceEnum;
import ch.amaba.model.bo.constants.TypeReligionEnum;
import ch.amaba.model.bo.constants.TypeSportEnum;

/**
 * Référentiel de la partie cliente.
 * 
 * */
public class CacheUtils {

	private static Set<CantonDTO> cantons;

	private static Map<String, Map<String, String>> traductions;

	public static Set<CantonDTO> getCantons() {
		return CacheUtils.cantons;
	}

	public static void setCantons(Set<CantonDTO> cantons) {
		for (final CantonDTO cantonDTO : cantons) {
			System.out.println(cantonDTO.getCodeCanton());
		}
		CacheUtils.cantons = cantons;
	}

	/**
	 * Retourne la traduction pour la clé et value (voir DB, table traduction);
	 * */
	public static String getTraduction(String keyDB, String valueDB) {
		String traduction = valueDB;
		final Map<String, String> map = CacheUtils.getTraductions().get(keyDB);
		if ((map != null) && (map.size() > 0)) {
			traduction = map.get(valueDB);
		}
		return traduction;
	}

	public static Map<String, Map<String, String>> getTraductions() {
		return CacheUtils.traductions;
	}

	public static void setTraductions(Map<String, Map<String, String>> traductions) {
		CacheUtils.traductions = traductions;
	}

	private static Map<String, Integer> enumMap = null;

	/**
	 * Retourne l'id d'un enum.<br/>
	 * 
	 * 
	 * Ex : <font color=red>TypeInteretEnum.VOYAGE</font> return <font
	 * color=red>3</font>.
	 * 
	 * @param enum
	 * @return l'id de l'enum. Retourne {@link IConstants.AUCUNE_SELECTION} si id
	 *         non trouveé.
	 * 
	 * */
	public static Integer getEnumId(Enum<?> obj) {
		Integer id = Integer.valueOf(IConstants.AUCUNE_SELECTION);
		if (CacheUtils.enumMap == null) {
			CacheUtils.enumMap = new HashMap<String, Integer>();
			final TypeInteretEnum[] values = TypeInteretEnum.values();
			for (final TypeInteretEnum typeInteretEnum : values) {
				CacheUtils.enumMap.put(typeInteretEnum.getClass().getName() + typeInteretEnum.name(), typeInteretEnum.getId());
			}
			final TypeSportEnum[] sports = TypeSportEnum.values();
			for (final TypeSportEnum type : sports) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeMusiqueEnum[] music = TypeMusiqueEnum.values();
			for (final TypeMusiqueEnum type : music) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeProfessionEnum[] prof = TypeProfessionEnum.values();
			for (final TypeProfessionEnum type : prof) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeReligionEnum[] religion = TypeReligionEnum.values();
			for (final TypeReligionEnum type : religion) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeCaractereEnum[] caractere = TypeCaractereEnum.values();
			for (final TypeCaractereEnum type : caractere) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeCouleurCheveux[] cheveux = TypeCouleurCheveux.values();
			for (final TypeCouleurCheveux type : cheveux) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeCouleurYeux[] yeux = TypeCouleurYeux.values();
			for (final TypeCouleurYeux type : yeux) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
			final TypeRaceEnum[] race = TypeRaceEnum.values();
			for (final TypeRaceEnum type : race) {
				CacheUtils.enumMap.put(type.getClass().getName() + type.name(), type.getId());
			}
		}
		if (obj != null) {
			id = CacheUtils.enumMap.get(obj.getClass().getName() + obj.name());
		}
		return id;
	}
}