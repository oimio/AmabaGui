package ch.amaba.client.ui.composite;

import com.google.gwt.user.client.ui.Label;

public class MyLabel extends Label {

	public MyLabel(String text, String style) {
		super(text);
		addStyleName(style);
	}
}
