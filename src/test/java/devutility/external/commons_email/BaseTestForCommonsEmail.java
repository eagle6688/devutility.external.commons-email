package devutility.external.commons_email;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import devutility.internal.test.BaseTest;
import devutility.internal.util.PropertiesUtils;

public abstract class BaseTestForCommonsEmail extends BaseTest {
	protected EmailHelper emailHelper;
	protected String fromEmail = "tester@test.com";
	protected String fromName = "Tester";

	public BaseTestForCommonsEmail() {
		Properties properties = null;

		try {
			properties = PropertiesUtils.getPropertiesFromResource("email.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			emailHelper = EmailHelper.create(properties, "email");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}