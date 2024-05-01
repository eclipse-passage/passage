/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.license;

import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.loc.report.internal.core.license.LicenseStorage;

final class LicensePlans implements Supplier<LicensePlan[]> {

	private final LicenseStorage storage;

	public LicensePlans(LicenseStorage storage) {
		this.storage = storage;
	}

	@Override
	public LicensePlan[] get() {
		return storage.plans().toArray(new LicensePlan[0]);
	}

}
