/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.workbench.dialogs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Bundle;

/**
 * A class that provides processing for branding properties.
 * 
 */
final class ApplicationBranding {

	private static final String[] NO_MAPPINGS = new String[0];

	/**
	 * The text to show in an "about" actions for this product.
	 */
	public static final String ABOUT_TEXT = "aboutText"; //$NON-NLS-1$

	private static final String ABOUT_MAPPINGS = "$nl$/about.mappings"; //$NON-NLS-1$

	private static Map<Bundle, String[]> mappingsMap = new HashMap<>(4);

	/**
	 * The text to show in an "about" actions for this product.
	 * <p>
	 * The returned value will have {n} values substituted based on the current
	 * product's mappings regardless of the given product argument.
	 * </p>
	 */
	public static String getAboutText(IApplicationContext context) {
		String property = context.getBrandingProperty(ABOUT_TEXT);
		Bundle brandingBundle = context.getBrandingBundle();
		return extractValue(property, brandingBundle);
	}

	private static String extractValue(String property, Bundle definingBundle) {
		if (property == null) {
			return ""; //$NON-NLS-1$
		}
		if (property.indexOf('{') == -1) {
			return property;
		}
		String[] tempMappings = getMappings(definingBundle);
		/*
		 * Check if the mapping value is a system property, specified by '$' at the
		 * beginning and end of the string. If so, update the mappings array with the
		 * system property value.
		 */
		for (int i = 0; i < tempMappings.length; i++) {
			String nextString = tempMappings[i];
			int length = nextString.length();
			if (length > 2 && nextString.charAt(0) == '$' && nextString.charAt(length - 1) == '$') {
				String systemPropertyKey = nextString.substring(1, length - 1);
				// If system property is not set, insert an empty String
				tempMappings[i] = System.getProperty(systemPropertyKey, ""); //$NON-NLS-1$ ;
			}
		}

		return MessageFormat.format(property, (Object[]) tempMappings);
	}

	private static String[] loadMappings(Bundle definingBundle) {
		URL location = FileLocator.find(definingBundle, new Path(ABOUT_MAPPINGS));
		if (location == null) {
			return NO_MAPPINGS;
		}
		PropertyResourceBundle resources = null;
		try (InputStream is = location.openStream()) {
			resources = new PropertyResourceBundle(is);
		} catch (IOException e) {
			return NO_MAPPINGS;
		}
		List<String> mappingsList = new ArrayList<>();
		boolean found = true;
		int i = 0;
		while (found) {
			try {
				mappingsList.add(resources.getString(Integer.toString(i)));
			} catch (MissingResourceException e) {
				found = false;
			}
			i++;
		}
		String[] mappings = mappingsList.toArray(new String[mappingsList.size()]);
		mappingsMap.put(definingBundle, mappings);
		return mappings;
	}

	private static String[] getMappings(Bundle definingBundle) {
		String[] mappings = mappingsMap.get(definingBundle);
		if (mappings == null) {
			mappings = loadMappings(definingBundle);
		}
		if (mappings == null) {
			mappings = NO_MAPPINGS;
		}
		return mappings;
	}

}
