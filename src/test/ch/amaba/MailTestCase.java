package ch.amaba;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailTestCase {

	private ClassPathXmlApplicationContext beanFactory;

	public void setup() {
		beanFactory = new ClassPathXmlApplicationContext("application-context.xml");
	}

	@Test
	public void sendMail() {
	}
}
