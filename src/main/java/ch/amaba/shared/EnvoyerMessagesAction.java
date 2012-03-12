package ch.amaba.shared;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * Première prise de contact.
 */
public class EnvoyerMessagesAction extends UnsecuredActionImpl<EnvoyerMessagesResult> {

	private static final long serialVersionUID = 1L;

	private Long idDestinataire;

	public EnvoyerMessagesAction() {
	}

	/**
	 * For serialization only.
	 */
	public EnvoyerMessagesAction(final Long idDestinataire) {
		this.idDestinataire = idDestinataire;

	}

	public Long getIdDestinataire() {
		return idDestinataire;
	}

	public void setIdDestinataire(Long idDestinataire) {
		this.idDestinataire = idDestinataire;
	}

}
