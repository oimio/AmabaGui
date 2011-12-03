package ch.amaba.client.ui.message;

import ch.amaba.client.presenter.LoginPagePresenter;
import ch.amaba.client.presenter.MessagesPresenter;
import ch.amaba.client.ui.composite.MyLabel;
import ch.amaba.client.utils.CssUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
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

	public MessagesView() {
		widget = MessagesView.uiBinder.createAndBindUi(this);
		// Header de la table
		messagesTable.setWidth("100%");
		messagesTable.getColumnFormatter().setWidth(1, "100px");
		messagesTable.getColumnFormatter().setWidth(2, "250px");
		messagesTable.getColumnFormatter().setWidth(3, "400px");
		messagesTable.setWidget(0, 0, new CheckBox());
		final MyLabel date = new MyLabel("Date", CssUtils.getCss().header());
		final MyLabel de = new MyLabel("De", CssUtils.getCss().header());
		final MyLabel sujet = new MyLabel("Sujet", CssUtils.getCss().header());
		messagesTable.setWidget(0, 1, date);
		messagesTable.setWidget(0, 2, de);
		messagesTable.setWidget(0, 3, sujet);
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

}
