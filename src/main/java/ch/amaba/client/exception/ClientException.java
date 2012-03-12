package ch.amaba.client.exception;

import com.google.gwt.user.client.Window;

public class ClientException extends Exception {

	/**
   * 
   */
	private static final long serialVersionUID = 1L;

	public ClientException(final Throwable e, final String method) {
		super(e);
		if (e != null) {
			e.printStackTrace();
		}
		Window.alert("[" + method + "()] Une erreur est intervenue :" + e != null ? e.getMessage() : null);
	}

}
