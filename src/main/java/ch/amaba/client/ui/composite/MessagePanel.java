package ch.amaba.client.ui.composite;

import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * 
 * @author ROG
 */
public class MessagePanel extends Composite {
	interface MessagePanelUiBinder extends UiBinder<Widget, MessagePanel> {
	}

	private static MessagePanelUiBinder uiBinder = GWT.create(MessagePanelUiBinder.class);

	@UiField
	CheckBox selection;

	@UiField
	Label date;

	@UiField
	Label from;

	@UiField
	Label sujet;
	private Long messageId;

	private TypeMessageStatutEnum typeMessageStatutEnum;

	/** Ne pas utiliser : utiliser initWidget */
	@SuppressWarnings("unused")
	private MessagePanel() {
	}

	public Long getMessageId() {
		return messageId;
	}

	public MessagePanel(Long messageId) {
		this.messageId = messageId;
		initWidget(MessagePanel.uiBinder.createAndBindUi(this));
	}

	public CheckBox getSelection() {
		return selection;
	}

	public Label getDate() {
		return date;
	}

	public Label getFrom() {
		return from;
	}

	public Label getSujet() {
		return sujet;
	}

	public TypeMessageStatutEnum getTypeMessageStatutEnum() {
		return typeMessageStatutEnum;
	}

	public void setTypeMessageStatutEnum(TypeMessageStatutEnum typeMessageStatutEnum) {
	  this.typeMessageStatutEnum = typeMessageStatutEnum;
  }

}