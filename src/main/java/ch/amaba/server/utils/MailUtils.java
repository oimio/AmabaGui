package ch.amaba.server.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailUtils {
	private static MailUtils INSTANCE;
	private final MailSender mailSender;

	private MailUtils() {
		mailSender = SpringFactory.get().getMailSender();
	}

	public static MailUtils get() {
		if (MailUtils.INSTANCE == null) {
			MailUtils.INSTANCE = new MailUtils();
		}
		return MailUtils.INSTANCE;
	}

	public void sendMail(String sujet, String message, String to) {
		final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(sujet);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom("test@test.com");
		simpleMailMessage.setText("<b>Cher membre</b><br/><br/>Toute l'équipe");
		try {
			mailSender.send(simpleMailMessage);
		} catch (final MailException e) {
			e.printStackTrace();
		}
	}
}
