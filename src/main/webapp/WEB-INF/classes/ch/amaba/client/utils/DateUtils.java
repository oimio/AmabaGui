package ch.amaba.client.utils;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateUtils {

	/**
	 * Retourne le jour du mois (compris entre 1 et 31).
	 * 
	 * @param date
	 * @return le jour
	 * */
	public static String getDay(final Date date) {
		String day = "";
		if (date != null) {
			final DateTimeFormat df = DateTimeFormat.getFormat("dd");
			day = df.format(date);
		}
		return day;
	}

	/**
	 * Retourne le mois sur 2 chiffres (compris entre 1 et 12).
	 * 
	 * @param date
	 * @return le mois
	 * */
	public static String getMonth(final Date date) {
		String mois = "";
		if (date != null) {
			final DateTimeFormat df = DateTimeFormat.getFormat("MM");
			mois = df.format(date);
		}
		return mois;
	}

	/**
	 * Retourne l'année sur 4 chiffres.
	 * 
	 * @param date
	 * @return l'année
	 * */
	public static String getYear(final Date date) {
		String year = "";
		if (date != null) {
			final DateTimeFormat df = DateTimeFormat.getFormat("yyyy");
			year = df.format(date);
		}
		return year;
	}

	/**
	 * Retourne l'âge en format String pour l'internationalisation.<br/>
	 * 
	 * Ex : <i>34 ans</i>.
	 */
	public static Integer getAge(final Date dateNaissance) {
		final Integer age;
		final Date now = new Date();
		final DateTimeFormat annee = DateTimeFormat.getFormat("yyyy");
		final DateTimeFormat mois = DateTimeFormat.getFormat("MM");
		final DateTimeFormat jour = DateTimeFormat.getFormat("dd");
		final DateTimeFormat anniversaireCetteAnneeDTF = DateTimeFormat.getFormat("dd-MM-yyyy");
		final String anneeNow = annee.format(now);
		final Date anniversaireCetteAnnee = anniversaireCetteAnneeDTF.parse(jour.format(dateNaissance) + "-" + mois.format(dateNaissance) + "-" + anneeNow);
		// Déjà passé !
		if (anniversaireCetteAnnee.before(now)) {
			age = Integer.valueOf(anneeNow) - Integer.valueOf(annee.format(dateNaissance));
		} else {
			age = Integer.valueOf(anneeNow) - Integer.valueOf(annee.format(dateNaissance)) - 1;
		}
		return age;
	}

}
