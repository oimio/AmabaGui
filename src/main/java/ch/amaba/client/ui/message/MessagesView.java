package ch.amaba.client.ui.message;

import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.MessagesPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * Vue des messages (nouveaux, recus, envoyes et supprimes) :<br>
 * <ul>
 * <li>- header</li>
 * <li>- panel de la liste des lessages</li>
 * <li>- panel contenant le text d'un message</li>
 * </ul>
 * 
 * @author ROG
 */
public class MessagesView extends ViewImpl implements MessagesPresenter.MyView {

	interface MessagesViewUiBinder extends UiBinder<Widget, MessagesView> {
	}

	private static MessagesViewUiBinder uiBinder = GWT.create(MessagesViewUiBinder.class);

	public Widget widget;
	@UiField
	FlexTable messagesTable;

	@UiField
	Button supprimerButton;

	@UiField
	HTMLPanel messageTextPanel;

	@UiField
	Label messageDate;

	@UiField
	Label messageText;

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

	@Override
	public Button getSupprimerButton() {
		return supprimerButton;
	}

	@Override
	public HTMLPanel getMessageTextPanel() {
		return messageTextPanel;
	}

	public Widget getWidget() {
		return widget;
	}

	@Override
	public Label getMessageDate() {
		return messageDate;
	}

	@Override
	public Label getMessageText() {
		return messageText;
	}
}