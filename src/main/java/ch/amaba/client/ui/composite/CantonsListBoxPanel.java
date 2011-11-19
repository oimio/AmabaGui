package ch.amaba.client.ui.composite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple menu that can be reused.
 * 
 * @author ROG
 * 
 */

public class CantonsListBoxPanel extends Composite {
	interface CantonsListBoxUiBinder extends UiBinder<Widget, CantonsListBoxPanel> {
	}

	@UiField
	ListBox cantonsListBox;

	private static CantonsListBoxUiBinder uiBinder = GWT.create(CantonsListBoxUiBinder.class);

	public CantonsListBoxPanel() {
		initWidget(CantonsListBoxPanel.uiBinder.createAndBindUi(this));
	}

	public ListBox getCantonsListBox() {
		return cantonsListBox;
	}

}