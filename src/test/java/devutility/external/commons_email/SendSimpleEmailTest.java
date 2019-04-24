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

		Mail mail = new Mail();
		mail.setFromEmail(map.get("fromEmail"));
		mail.setToEmails(Arrays.asList(map.get("toEmails").split(",")));
		mail.setCopyEmails(Arrays.asList(map.get("copyEmails").split(",")));
		mail.setSubject("Test mail");
		mail.setContent("Hello world!");

		try {
			commonsEmailHelper.setDebug(true);
			commonsEmailHelper.send(mail);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(SendSimpleEmailTest.class);
	}
}