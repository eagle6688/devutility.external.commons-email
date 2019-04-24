package devutility.external.commons_email;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;

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
		email.setCharset(EmailConstants.UTF_8);
		email.setSentDate(new Date());
		email.setDebug(isDebug());
	}

	public String send(Mail mail) throws EmailException {
		Email email = mail.toEmail();
		setEmail(email);
		return email.send();
	}

	public static EmailHelper create(Properties properties, String prefix) throws NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		EmailProperties emailProperties = PropertiesUtils.toModel(properties, prefix, EmailProperties.class);

		if (emailProperties == null) {
			return null;
		}

		return new EmailHelper(emailProperties);
	}
}