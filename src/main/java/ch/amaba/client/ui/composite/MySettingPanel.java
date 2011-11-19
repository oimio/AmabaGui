package ch.amaba.client.ui.composite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MySettingPanel extends Composite {

	interface MySettingPanelUiBinder extends UiBinder<Widget, MySettingPanel> {
	}

	@UiField
	Image fermerImage;

	@UiField
	Label label;

	String id;

	private static MySettingPanelUiBinder uiBinder = GWT.create(MySettingPanelUiBinder.class);

	public MySettingPanel(final String textPreference, String id) {
		initWidget(MySettingPanel.uiBinder.createAndBindUi(this));
		label.setText(textPreference);
		this.id = id;
	}

	public Image getFermerImage() {
		return fermerImage;
	}

	public Label getLabel() {
		return label;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
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
		return true;
	}

}
