package ch.amaba.client.ui.composite;

import java.util.Set;

import ch.amaba.client.utils.CacheUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel représentant une préférence utilisateur.
 * 
 * <br/>
 * Il est définit par un text et un id.
 */
public class ListSettingPanel extends Composite {

	interface ListSettingPanelUiBinder extends UiBinder<Widget, ListSettingPanel> {
	}

	@UiField
	VerticalPanel list;

	@UiField
	Label label;

	Integer id;

	private static ListSettingPanelUiBinder uiBinder = GWT.create(ListSettingPanelUiBinder.class);

	/**
	 * Constructeur.
	 * 
	 * @param text
	 * @param id
	 */
	public ListSettingPanel(final String title, Set<String> values, String keyDB) {
		initWidget(ListSettingPanel.uiBinder.createAndBindUi(this));
		label.setText(title);
		for (final String value : values) {
			final Label label = new Label(CacheUtils.getTraduction(keyDB, value));
			list.add(label);
		}
	}

	/**
	 * Retourne l'id du setting.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	public Label getLabel() {
		return label;
	}

	public VerticalPanel getList() {
		return list;
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
		final ListSettingPanel other = (ListSettingPanel) obj;
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
