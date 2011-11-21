package ch.amaba.client.context;

import java.util.Set;

import ch.amaba.model.bo.UserCriteria;

/**
 * Conserve les variables du context UI.
 * */
public class ContextUI {

	private static ContextUI contextUI;

	private UserCriteria userCriteria;

	/** Résultat d'une recherche. */
	private Set<UserCriteria> searchResult;

	/** Retourne le Singleton. */
	public static ContextUI get() {
		if (ContextUI.contextUI == null) {
			ContextUI.contextUI = new ContextUI();
		}
		return ContextUI.contextUI;
	}

	/***/
	private ContextUI() {

	}

	/**
	 * Retourne le user connecté.
	 * */
	public final UserCriteria getUserCourant() {
		return ContextUI.get().userCriteria;
	}

	public void setUserCourant(final UserCriteria userCriteria) {
		ContextUI.get().userCriteria = userCriteria;
	}

	/** Résultat d'une recherche. */
	public Set<UserCriteria> getSearchResult() {
		return searchResult;
	}

	/** Set le résultat de la recherche. */
	public void setSearchResult(final Set<UserCriteria> searchResult) {
		this.searchResult = searchResult;
	}

}
