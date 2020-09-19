/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.ui.SelectLicensePlan;
import org.eclipse.passage.loc.internal.workbench.SelectRoot;
import org.eclipse.swt.widgets.Text;

public class ChosenLicensePlan extends TextLicenseData<LicensePlanDescriptor> {

	@Override
	protected String label() {
		return IssueLicensePageMessages.IssueLicenseRequestPage_lbl_license_plan;
	}

	@Override
	protected T select(Text control) {
		Optional<LicensePlanDescriptor> data = data();

		Collection<LicensePlanDescriptor> initial = data.map(Arrays::asList).orElseGet(Collections::emptyList);
		LicensePlanDescriptor selected = new SelectRoot<>(new SelectLicensePlan(context).get(), context).get()
				.orElse(null);
		return T;
	}

}
