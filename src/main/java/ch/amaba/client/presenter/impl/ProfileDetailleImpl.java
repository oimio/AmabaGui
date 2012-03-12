package ch.amaba.client.presenter.impl;

import ch.amaba.client.IConstants;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.ProfileDetaillePresenter;
import ch.amaba.client.ui.composite.ListSettingPanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.PhysiqueCriteria;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeCaractereEnum;
import ch.amaba.model.bo.constants.TypeCouleurCheveux;
import ch.amaba.model.bo.constants.TypeCouleurYeux;
import ch.amaba.model.bo.constants.TypeGenreEnum;
import ch.amaba.model.bo.constants.TypeInteretEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeProfessionEnum;
import ch.amaba.model.bo.constants.TypeRaceEnum;
import ch.amaba.model.bo.constants.TypeReligionEnum;
import ch.amaba.model.bo.constants.TypeSexeEnum;
import ch.amaba.model.bo.constants.TypeSportEnum;
import ch.amaba.shared.ProfileDetailleAction;
import ch.amaba.shared.ProfileDetailleResult;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;

public class ProfileDetailleImpl {

	public static void process(final DispatchAsync dispatcher, final ProfileDetaillePresenter.MyView view) {
		dispatcher.execute(new ProfileDetailleAction(ContextUI.get().getProfileDetailleId()), new AsyncCallback<ProfileDetailleResult>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(ProfileDetailleResult result) {
				final UserCriteria userCriteria = result.getUserCriteria();
				final String photoPrincipaleFileName = userCriteria.getPhotoPrincipaleFileName();
				if (photoPrincipaleFileName != null) {
					view.getPhotoPrincipale().setUrl("amaba/download?id=" + userCriteria.getIdUser() + "&file=_" + userCriteria.getPhotoPrincipaleFileName());
				}
				view.getPrenom().setText(userCriteria.getPrenom());
				view.getNom().setText(userCriteria.getNom());
				view.getAge().setText(DateUtils.getAge(userCriteria.getDateNaissance()) + " ans");
				view.getSexe().setText(TypeSexeEnum.MASCULIN.getId().equals(userCriteria.getIdSexe()) ? "Homme" : "Femme");
				final ProfileCriteria profileCriteria = userCriteria.getProfileCriteria();
				if (profileCriteria != null) {
					view.getMarie().setText(Short.valueOf("1").equals(profileCriteria.getMarie()) ? "Marié" : "");
					view.getDivorce().setText(Short.valueOf("1").equals(profileCriteria.getDivorce()) ? "Divorcé" : "");
					view.getVeuf().setText(Short.valueOf("1").equals(profileCriteria.getVeuf()) ? "Veuf" : "");
					view.getGenre().setText(TypeGenreEnum.getEnumById(profileCriteria.getGenre()).name());
					view.getRelationSerieuse()
					    .setText(Short.valueOf("1").equals(profileCriteria.getRechercheRelationSerieuse()) ? "Recherche une relation sérieuse" : "");
				}
				if (userCriteria.getPhysiqueCriteria() != null) {
					final PhysiqueCriteria physiqueCriteria = userCriteria.getPhysiqueCriteria();
					view.getCouleurCheveux().setText(TypeCouleurCheveux.getEnumById(physiqueCriteria.getCouleurCheveuxUser()).name());
					view.getCouleurYeux().setText(TypeCouleurYeux.getEnumById(physiqueCriteria.getCouleurYeuxUser()).name());
					view.getTaille().setText(physiqueCriteria.getPoids() + " kg");
					view.getPoids().setText(physiqueCriteria.getTaille() + " cm");
				}
				view.getInterets().clear();
				view.getSports().clear();
				view.getMusiques().clear();
				view.getProfessions().clear();
				view.getReligions().clear();
				view.getRaces().clear();
				view.getCaracteres().clear();

				view.getInterets().add(new ListSettingPanel("Intérêts", TypeInteretEnum.getNames(userCriteria.getIdInterets()), IConstants.ENUM_TYPE_INTERET));
				view.getMusiques().add(new ListSettingPanel("Musique", TypeMusiqueEnum.getNames(userCriteria.getIdMusiques()), IConstants.ENUM_TYPE_MUSIC));
				view.getRaces().add(new ListSettingPanel("Race", TypeRaceEnum.getNames(userCriteria.getIdRaces()), IConstants.ENUM_TYPE_RACE));
				view.getReligions().add(new ListSettingPanel("Religion", TypeReligionEnum.getNames(userCriteria.getIdReligions()), IConstants.ENUM_TYPE_RELIGION));
				view.getCaracteres().add(
				    new ListSettingPanel("Personnalité", TypeCaractereEnum.getNames(userCriteria.getIdCaracteres()), IConstants.ENUM_TYPE_CARACTERE));
				view.getProfessions().add(
				    new ListSettingPanel("Profession", TypeProfessionEnum.getNames(userCriteria.getIdProfessions()), IConstants.ENUM_TYPE_PROFESSION));
				view.getSports().add(new ListSettingPanel("Sports", TypeSportEnum.getNames(userCriteria.getIdSports()), IConstants.ENUM_TYPE_SPORT));

			}
		});
	}
}
