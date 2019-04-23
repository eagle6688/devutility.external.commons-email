package devutility.external.commons_email.model;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;

import devutility.internal.lang.StringUtils;

/**
 * 
 * EmailProperties
 * 
 * @author: Aldwin Su
 * @version: 2019-04-23 17:23:46
 */
public class EmailProperties {
	private String host;
	private int port;
	private String userName;
	private String password;
	private boolean sslOnConnect;

	public EmailProperties() {
	}

	public EmailProperties(String host, int port, String userName, String password) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSSLOnConnect() {
		return sslOnConnect;
	}

	public void setSSLOnConnect(boolean sslOnConnect) {
		this.sslOnConnect = sslOnConnect;
	}

	/**
	 * Set properties of Email object.
	 * @param email org.apache.commons.mail.Email object.
	 */
	public void setEmail(Email email) {
		email.setHostName(getHost());
		email.setSmtpPort(getPort());
		email.setAuthenticator(defaultAuthenticator());
		email.setSSLOnConnect(isSSLOnConnect());
	}

	/**
	 * Create a DefaultAuthenticator object.
	 * @return DefaultAuthenticator
	 */
	public DefaultAuthenticator defaultAuthenticator() {
		if (StringUtils.isNotEmpty(getUserName())) {
			return new DefaultAuthenticator(getUserName(), getPassword());
		}

		return null;
	}
}