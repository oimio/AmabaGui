package ch.amaba.client.presenter;

import java.util.HashSet;
import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.context.ContextUI;
import ch.amaba.client.presenter.action.ChangeMessageStatutClickEvent;
import ch.amaba.client.presenter.action.GetMessageTextClickEvent;
import ch.amaba.client.ui.composite.MessagePanel;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.shared.MessagesAction;
import ch.amaba.shared.MessagesResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
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

		HTMLPanel getMessageTextPanel();

		Label getMessageText();

		Label getMessageDate();

	}

	private final PlaceManager placeManager;

	private final DispatchAsync dispatcher;

	@Inject
	public MessagesPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	FlexTable messagesTable = null;

	/**
	 * Retourne la liste des ids sélectionnés (checkbox selected).
	 * */
	public Set<Long> getSelectionIds() {
		final Set<Long> ids = new HashSet<Long>();
		for (int row = 1; row < messagesTable.getRowCount(); row++) {
			final MessagePanel messagePanelRow = (MessagePanel) messagesTable.getWidget(row, 0);

			if (messagePanelRow.getSelection().getValue() == true) {
				ids.add(Long.valueOf(messagePanelRow.getMessageId()));
			}
		}
		return ids;
	}

	@Override
	protected void onBind() {
		super.onBind();
		messagesTable = getView().getMessagesTable();
		getView().getSupprimerButton().addClickHandler(new ChangeMessageStatutClickEvent(dispatcher, getSelectionIds(), getView(), TypeMessageStatutEnum.SUPPRIME));
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

				messagesTable.clear();
				// La première ligne est le Header du tableau
				int row = 0;
				final MessagePanel messagePanelHeader = new MessagePanel(null);
				messagePanelHeader.getSelection().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						for (int row = 1; row < messagesTable.getRowCount(); row++) {
							final MessagePanel messagePanelRow = (MessagePanel) messagesTable.getWidget(row, 0);
							if (messagePanelRow != null) {
								messagePanelRow.getSelection().setValue(messagePanelHeader.getSelection().getValue());
							}
						}
					}
				});
				messagePanelHeader.getDate().setText("Date");
				messagePanelHeader.getDate().addStyleName("table-header");
				messagePanelHeader.getFrom().setText(TypeMessageStatutEnum.ENVOYE.equals(ContextUI.get().getMessageAction()) ? "Envoyé à" : "De");
				messagePanelHeader.getFrom().addStyleName("table-header");
				messagePanelHeader.getSujet().setText("Objet");
				messagePanelHeader.getSujet().addStyleName("table-header");

				messagesTable.setWidget(row++, 0, messagePanelHeader);
				for (final MessageDTO messageDTO : messages) {
					final MessagePanel messagePanelRow = new MessagePanel(messageDTO.getBusinessObjectId());
					messagePanelRow.getDate().setText(DateUtils.getDateTime(messageDTO.getDate()));
					messagePanelRow.getFrom().setText(messageDTO.getNomCorrespondant() + " " + messageDTO.getPrenomCorrespondant());
					messagePanelRow.getSujet().setText(messageDTO.getSujet());
					if (TypeMessageStatutEnum.NON_LU.equals(messageDTO.getTypeMessageStatutEnum())) {
						messagePanelRow.getSujet().addStyleName("fontBold");
					}
					messagePanelRow.getSujet().addClickHandler(new GetMessageTextClickEvent(dispatcher, messageDTO, getView()));
					messagePanelRow.setTypeMessageStatutEnum(messageDTO.getTypeMessageStatutEnum());
					messagesTable.setWidget(row++, 0, messagePanelRow);
				}
			}
		});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}

}