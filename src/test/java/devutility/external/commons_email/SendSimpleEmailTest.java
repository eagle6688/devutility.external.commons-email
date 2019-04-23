package devutility.external.commons_email;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import devutility.external.commons_email.config.MailConfig;
import devutility.external.commons_email.model.Mail;
import devutility.internal.test.BaseTest;
import devutility.internal.test.TestExecutor;

public class SendSimpleEmailTest extends BaseTest {
	@Override
	public void run() {
		Map<String, String> map = MailConfig.get();
		int port = Integer.valueOf(map.get("port"));
		EmailHelper commonsEmailHelper = new EmailHelper(map.get("host"), port, map.get("userName"), map.get("password"));

		Mail emailModel = new Mail();
		emailModel.setFromEmail(map.get("fromEmail"));
		emailModel.setToEmails(Arrays.asList(map.get("toEmails").split(",")));
		emailModel.setCopyEmails(Arrays.asList(map.get("copyEmails").split(",")));
		emailModel.setSubject("Test mail");
		emailModel.setContent("Hello world!");

		try {
			commonsEmailHelper.setDebug(true);
			commonsEmailHelper.sendSimpleEmail(emailModel);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(SendSimpleEmailTest.class);
	}
}