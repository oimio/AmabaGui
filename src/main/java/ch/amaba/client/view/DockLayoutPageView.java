package ch.amaba.client.view;

import ch.amaba.client.presenter.DockLayoutPagePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * Vue principale de l'application (header, menu gauche et body central).
 * DockLayout
 * 
 * @author ROG
 */
public class DockLayoutPageView extends ViewImpl implements DockLayoutPagePresenter.MyView {

	interface DockLayoutUiBinder extends UiBinder<Widget, DockLayoutPageView> {
	}

	private static DockLayoutUiBinder uiBinder = GWT.create(DockLayoutUiBinder.class);

	public Widget widget;

	@UiField
	Label rechercheDetaillee;

	@UiField
	Label modifierDonneesLabel;

	@UiField
	Label mesPhotosLabel;

	@UiField
	Label confidentialiteLabel;

	@UiField
	Label messagesRecusLabel;
	@UiField
	Label messagesEnvoyesLabel;
	@UiField
	Label messagesSupprimesLabel;

	@UiField
	Label messagesNouveauxLabel;

	@UiField
	Label bodyLabel;

	@UiField
	VerticalPanel bodyPanel;

	@UiField
	ScrollPanel favorisPanel;

	public DockLayoutPageView() {
		widget = (DockLayoutPageView.uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == DockLayoutPagePresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		// On vide le panel central ("main" ou "body")
		getBodyPanel().clear();
		getBodyPanel().add(content);
		// mainContentPanel.clear();
		//
		// if (content != null) {
		// mainContentPanel.add(content);
		// }
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public Button getSendButton() {
		// TODO Auto-generated method stub
		return new Button();
	}

	@Override
	public Label getRechercheDetaillee() {
		return rechercheDetaillee;
	}

	@Override
	public Label getBodyLabel() {
		return bodyLabel;
	}

	public VerticalPanel getBodyPanel() {
		return bodyPanel;
	}

	@Override
	public Label getModifierDonneesLabel() {
		return modifierDonneesLabel;
	}

	@Override
	public Label getConfidentialiteLabel() {
		return confidentialiteLabel;
	}

	@Override
	public ScrollPanel getFavorisPanel() {
		return favorisPanel;
	}

	@Override
	public Label getMesPhotosLabel() {
		return mesPhotosLabel;
	}

	@Override
	public Label getMessagesRecusLabel() {
		return messagesRecusLabel;
	}

	@Override
	public Label getMessagesEnvoyesLabel() {
		return messagesEnvoyesLabel;
	}

	@Override
	public Label getMessagesSupprimesLabel() {
		return messagesSupprimesLabel;
	}

	@Override
	public Label getMessagesNouveauxLabel() {
		return messagesNouveauxLabel;
	}
}