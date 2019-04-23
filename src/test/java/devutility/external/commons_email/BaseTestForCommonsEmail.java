package devutility.external.commons_email;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import devutility.internal.test.BaseTest;
import devutility.internal.util.PropertiesUtils;

public abstract class BaseTestForCommonsEmail extends BaseTest {
	protected Properties properties = PropertiesUtils.getProperties("email.properties");
	protected EmailHelper emailHelper;

	public BaseTestForCommonsEmail() {
		try {
			emailHelper = EmailHelper.create(properties, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}