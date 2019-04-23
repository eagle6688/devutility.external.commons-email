package devutility.external.commons_email.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailAttachment;

import devutility.internal.util.CollectionUtils;

/**
 * 
 * EmailModel
 * 
 * @author: Aldwin Su
 * @version: 2019-04-23 18:07:27
 */
public class Mail {
	private String fromEmail;
	private String fromName;
	private List<String> toEmails;

	/**
	 * Map for major receivers. Key is email, value is name of email.
	 */
	private Map<String, String> toEmailMap;

	private List<String> copyEmails;

	/**
	 * Map for copping receivers. Key is email, value is name of email.
	 */
	private Map<String, String> copyEmailMap;

	private List<String> bccEmails;

	/**
	 * Map for blind carbon copy receivers. Key is email, value is name of email.
	 */
	private Map<String, String> bccEmailMap;
	private String subject;
	private String content;
	private List<EmailAttachment> attachments;
	private boolean html;

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public List<String> getToEmails() {
		return toEmails;
	}

	public void setToEmails(List<String> toEmails) {
		this.toEmails = toEmails;
	}

	public Map<String, String> getToEmailMap() {
		if (toEmailMap == null && CollectionUtils.isNotEmpty(toEmails)) {
			Map<String, String> map = new LinkedHashMap<String, String>();

			for (String toEmail : toEmails) {
				map.put(toEmail, null);
			}

			return map;
		}

		return toEmailMap;
	}

	public void setToEmailMap(Map<String, String> toEmailMap) {
		this.toEmailMap = toEmailMap;
	}

	public List<String> getCopyEmails() {
		return copyEmails;
	}

	public void setCopyEmails(List<String> copyEmails) {
		this.copyEmails = copyEmails;
	}

	public Map<String, String> getCopyEmailMap() {
		return copyEmailMap;
	}

	public void setCopyEmailMap(Map<String, String> copyEmailMap) {
		this.copyEmailMap = copyEmailMap;
	}

	public List<String> getBccEmails() {
		return bccEmails;
	}

	public void setBccEmails(List<String> bccEmails) {
		this.bccEmails = bccEmails;
	}

	public Map<String, String> getBccEmailMap() {
		return bccEmailMap;
	}

	public void setBccEmailMap(Map<String, String> bccEmailMap) {
		this.bccEmailMap = bccEmailMap;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<EmailAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<EmailAttachment> attachments) {
		this.attachments = attachments;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}
}