/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base.requirements;

import static org.eclipse.passage.lic.base.LicensingNamespaces.*;
import static org.eclipse.passage.lic.base.LicensingProperties.*;

import java.util.Collections;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;

public class ConfigurationRequirements {

	private ConfigurationRequirements() {
		// block
	}

	public static BaseConfigurationRequirement extractFromCapability(String bundleName, String bundleVendor,
			Map<String, Object> attributes, Map<String, String> directives, Object source) {
		Object feature = attributes.get(CAPABILITY_LICENSING_FEATURE);
		if (feature instanceof String) {
			String featureId = (String) feature;
			String version = LicensingVersions.toVersionValue(attributes.get(ATTRIBUTE_VERSION));
			String name = getStringValue(attributes, ATTRIBUTE_NAME, bundleName);
			if (name == null) {
				name = featureId;
			}
			String provider = getStringValue(attributes, ATTRIBUTE_PROVIDER, bundleVendor);
			String level = toLevelAttribute(attributes.get(ATTRIBUTE_LEVEL));
			return new BaseConfigurationRequirement(featureId, version, name, provider, level, source);
		}
		return null;
	}

	public static BaseConfigurationRequirement extractFromProperties(String bundleName, String bundleVendor,
			Map<String, Object> properties, Object source) {
		Object feature = properties.get(LICENSING_FEATURE_IDENTIFIER);
		if (feature instanceof String) {
			String featureId = (String) feature;
			String version = LicensingVersions.toVersionValue(properties.get(LICENSING_FEATURE_VERSION));
			String name = getStringValue(properties, LICENSING_FEATURE_NAME, bundleName);
			if (name == null) {
				name = featureId;
			}
			String provider = getStringValue(properties, LICENSING_FEATURE_PROVIDER, bundleVendor);
			if (provider == null) {
				provider = "";
			}
			String level = toRestrictionLevelProperty(properties.get(LICENSING_RESTRICTION_LEVEL));
			return new BaseConfigurationRequirement(featureId, version, name, provider, level, source);
		}
		return null;
	}

	public static BaseConfigurationRequirement createConfigurationError(String featureId, Object source) {
		String name = LICENSING_FEATURE_NAME_DEFAULT;
		String provider = LICENSING_FEATURE_PROVIDER_DEFAULT;
		return createConfigurationError(featureId, name, provider, source);
	}

	public static BaseConfigurationRequirement createConfigurationError(String featureId, String name, String provider,
			Object source) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		String version = LicensingVersions.VERSION_DEFAULT;
		return new BaseConfigurationRequirement(featureId, version, name, provider, policy, source);
	}

	public static BaseConfigurationRequirement createError(String featureId, String version, String name,
			String provider, Object source) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		return new BaseConfigurationRequirement(featureId, version, name, provider, policy, source);
	}

	public static Iterable<LicensingRequirement> createErrorIterable(String featureId, String version, String name,
			String provider, Object source) {
		return Collections.singletonList(createError(featureId, version, name, provider, source));
	}

	public static BaseConfigurationRequirement createDefault(String featureId, String version, String name,
			String provider, Object source) {
		String policy = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		return new BaseConfigurationRequirement(featureId, version, name, provider, policy, source);
	}

	private static String getStringValue(Map<String, Object> map, String key, String empty) {
		Object object = map.get(key);
		if (object instanceof String) {
			return (String) object;
		} else {
			return empty;
		}
	}

}
