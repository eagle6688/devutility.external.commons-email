package devutility.external.commons_email;

import org.apache.commons.mail.EmailException;

import devutility.external.commons_email.model.Mail;
import devutility.internal.test.TestExecutor;

public class SendHtmlEmailTest extends BaseTestForCommonsEmail {
	@Override
	public void run() {
		Mail emailModel = new Mail();
		emailModel.setSubject("Test mail");
		emailModel.setContent("<h2>Hello world!<h2>");

		try {
			emailHelper.setDebug(true);
			emailHelper.sendHtmlEmail(emailModel);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(SendHtmlEmailTest.class);
	}
}