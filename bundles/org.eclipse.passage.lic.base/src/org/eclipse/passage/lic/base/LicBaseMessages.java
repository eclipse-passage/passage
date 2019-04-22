package org.eclipse.passage.lic.base;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LicBaseMessages {
	private static final String ACCESS_BUNDLE_NAME = "org.eclipse.passage.lic.base.AccessMessages"; //$NON-NLS-1$
	private static final ResourceBundle ACCESS_RESOURCE_BUNDLE = ResourceBundle.getBundle(ACCESS_BUNDLE_NAME);
	private static final String CONDITIONS_BUNDLE_NAME = "org.eclipse.passage.lic.base.ConditionsMessages"; //$NON-NLS-1$
	static final ResourceBundle CONDITIONS_RESOURCE_BUNDLE = ResourceBundle.getBundle(CONDITIONS_BUNDLE_NAME);
	private static final String BASE_BUNDLE_NAME = "org.eclipse.passage.lic.base.BaseMessages"; //$NON-NLS-1$
	static final ResourceBundle BASE_RESOURCE_BUNDLE = ResourceBundle.getBundle(BASE_BUNDLE_NAME);

	private LicBaseMessages() {
	}

	public static String getAccessString(String key) {
		return getString(key, ACCESS_RESOURCE_BUNDLE);
	}

	public static String getConditionsString(String key) {
		return getString(key, CONDITIONS_RESOURCE_BUNDLE);
	}

	public static String getBaseString(String key) {
		return getString(key, BASE_RESOURCE_BUNDLE);
	}

	private static String getString(String key, ResourceBundle resourceBundle) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
