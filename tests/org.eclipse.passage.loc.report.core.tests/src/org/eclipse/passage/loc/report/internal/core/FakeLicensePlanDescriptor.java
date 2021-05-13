/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core;

import java.util.List;

import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;

public final class FakeLicensePlanDescriptor implements LicensePlanDescriptor {

	private final String identifier;
	private final String name;

	public FakeLicensePlanDescriptor(String identifier, String name) {
		this.identifier = identifier;
		this.name = name;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends LicensePlanFeatureDescriptor> getLicensePlanFeatures() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends LicensePackDescriptor> getPersonal() {
		throw new UnsupportedOperationException("MUST be implemented for test purposes"); //$NON-NLS-1$
	}

	@Override
	public List<? extends FloatingLicensePackDescriptor> getFloating() {
		throw new UnsupportedOperationException("MUST be implemented for test purposes"); //$NON-NLS-1$
	}

}
