/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.base.requirements;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_IDENTIFIER;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_NAME;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_VERSION;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL;

import java.util.Objects;

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;

class BaseLicensingRequirement implements LicensingRequirement {

	private final String featureIdentifier;
	private final String featureVersion;
	private final String featureName;
	private final String featureProvider;
	private final String restrictionLevel;
	private final Object source;

	BaseLicensingRequirement(String id, String version, String name, String provider, String level, Object source) {
		this.featureIdentifier = id;
		this.featureVersion = version;
		this.featureName = name;
		this.featureProvider = provider;
		this.restrictionLevel = level;
		this.source = source;
	}

	@Override
	public String getFeatureProvider() {
		return featureProvider;
	}

	@Override
	public String getFeatureName() {
		return featureName;
	}

	@Override
	public String getFeatureVersion() {
		return featureVersion;
	}

	@Override
	public String getFeatureIdentifier() {
		return featureIdentifier;
	}

	@Override
	public String getRestrictionLevel() {
		return restrictionLevel;
	}

	@Override
	public Object getRequirementSource() {
		return source;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LICENSING_FEATURE_IDENTIFIER).append('=').append(featureIdentifier).append(';');
		sb.append(LICENSING_FEATURE_VERSION).append('=').append(featureVersion).append(';');
		sb.append(LICENSING_FEATURE_NAME).append('=').append(featureName).append(';');
		sb.append(LICENSING_RESTRICTION_LEVEL).append('=').append(restrictionLevel).append(';');
		sb.append("source").append('=').append(source).append(';'); //$NON-NLS-1$
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(featureIdentifier, featureName, featureProvider, featureVersion, restrictionLevel, source);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseLicensingRequirement other = (BaseLicensingRequirement) obj;
		if (!Objects.equals(featureIdentifier, other.featureIdentifier)) {
			return false;
		}
		if (!Objects.equals(featureName, other.featureName)) {
			return false;
		}
		if (!Objects.equals(featureProvider, other.featureProvider)) {
			return false;
		}
		if (!Objects.equals(featureVersion, other.featureVersion)) {
			return false;
		}
		if (!Objects.equals(restrictionLevel, other.restrictionLevel)) {
			return false;
		}
		if (!Objects.equals(source, other.source)) {
			return false;
		}
		return true;
	}

}
