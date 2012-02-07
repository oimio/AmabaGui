package ch.amaba.client.ui.message;

import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.MessagesPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author ROG
 */
public class MessagesView extends ViewImpl implements MessagesPresenter.MyView {

	interface ConfidentialiteViewUiBinder extends UiBinder<Widget, MessagesView> {
	}

	private static ConfidentialiteViewUiBinder uiBinder = GWT.create(ConfidentialiteViewUiBinder.class);

	public Widget widget;
	@UiField
	FlexTable messagesTable;

	@UiField
	Button supprimerButton;

	public MessagesView() {
		widget = MessagesView.uiBinder.createAndBindUi(this);
		// messagesTable.getColumnFormatter().setWidth(0, "600px");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == LoginPagePresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {

	}

	@Override
	public FlexTable getMessagesTable() {
		return messagesTable;
	}

	public Button getSupprimerButton() {
		return supprimerButton;
	}

}
