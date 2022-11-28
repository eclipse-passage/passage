/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.licenses.model.described;

import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;

public final class DescribedPersonalLicense implements Supplier<String> {

	private final PersonalLicensePackDescriptor pack;

	public DescribedPersonalLicense(PersonalLicensePackDescriptor license) {
		this.pack = license;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		out.append(new DescribedLicenseRequisites(pack.getLicense()).get());
		out.append(new DescribedPersonalFeatureGrants(pack.getGrants()).get());
		return out.toString();
	}

}
