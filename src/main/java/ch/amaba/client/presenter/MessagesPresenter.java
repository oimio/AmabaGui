package ch.amaba.client.presenter;

import java.util.HashSet;
import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.ui.composite.MessagePanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.shared.MessagesAction;
import ch.amaba.shared.MessagesResult;
import ch.amaba.shared.SupprimerDefinitivementAction;
import ch.amaba.shared.SupprimerDefinitivementResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * Modifier les données personnelles du compte.
 * 
 * @author ROG
 */
public class MessagesPresenter extends Presenter<MessagesPresenter.MyView, MessagesPresenter.MyProxy> {
	/**
	 * {@link MessagesPresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.messages)
	public interface MyProxy extends Proxy<MessagesPresenter>, Place {
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	/**
	 * {@link MessagesPresenter}'s view.
	 */
	public interface MyView extends View {
		FlexTable getMessagesTable();

		Button getSupprimerButton();

	}

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	@Inject
	public MessagesPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();

	}

	@Override
	protected void onReset() {
		super.onReset();
		dispatcher.execute(new MessagesAction(ContextUI.get().getMessageAction()), new AsyncCallback<MessagesResult>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(MessagesResult result) {
				final Set<MessageDTO> messages = result.getMessages();
				final FlexTable messagesTable = getView().getMessagesTable();
				messagesTable.clear();
				// La première ligne est le Header du tableau
				int row = 0;
				final MessagePanel messagePanel = new MessagePanel(null);
				messagePanel.getSelection().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						for (int row = 1; row < messagesTable.getRowCount(); row++) {
							final MessagePanel messagePanelRow = (MessagePanel) messagesTable.getWidget(row, 0);
							messagePanelRow.getSelection().setValue(messagePanel.getSelection().getValue());
						}
					}
				});
				messagePanel.getDate().setText("Date");
				messagePanel.getDate().addStyleName("table-header");
				messagePanel.getFrom().setText(TypeMessageStatutEnum.ENVOYE.equals(ContextUI.get().getMessageAction()) ? "Envoyé à" : "De");
				messagePanel.getFrom().addStyleName("table-header");
				messagePanel.getSujet().setText("Objet");
				messagePanel.getSujet().addStyleName("table-header");
				messagesTable.setWidget(row++, 0, messagePanel);
				for (final MessageDTO messageDTO : messages) {
					final MessagePanel messagePanelRow = new MessagePanel(messageDTO.getBusinessObjectId());
					messagePanelRow.getDate().setText(DateUtils.getDateTime(messageDTO.getDate()));
					messagePanelRow.getFrom().setText(messageDTO.getNomCorrespondant() + " " + messageDTO.getPrenomCorrespondant());
					messagePanelRow.getSujet().setText(messageDTO.getSujet());
					messagesTable.setWidget(row++, 0, messagePanelRow);
				}
				getView().getSupprimerButton().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						final Set<Long> ids = new HashSet<Long>();
						for (int row = 1; row < messagesTable.getRowCount(); row++) {
							final MessagePanel messagePanelRow = (MessagePanel) messagesTable.getWidget(row, 0);

							if (messagePanelRow.getSelection().getValue() == true) {
								ids.add(Long.valueOf(messagePanelRow.getMessageId()));
							}
						}
						dispatcher.execute(new SupprimerDefinitivementAction(ids, MessageDTO.class.getName()), new AsyncCallback<SupprimerDefinitivementResult>() {

							@Override
							public void onFailure(Throwable caught) {
								if (caught != null) {
									caught.printStackTrace();
									Window.alert(caught.getMessage());
								}
							}

							@Override
							public void onSuccess(SupprimerDefinitivementResult result) {
								Window.alert("Messages supprimés.");
							}

						});
					}
				});

			}
		});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}

}