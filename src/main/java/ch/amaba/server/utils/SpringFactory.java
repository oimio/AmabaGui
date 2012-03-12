package ch.amaba.server.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;

import ch.amaba.dao.IAmabaDao;
import ch.amaba.dao.IMessagesDAO;

public class SpringFactory {

	private static SpringFactory INSTANCE;
	private final ClassPathXmlApplicationContext beanFactory;
	private final IAmabaDao dao;
	private MailSender mailSender;
	private final IMessagesDAO messageDAO;

	public static SpringFactory get() {
		if (SpringFactory.INSTANCE == null) {
			SpringFactory.INSTANCE = new SpringFactory();
		}
		return SpringFactory.INSTANCE;
	}

	private SpringFactory() {
		beanFactory = new ClassPathXmlApplicationContext("application-context.xml");
		dao = (IAmabaDao) beanFactory.getBean("amabaDao");
		messageDAO = (IMessagesDAO) beanFactory.getBean("messageDAO");
	}

	public MailSender getMailSender() {
		return (MailSender) beanFactory.getBean("mailSender");
	}

	public IAmabaDao getDao() {
		return dao;
	}

	public IMessagesDAO getMessageDAO() {
		return messageDAO;
	}
}
