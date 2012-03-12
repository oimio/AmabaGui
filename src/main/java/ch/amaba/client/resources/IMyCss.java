package ch.amaba.client.resources;

import com.google.gwt.resources.client.CssResource;

public interface IMyCss extends CssResource {

	public String header();

	public String menu();

	public String menuHeader();

	/** Tableau des messages */
	public String messageRow();

	public String messageRowCheckBox();

	public String messageRowDate();

	public String messageRowFrom();

	public String messageText();

	public String messageDate();

	public String messageTextPanel();

	public String profileDetailleInfo();

	public String amisTitle();

	public String blockColor();

	public String title();

	/** Le css pour un setting (value + bouton supprimer) */
	public String settingValue();
}