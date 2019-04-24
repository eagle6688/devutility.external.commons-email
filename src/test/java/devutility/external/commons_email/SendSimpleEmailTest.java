package devutility.external.commons_email;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.mail.EmailException;

import devutility.external.commons_email.model.Mail;
import devutility.internal.test.TestExecutor;

public class SendSimpleEmailTest extends BaseTestForCommonsEmail {
	@Override
	public void run() {
		List<String> toEmails = Arrays.asList("");
		List<String> copyEmails = Arrays.asList("");
		List<String> bccEmails = Arrays.asList("");

		Mail mail = new Mail();
		mail.setFromName(fromName);
		mail.setFromEmail(fromEmail);
		mail.setToEmails(toEmails);
		mail.setCopyEmails(copyEmails);
		mail.setBccEmails(bccEmails);
		mail.setSubject("Test mail 测试邮件");
		mail.setContent("Hello world!测试邮件！");

		try {
			emailHelper.setDebug(true);
			emailHelper.send(mail);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(SendSimpleEmailTest.class);
	}
}