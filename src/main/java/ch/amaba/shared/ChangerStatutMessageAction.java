package ch.amaba.shared;

import java.util.Set;

import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * Change le statut des messages passés en paramètres (LU ou SUPPRIME).
 * 
 * */
public class ChangerStatutMessageAction extends UnsecuredActionImpl<ChangerStatutMessageResult> {
	private static final long serialVersionUID = 1L;

	/** Les id concernés. */
	private Set<Long> ids;

	/** Le nouveau statut. */
	private TypeMessageStatutEnum typeMessageStatutEnum;

	/**
	 * Ne pas supprimer : pour la serialization
	 * */
	@SuppressWarnings("unused")
	private ChangerStatutMessageAction() {
	}

	public ChangerStatutMessageAction(Set<Long> ids, TypeMessageStatutEnum typeMessageStatutEnum) {
		this.ids = ids;
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

	public Set<Long> getIds() {
		return ids;
	}

	public TypeMessageStatutEnum getTypeMessageStatutEnum() {
		return typeMessageStatutEnum;
	}

}
