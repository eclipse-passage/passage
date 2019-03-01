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
package org.eclipse.passage.lic.base;

import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_NAME;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_VERSION;
import static org.eclipse.passage.lic.base.LicensingNamespaces.CAPABILITY_LICENSING_FEATURE;
import static org.eclipse.passage.lic.base.LicensingNamespaces.toLevelAttribute;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_IDENTIFIER;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_VERSION;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingProperties.toRestrictionLevelProperty;

import java.util.Collections;
import java.util.Map;

import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public class ConfigurationRequirements {

	private ConfigurationRequirements() {
		// block
	}

	public static BaseConfigurationRequirement extractFromCapability(Map<String, Object> attributes,
			Map<String, String> directives, Object source, LicensingConfiguration configuration) {
		Object feature = attributes.get(CAPABILITY_LICENSING_FEATURE);
		if (feature instanceof String) {
			String featureId = (String) feature;
			String version = LicensingVersions.toVersionValue(attributes.get(ATTRIBUTE_VERSION));
			String name = String.valueOf(attributes.get(ATTRIBUTE_NAME));
			String level = toLevelAttribute(attributes.get(ATTRIBUTE_LEVEL));
			return new BaseConfigurationRequirement(featureId, version, name, level, source, configuration);
		}
		return null;
	}

	public static BaseConfigurationRequirement extractFromProperties(Map<String, Object> properties, Object source,
			LicensingConfiguration configuration) {
		Object feature = properties.get(LICENSING_FEATURE_IDENTIFIER);
		if (feature instanceof String) {
			String featureId = (String) feature;
			String version = LicensingVersions.toVersionValue(properties.get(LICENSING_FEATURE_VERSION));
			String name = String.valueOf(properties.get(ATTRIBUTE_NAME));
			String level = toRestrictionLevelProperty(properties.get(LICENSING_RESTRICTION_LEVEL));
			return new BaseConfigurationRequirement(featureId, version, name, level, source, configuration);
		}
		return null;
	}

	public static BaseConfigurationRequirement createConfigurationError(String featureId, LicensingConfiguration configuration) {
		String name = "Licensing";
		return createConfigurationError(featureId, name, configuration);
	}

	public static BaseConfigurationRequirement createConfigurationError(String featureId, String name, LicensingConfiguration configuration) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		String version = LicensingVersions.VERSION_DEFAULT;
		String source = configuration.getProductIdentifier();
		return new BaseConfigurationRequirement(featureId, version, name, policy, source, configuration);
	}

	public static BaseConfigurationRequirement createError(String featureId, String version, String name,
			Object source, LicensingConfiguration configuration) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		return new BaseConfigurationRequirement(featureId, version, name, policy, source, configuration);
	}

	public static Iterable<ConfigurationRequirement> createErrorIterable(String featureId, String version, String name,
			Object source, LicensingConfiguration configuration) {
		return Collections.singletonList(createError(featureId, version, name, source, configuration));
	}

	public static BaseConfigurationRequirement createDefault(String featureId, String version, String name,
			Object source, LicensingConfiguration configuration) {
		String policy = LICENSING_RESTRICTION_LEVEL_DEFAULT;
		return new BaseConfigurationRequirement(featureId, version, name, policy, source, configuration);
	}

}
