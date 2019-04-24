package devutility.external.commons_email.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

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

			for (String email : toEmails) {
				map.put(email, null);
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
		if (copyEmailMap == null && CollectionUtils.isNotEmpty(copyEmails)) {
			Map<String, String> map = new LinkedHashMap<String, String>();

			for (String email : copyEmails) {
				map.put(email, null);
			}

			return map;
		}

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
		if (bccEmailMap == null && CollectionUtils.isNotEmpty(bccEmails)) {
			Map<String, String> map = new LinkedHashMap<String, String>();

			for (String email : bccEmails) {
				map.put(email, null);
			}

			return map;
		}

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

	public void setHtmlContent(String content) {
		this.content = content;
		this.html = true;
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

	private void setToEmails(Email email) throws EmailException {
		Map<String, String> toEmailMap = getToEmailMap();

		if (toEmailMap != null) {
			for (Map.Entry<String, String> entry : toEmailMap.entrySet()) {
				email.addTo(entry.getKey(), entry.getValue(), EmailConstants.UTF_8);
			}
		}
	}

	private void setCopyEmails(Email email) throws EmailException {
		Map<String, String> copyEmailMap = getCopyEmailMap();

		if (copyEmailMap != null) {
			for (Map.Entry<String, String> entry : copyEmailMap.entrySet()) {
				email.addCc(entry.getKey(), entry.getValue(), EmailConstants.UTF_8);
			}
		}
	}

	private void setBccEmails(Email email) throws EmailException {
		Map<String, String> bccEmailMap = getBccEmailMap();

		if (bccEmailMap != null) {
			for (Map.Entry<String, String> entry : bccEmailMap.entrySet()) {
				email.addBcc(entry.getKey(), entry.getValue(), EmailConstants.UTF_8);
			}
		}
	}

	private void setAttachments(HtmlEmail email) throws EmailException {
		List<EmailAttachment> attachments = getAttachments();

		if (CollectionUtils.isNotEmpty(attachments)) {
			for (EmailAttachment attachment : attachments) {
				email.attach(attachment);
			}
		}
	}

	private void setEmail(Email email) throws EmailException {
		email.setFrom(getFromEmail(), getFromName(), EmailConstants.UTF_8);
		setToEmails(email);
		setCopyEmails(email);
		setBccEmails(email);
		email.setSubject(getSubject());
	}

	public SimpleEmail toSimpleEmail() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		setEmail(email);
		email.setMsg(getContent());
		return email;
	}

	public HtmlEmail toHtmlEmail() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		setEmail(email);
		setAttachments(email);
		email.setHtmlMsg(getContent());
		return email;
	}

	public Email toEmail() throws EmailException {
		if (isHtml()) {
			return toHtmlEmail();
		}

		return toSimpleEmail();
	}
}