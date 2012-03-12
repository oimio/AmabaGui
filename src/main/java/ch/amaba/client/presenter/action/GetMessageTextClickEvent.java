package ch.amaba.client.presenter.action;

import java.util.HashSet;
import java.util.Set;

import ch.amaba.client.presenter.MessagesPresenter;
import ch.amaba.client.presenter.impl.ChangerMessageStatutImpl;
import ch.amaba.client.utils.DateUtils;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.shared.MessageTextAction;
import ch.amaba.shared.MessageTextResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.gwtplatform.dispatch.shared.DispatchAsync;

/**
 * Handler sur le click d'un message :
 * <ul>
 * <li>- marque le message comme lu</li>
 * <li>- affiche le text du message</li>
 * </ul>
 * */
public class GetMessageTextClickEvent implements ClickHandler {

	private final DispatchAsync dispatcher;
	private final MessageDTO messageDTO;
	private final MessagesPresenter.MyView view;

	public GetMessageTextClickEvent(final DispatchAsync dispatcher, final MessageDTO messageDTO, final MessagesPresenter.MyView view) {
		this.dispatcher = dispatcher;
		this.messageDTO = messageDTO;
		this.view = view;
	}

	@Override
	public void onClick(ClickEvent event) {
		final Label sujet = (Label) event.getSource();
		// Marqué comme lu
		if (TypeMessageStatutEnum.NON_LU.equals(messageDTO.getTypeMessageStatutEnum())) {
			final Set<Long> ids = new HashSet<Long>();
			ids.add(messageDTO.getBusinessObjectId());
			ChangerMessageStatutImpl.process(dispatcher, ids, TypeMessageStatutEnum.LU);
		}
		// Afficher le text
		dispatcher.execute(new MessageTextAction(messageDTO.getBusinessObjectId()), new AsyncCallback<MessageTextResult>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(MessageTextResult result) {
				final MessageDTO message = result.getMessage();
				view.getMessageText().setText(message.getMessage());
				view.getMessageDate().setText(DateUtils.getDateTime(message.getDate()));
				sujet.removeStyleName("fontBold");
			}
		});

	}
}
