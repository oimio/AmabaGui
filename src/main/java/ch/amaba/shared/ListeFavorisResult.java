package ch.amaba.shared;

import java.util.Set;

import ch.amaba.model.bo.AmiDTO;

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link AjouterFavorisAction} action.
 */
public class ListeFavorisResult implements Result {

	private static final long serialVersionUID = 1L;

	private Set<AmiDTO> listeFavoris;

	/**
	 * For serialization only.
	 */
	public ListeFavorisResult(Set<AmiDTO> listeFavoris) {
		this.listeFavoris = listeFavoris;
	}

	/**
	 * For serialization only.
	 */
	@SuppressWarnings("unused")
	private ListeFavorisResult() {
	}

	public Set<AmiDTO> getListeFavoris() {
		return listeFavoris;
	}
}