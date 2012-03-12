package ch.amaba.client.presenter.impl;

import java.util.Set;

import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.shared.ChangerStatutMessageAction;
import ch.amaba.shared.ChangerStatutMessageResult;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;

public class ChangerMessageStatutImpl {

	public static void process(final DispatchAsync dispatcher, Set<Long> idsMessage, TypeMessageStatutEnum typeMessageStatutEnum) {
		dispatcher.execute(new ChangerStatutMessageAction(idsMessage, typeMessageStatutEnum), new AsyncCallback<ChangerStatutMessageResult>() {
			@Override
			public void onFailure(Throwable caught) {
				if (caught != null) {
					caught.printStackTrace();
					Window.alert(caught.getMessage());
				}
			}

			@Override
			public void onSuccess(ChangerStatutMessageResult result) {
				if (TypeMessageStatutEnum.SUPPRIME.equals(result.getTypeMessageStatutEnum())) {
					Window.alert("Messages supprimés.");
				}
			}
		});
	}
}
