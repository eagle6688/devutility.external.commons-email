package devutility.external.commons_email;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

import devutility.external.commons_email.model.Mail;
import devutility.internal.test.TestExecutor;

public class SendHtmlEmailWithAttachmentTest extends BaseTestForCommonsEmail {
	@Override
	public void run() {
		List<String> toEmails = Arrays.asList("");
		List<EmailAttachment> attachments = new LinkedList<>();

		EmailAttachment emailAttachment = new EmailAttachment();
		emailAttachment.setPath("E:\\Test\\Test2.txt");
		attachments.add(emailAttachment);

		Mail mail = new Mail();
		mail.setFromName(fromName);
		mail.setFromEmail(fromEmail);
		mail.setToEmails(toEmails);
		mail.setSubject("Test mail");
		mail.setHtmlContent("<h2>Hello world!<h2>");
		mail.setAttachments(attachments);

		try {
			emailHelper.setDebug(true);
			println(emailHelper.send(mail));
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestExecutor.run(SendHtmlEmailWithAttachmentTest.class);
	}
}