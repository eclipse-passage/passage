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
package org.eclipse.passage.lic.equinox.restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.inspector.FeatureCase;
import org.eclipse.passage.lic.api.inspector.FeatureInspector;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.equinox.ApplicationConfigurations;
import org.eclipse.passage.lic.equinox.LicensingEquinox;

public class EquinoxRestrictions {

	public static Iterable<RestrictionVerdict> getFeatureVerdicts(String... featureIds) {
		FeatureInspector featureInspector = LicensingEquinox.getFeatureInspector();
		if (featureInspector == null) {
			LicensingConfiguration configuration = ApplicationConfigurations.getLicensingConfiguration();
			if (featureIds.length == 0) {
				String id = configuration.getProductIdentifier();
				return Collections.singletonList(RestrictionVerdicts.createConfigurationError(configuration, id));
			}
			List<RestrictionVerdict> errors = new ArrayList<>();
			for (String id : featureIds) {
				errors.add(RestrictionVerdicts.createConfigurationError(configuration, id));
			}
			return errors;
		}
		try (FeatureCase inspection = featureInspector.inspectFeatures(featureIds)) {
			return inspection.getRestrictions();
		}
	}

}
