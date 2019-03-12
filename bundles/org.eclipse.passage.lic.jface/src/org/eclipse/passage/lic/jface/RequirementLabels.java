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
package org.eclipse.passage.lic.jface;

import java.util.Iterator;

import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;

public class RequirementLabels {

	public static final String PROVIDER_NOT_AVAILABLE = "N/A";
	public static final String NAME_NOT_AVAILABLE = "N/A";

	public static String getFeatureProvider(Iterable<LicensingRequirement> requirements) {
		if (requirements == null) {
			return PROVIDER_NOT_AVAILABLE;
		}
		Iterator<LicensingRequirement> iterator = requirements.iterator();
		if (iterator.hasNext()) {
			return getFeatureProvider(iterator.next());
		}
		return PROVIDER_NOT_AVAILABLE;
	}

	public static String getFeatureProvider(LicensingRequirement requirement) {
		if (requirement == null) {
			return PROVIDER_NOT_AVAILABLE;
		}
		String value = requirement.getFeatureProvider();
		if (value == null || value.trim().length() == 0) {
			return PROVIDER_NOT_AVAILABLE;
		}
		return value;
	}

	public static String getFeatureName(Iterable<LicensingRequirement> requirements) {
		if (requirements == null) {
			return NAME_NOT_AVAILABLE;
		}
		Iterator<LicensingRequirement> iterator = requirements.iterator();
		if (iterator.hasNext()) {
			return getFeatureName(iterator.next());
		}
		return NAME_NOT_AVAILABLE;
	}

	public static String getFeatureName(LicensingRequirement requirement) {
		if (requirement == null) {
			return NAME_NOT_AVAILABLE;
		}
		String value = requirement.getFeatureName();
		if (value == null || value.trim().length() == 0) {
			return NAME_NOT_AVAILABLE;
		}
		return value;
	}

}
