package devutility.external.commons_email;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import devutility.internal.test.BaseTest;
import devutility.internal.util.PropertiesUtils;

public abstract class BaseTestForCommonsEmail extends BaseTest {
	protected Properties properties = PropertiesUtils.getPropertiesFromResource("email.properties");
	protected EmailHelper emailHelper;
	protected String fromEmail = "tester@test.com";
	protected String fromName = "Tester";

	public BaseTestForCommonsEmail() {
		try {
			emailHelper = EmailHelper.create(properties, "email");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}