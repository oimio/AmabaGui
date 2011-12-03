package ch.amaba.client.presenter;

import java.util.Set;

import ch.amaba.client.NameTokens;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.shared.MessagesAction;
import ch.amaba.shared.MessagesResult;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
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
		dispatcher.execute(new MessagesAction(), new AsyncCallback<MessagesResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// getView().setServerResponse("An error occured: " +
				// caught.getMessage());
				// getView().getMessagesButton().setEnabled(true);
			}

			@Override
			public void onSuccess(MessagesResult result) {
				final Set<MessageDTO> messages = result.getMessages();
				final FlexTable messagesTable = getView().getMessagesTable();
				// La première ligne est le Header du tableau
				int row = 1;
				for (final MessageDTO messageDTO : messages) {
					final CheckBox checkBox = new CheckBox();
					messagesTable.setWidget(row, 0, checkBox);

					final Label date = new Label(DateUtils.getDate(messageDTO.getDate()));
					messagesTable.setWidget(row, 1, date);

					final Label envoyeA = new Label(messageDTO.getNomCorrespondant() + " " + messageDTO.getPrenomCorrespondant());
					messagesTable.setWidget(row, 2, envoyeA);

					final Label sujet = new Label(messageDTO.getSujet());
					messagesTable.setWidget(row, 3, sujet);
					row++;
				}

			}
		});
	}

	@Override
	protected void onReset() {
		super.onReset();

	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, DockLayoutPagePresenter.TYPE_SetMainContent, this);
	}

}