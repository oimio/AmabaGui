package ch.amaba.client.ui.composite;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ch.amaba.client.IConstants;
import ch.amaba.client.utils.ListBoxUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
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
	ListBoxPanel listBoxPanel;

	@UiField
	VerticalPanel selectionHorizontalPanel;

	private final Map<String, MySettingPanel> settings = new HashMap<String, MySettingPanel>();

	public ChoixMultiplePanel(Class<? extends Enum<?>> enumClass, String type, String headerText) {
		initWidget(ChoixMultiplePanel.uiBinder.createAndBindUi(this));
		final ListBox listBox = getListBoxPanel().getListBox();
		getListBoxPanel().setHeaderText(headerText);
		// Populate listBox
		ListBoxUtils.populateAvecTraduction(listBox, enumClass, type);
		selectionHorizontalPanel.setWidth(IConstants.CHOIX_MULTIPLE_WIDTH);

		getListBoxPanel().getListBox().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (settings.size() >= IConstants.MAX_SELECTION) {
					Window.alert("L'ajout est limité à " + IConstants.MAX_SELECTION + " éléments.");
				} else {
					final String text = listBox.getItemText(listBox.getSelectedIndex());
					final String value = listBox.getValue(listBox.getSelectedIndex());
					if ((value != null) && !IConstants.AUCUNE_SELECTION.equals(value)) {
						ajouterPreference(text, value);
					}
				}
			}
		});
	}

	/**
	 * Ajout une nouvelle préférence dans la liste (un sport, un intérêt, une
	 * religion, etc...)
	 */
	public void ajouterPreference(final String text, final String id) {
		if (settings.get(id + text) == null) {

			final MySettingPanel msp = new MySettingPanel(text, id);
			msp.getFermerImage().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (settings.remove(id + text) != null) {
						getSelectionHorizontalPanel().remove(msp);
					}
				}
			});
			settings.put(id + text, msp);
			getSelectionHorizontalPanel().add(msp);
		}
	}

	public ListBoxPanel getListBoxPanel() {
		return listBoxPanel;
	}

	VerticalPanel getSelectionHorizontalPanel() {
		return selectionHorizontalPanel;
	}

	/**
	 * Retourne les id des settings utilisateurs.
	 * */
	public Set<Integer> getValues() {
		final Set<Integer> values = new HashSet<Integer>();
		final Collection<MySettingPanel> selection = settings.values();
		for (final MySettingPanel panel : selection) {
			values.add(panel.getId());
		}
		return values;
	}
}