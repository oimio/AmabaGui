/**
 * Copyright 2011 Amaba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ch.amaba.client;

import ch.amaba.client.presenter.ResponsePresenter;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author ROG
 */
public class ResponseView extends ViewImpl implements ResponsePresenter.MyView {

	private static String html = "<h1>Remote Procedure Call</h1>\n" + "<table align=\"center\">\n" + "  <tr>\n"
	    + "    <td style=\"font-weight:bold;\">Sending name to server:</td>\n" + "  </tr>\n" + "  <tr>\n" + "    <td id=\"textToServerContainer\"></td>\n"
	    + "  </tr>\n" + "  <tr>\n" + "    <td style=\"font-weight:bold;\">Server replies:</td>\n" + "  </tr>\n" + "  <tr>\n"
	    + "    <td id=\"serverResponseContainer\"></td>\n" + "  </tr>\n" + "  <tr>\n" + "    <td id=\"closeButton\"></td>\n" + "  </tr>\n" + "</table>\n";

	HTMLPanel panel = new HTMLPanel(ResponseView.html);

	private final Button closeButton;
	private final HTML serverResponseLabel;
	private final Label textToServerLabel;

	@Inject
	public ResponseView() {
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		textToServerLabel = new Label();
		serverResponseLabel = new HTML();

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		panel.add(closeButton, "closeButton");
		panel.add(textToServerLabel, "textToServerContainer");
		panel.add(serverResponseLabel, "serverResponseContainer");
	}

	public Widget asWidget() {
		return panel;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public void setServerResponse(String serverResponse) {
		serverResponseLabel.setHTML(serverResponse);
	}

	public void setTextToServer(String textToServer) {
		textToServerLabel.setText(textToServer);
	}

}
