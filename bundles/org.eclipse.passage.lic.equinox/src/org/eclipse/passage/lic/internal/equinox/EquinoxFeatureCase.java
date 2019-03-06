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
package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.passage.lic.runtime.LicensingRequirement;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.passage.lic.runtime.inspector.FeatureCase;

public class EquinoxFeatureCase implements FeatureCase {
	
	private final EquinoxFeatureInspector inspector;
	
	private final Iterable<String> featureIdentifiers;
	
	EquinoxFeatureCase(EquinoxFeatureInspector inspector, Iterable<String> features) {
		this.inspector = inspector;
		this.featureIdentifiers = features;
	}
	
	public Iterable<String> getFeatureIdentifiers() {
		return featureIdentifiers;
	}

	@Override
	public Iterable<LicensingRequirement> getRequirements() {
		return inspector.getRequirements(featureIdentifiers);
	}

	@Override
	public Iterable<RestrictionVerdict> getRestrictions() {
		return inspector.getRestrictions(featureIdentifiers);
	}

	@Override
	public void close() {
		inspector.close(this);
	}

}
