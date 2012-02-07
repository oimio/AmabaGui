package ch.amaba.client.ui.composite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel représentant une préférence utilisateur.
 * 
 * <br/>
 * Il est définit par un text et un id.
 */
public class MySettingPanel extends Composite {

	interface MySettingPanelUiBinder extends UiBinder<Widget, MySettingPanel> {
	}

	@UiField
	Image fermerImage;

	@UiField
	Label label;

	Integer id;

	private static MySettingPanelUiBinder uiBinder = GWT.create(MySettingPanelUiBinder.class);

	/**
	 * Constructeur.
	 * 
	 * @param text
	 * @param id
	 */
	public MySettingPanel(final String textPreference, String id) {
		initWidget(MySettingPanel.uiBinder.createAndBindUi(this));
		label.setText(textPreference);
		this.id = Integer.valueOf(id);
	}

	/**
	 * Retourne l'id du setting.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	public Image getFermerImage() {
		return fermerImage;
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	/**
	 * <b>NE PAS SUPPRIMER</b>
	 * 
	 * Permet d'identifier les preferences uniques !
	 * */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final MySettingPanel other = (MySettingPanel) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}
}
