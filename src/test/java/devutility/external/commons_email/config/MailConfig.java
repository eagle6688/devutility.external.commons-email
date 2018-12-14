package devutility.external.commons_email.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import devutility.internal.util.PropertiesUtils;

public class MailConfig {
	public static final String configFile = "email.properties";

	public static Map<String, String> get() {
		Properties properties = PropertiesUtils.getProperties(configFile);

		Map<String, String> map = new HashMap<String, String>();
		map.put("host", PropertiesUtils.getProperty(properties, "host"));
		map.put("port", PropertiesUtils.getProperty(properties, "port"));
		map.put("userName", PropertiesUtils.getProperty(properties, "userName"));
		map.put("password", PropertiesUtils.getProperty(properties, "password"));
		map.put("fromEmail", PropertiesUtils.getProperty(properties, "fromEmail"));
		map.put("toEmails", PropertiesUtils.getProperty(properties, "toEmails"));
		map.put("copyEmails", PropertiesUtils.getProperty(properties, "copyEmails"));
		return map;
	}
}