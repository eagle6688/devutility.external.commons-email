package devutility.external.commons_email;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import devutility.external.commons_email.model.Mail;
import devutility.external.commons_email.model.EmailProperties;
import devutility.internal.util.PropertiesUtils;

/**
 * 
 * EmailHelper
 * 
 * @author: Aldwin Su
 * @version: 2019-04-23 17:39:22
 */
public class EmailHelper {
	private EmailProperties emailProperties;
	private boolean debug;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public EmailHelper() {
	}

	public EmailHelper(EmailProperties emailProperties) {
		this.emailProperties = emailProperties;
	}

	public EmailHelper(String host, int port, String userName, String password) {
		this(new EmailProperties(host, port, userName, password));
	}

	private void setEmail(Email email) {
		emailProperties.setEmail(email);
		email.setCharset("UTF-8");
		email.setSentDate(new Date());
		email.setDebug(isDebug());
	}

	private void setEmail(Email email, Mail emailModel) throws EmailException {
		email.setFrom(emailModel.getFromEmail(), emailModel.getFromName(), "utf-8");
		setToEmails(email, emailModel);
		setCopyEmails(email, emailModel);
		email.setSubject(emailModel.getSubject());
	}

	private void setToEmails(Email email, Mail emailModel) throws EmailException {
		if (emailModel.getToEmailMap() != null) {
			for (Map.Entry<String, String> entry : emailModel.getToEmailMap().entrySet()) {
				email.addTo(entry.getKey(), entry.getValue(), "utf-8");
			}

			return;
		}

		if (emailModel.getToEmails() != null) {
			for (String mail : emailModel.getToEmails()) {
				email.addTo(mail);
			}
		}
	}

	private void setCopyEmails(Email email, Mail emailModel) throws EmailException {
		if (emailModel.getCopyEmailMap() != null) {
			for (Map.Entry<String, String> entry : emailModel.getCopyEmailMap().entrySet()) {
				email.addCc(entry.getKey(), entry.getValue(), "utf-8");
			}

			return;
		}

		if (emailModel.getCopyEmails() != null) {
			for (String mail : emailModel.getCopyEmails()) {
				email.addCc(mail);
			}
		}
	}

	public void sendSimpleEmail(Mail emailModel) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		setEmail(email);
		setEmail(email, emailModel);
		email.setMsg(emailModel.getContent());
		email.send();
	}

	public void sendHtmlEmail(Mail emailModel) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		setEmail(email);
		setEmail(email, emailModel);
		email.setHtmlMsg(emailModel.getContent());
		email.send();
	}

	public static EmailHelper create(Properties properties, String prefix) throws NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		EmailProperties emailProperties = PropertiesUtils.toModel(properties, prefix, EmailProperties.class);

		if (emailProperties == null) {
			return null;
		}

		return new EmailHelper(emailProperties);
	}
}