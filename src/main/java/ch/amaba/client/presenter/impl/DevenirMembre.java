package ch.amaba.client.presenter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import ch.amaba.client.IConstants;
import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.client.utils.StringUtils;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.shared.DevenirMembreAction;
import ch.amaba.shared.DevenirMembreResult;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;

/**
 * Process de validation du formulaire pour devenir membre.
 * */
public class DevenirMembre {

	public static void devenirMembre(final LoginPagePresenter.MyView view, final DispatchAsync dispatcher) {
		final List<String> errorMessages = new ArrayList<String>();
		String codeSexe = view.getGenreListBox().getValue(view.getGenreListBox().getSelectedIndex());
		String cantonId = view.getCantonListBox().getValue(view.getCantonListBox().getSelectedIndex());
		String nom = view.getNomTextBox().getText();
		String prenom = view.getPrenomTextBox().getText();
		String email = view.getEmailTextBox().getText();
		String emailRepeat = view.getEmailRepeatTextBox().getText();

		String password = view.getPasswordTextBox().getText();
		String passwordRepeat = view.getPasswordRepeatTextBox().getText();
		String jour = view.getJourTextBox().getText();
		String mois = view.getMoisTextBox().getText();
		String annee = view.getAnneeTextBox().getText();
		// MOCK
		codeSexe = "1";
		cantonId = "1";
		email = "r@gmail.com";
		emailRepeat = "r@gmail.com";
		prenom = "Rodolphe";
		password = "123456";
		passwordRepeat = "123456";
		nom = "Gomes";
		jour = "15";
		mois = "02";
		annee = "1975";
		// ///////////////////////////////////

		if (IConstants.AUCUNE_SELECTION.equals(codeSexe)) {
			DOM.setElementAttribute(view.getPrenomTextBox().getElement(), "id", "error-background");
			errorMessages.add("Vous devez choisir si vous êtes un homme ou une femme.");
		}
		if (StringUtils.isBlank(password) || StringUtils.isBlank(passwordRepeat) || !password.equals(passwordRepeat) || (password.length() < 6)) {
			errorMessages.add("Les mots de passes doivent contenir au moins 6 caractères et doivent être identiques.");
		}
		if (StringUtils.isBlank(email) || StringUtils.isBlank(emailRepeat) || !email.equals(emailRepeat)) {
			errorMessages.add("Les emails doivent être identiques.");
		}
		if (StringUtils.isBlank(prenom)) {
			DOM.setElementAttribute(view.getPrenomTextBox().getElement(), "id", "error-background");
			errorMessages.add("Le prénom est obligatoire.");
		}
		if (StringUtils.isBlank(nom)) {
			errorMessages.add("Le nom est obligatoire.");
		}
		if (StringUtils.isBlank(jour) || (Integer.valueOf(jour) < 1) || (Integer.valueOf(jour) > 31)) {
			errorMessages.add("Le jour de naissance est obligatoire et doit être compris entre 1 et 31.");
		}
		if (StringUtils.isBlank(mois) || (Integer.valueOf(mois) < 1) || (Integer.valueOf(mois) > 12)) {
			errorMessages.add("Le mois de naissance est obligatoire et doit être compris entre 1 et 12.");
		}
		if (StringUtils.isBlank(annee) || (Integer.valueOf(annee) < 1900)) {
			errorMessages.add("L'année de naissance est obligatoire et doit être au format yyyy.");
		}
		if (IConstants.AUCUNE_SELECTION.equals(cantonId)) {
			errorMessages.add("Le nom est obligatoire.");
		}
		DateTimeFormat df = DateTimeFormat.getFormat("ddMMyyyy HH:mm:ss");
		df = DateTimeFormat.getFormat("yyyy-MM-dd");
		Date dateNaissance = null;
		try {
			dateNaissance = df.parse(annee + "-" + mois + "-" + jour);
			if (DateUtils.getAge(dateNaissance) < 18) {
				errorMessages.add("Vous devez être majeur - avoir au moins 18 ans - pour vous inscrire.");
			}
		} catch (final Exception e) {
			errorMessages.add("La date de naissance est incorecte.");
		}
		view.getErrorPanel().setVisible(!errorMessages.isEmpty());
		view.setError(errorMessages);
		if (errorMessages.isEmpty()) {
			final UserCriteria criteria = new UserCriteria();

			criteria.setEmail(email + df.format(new Date()));
			criteria.setNom(nom);
			criteria.setPrenom(prenom);
			criteria.setIdSexe(Integer.valueOf(codeSexe));
			criteria.setIdCantons(new HashSet<Integer>(Arrays.asList(1)));
			criteria.setPassword(password);

			criteria.setDateNaissance(dateNaissance);

			dispatcher.execute(new DevenirMembreAction(criteria), new AsyncCallback<DevenirMembreResult>() {

				@Override
				public void onFailure(Throwable caught) {
					// view.setServerResponse("An error occured: " +
					caught.getMessage();
				}

				@Override
				public void onSuccess(DevenirMembreResult result) {
					Window.alert("Pour valider votre inscription, merci de consulter votre boite mail et de cliquer sur le lien d'activation.");
				}
			});
		}

	}
}
