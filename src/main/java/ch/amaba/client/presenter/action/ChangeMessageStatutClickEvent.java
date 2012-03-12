package ch.amaba.client.presenter.action;

import java.util.Set;

import ch.amaba.client.presenter.MessagesPresenter;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtplatform.dispatch.shared.DispatchAsync;

public class ChangeMessageStatutClickEvent implements ClickHandler {

	private final DispatchAsync dispatcher;
	private final Set<Long> idsMessage;
	private final MessagesPresenter.MyView view;
	private final TypeMessageStatutEnum typeMessageStatutEnum;

	public ChangeMessageStatutClickEvent(final DispatchAsync dispatcher, final Set<Long> idsMessage, final MessagesPresenter.MyView view,
	    final TypeMessageStatutEnum typeMessageStatutEnum) {
		this.dispatcher = dispatcher;
		this.idsMessage = idsMessage;
		this.view = view;
		this.typeMessageStatutEnum = typeMessageStatutEnum;
	}

	@Override
	public void onClick(ClickEvent event) {

	}

}
