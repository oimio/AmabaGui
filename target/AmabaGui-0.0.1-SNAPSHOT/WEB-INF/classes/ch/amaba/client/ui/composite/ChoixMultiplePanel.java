package ch.amaba.client.ui.composite;

import java.util.HashSet;
import java.util.Set;

import ch.amaba.client.IConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * ListBox des sports
 * 
 * @author ROG
 */
public class ChoixMultiplePanel extends Composite {
	interface ChoixMultiplePanelUiBinder extends UiBinder<Widget, ChoixMultiplePanel> {
	}

	private static ChoixMultiplePanelUiBinder uiBinder = GWT.create(ChoixMultiplePanelUiBinder.class);

	@UiField
	SportListBox sportListBox;

	@UiField
	VerticalPanel selectionHorizontalPanel;

	private final Set<MySettingPanel> settings = new HashSet<MySettingPanel>();

	public ChoixMultiplePanel() {
		initWidget(ChoixMultiplePanel.uiBinder.createAndBindUi(this));
		getSportListBox().getSportListBox().addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {
				if (settings.size() >= IConstants.MAX_SELECTION) {
					Window.alert("Vous pouvez ajouter jusque " + IConstants.MAX_SELECTION + " éléments.");
				} else {
					final String text = getSportListBox().getSportListBox().getItemText(getSportListBox().getSportListBox().getSelectedIndex());
					final String value = getSportListBox().getSportListBox().getValue(getSportListBox().getSportListBox().getSelectedIndex());
					ajouterPreference(text, value);
				}
			}
		});
	}

	/**
	 * Ajout une nouvelle préférence dans la liste (un sport, un intérêt, une
	 * religion, etc...)
	 */
	public void ajouterPreference(final String text, String id) {
		final MySettingPanel msp = new MySettingPanel(text, id);
		msp.getFermerImage().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (settings.remove(msp)) {
					getSelectionHorizontalPanel().remove(msp);
				}
			}
		});
		if (!settings.contains(msp)) {
			settings.add(msp);
			getSelectionHorizontalPanel().add(msp);
		}
	}

	SportListBox getSportListBox() {
		return sportListBox;
	}

	VerticalPanel getSelectionHorizontalPanel() {
		return selectionHorizontalPanel;
	}
}