package ch.amaba.client.context;

import java.util.Set;

import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

/**
 * Conserve les variables du context UI.
 * */
public class ContextUI {

	private static final ContextUI contextUI = new ContextUI();

	private UserCriteria userCriteria;

	/** Résultat d'une recherche. */
	private Set<UserCriteria> searchResult;

	/**
	 * Par défaut tous les messages recus (non lu et lu)
	 * */
	private TypeMessageStatutEnum typeMessageStatutEnum;

	/** Retourne le Singleton. */
	public static ContextUI get() {
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

	/**
	 * Retourne l'action pour l'affichage des messages (recus, envoyes ou
	 * supprimes)
	 * */
	public TypeMessageStatutEnum getMessageAction() {
		return typeMessageStatutEnum;
	}

	public void setMessageAction(TypeMessageStatutEnum typeMessageStatutEnum) {
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

	Long profileDetailleId;

	public Long getProfileDetailleId() {
		return profileDetailleId;
	}

	/**
	 * L'id du profile détaillé que l'on souhaite voir.
	 * */
	public void setProfileDetailleId(final Long profileDetailleId) {
		this.profileDetailleId = profileDetailleId;
	}

}