package devutility.external.commons_email;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.mail.EmailException;

import devutility.internal.test.BaseTest;
import devutility.internal.test.TestExecutor;
import devutility.internal.util.PropertiesUtils;

public class SendHtmlEmailTest extends BaseTest {
	@Override
	public void run() {
		CommonsEmailHelper helper = null;
		EmailModel emailModel = null;

		try {
			helper = CommonsEmailHelper.create("email.properties");
			helper.setDebug(true);

			emailModel = PropertiesUtils.toModel("email.properties", null, EmailModel.class);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		emailModel.setSubject("Test mail");
		emailModel.setContent("<h2>Hello world!<h2>");

		try {
			helper.sendHtmlEmail(emailModel);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(SendHtmlEmailTest.class);
	}
}