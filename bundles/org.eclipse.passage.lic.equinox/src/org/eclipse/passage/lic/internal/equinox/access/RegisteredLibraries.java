/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.access;

import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.base.access.DelegatedLicensingService;
import org.eclipse.passage.lic.internal.equinox.ServiceExtensions;

@SuppressWarnings("restriction")
public final class RegisteredLibraries implements Supplier<List<DelegatedLicensingService>> {

	@Override
	public List<DelegatedLicensingService> get() {
		return new ServiceExtensions<DelegatedLicensingService>(//
				"org.eclipse.passage.lic.base", //$NON-NLS-1$
				"library", //$NON-NLS-1$
				DelegatedLicensingService.class)//
						.get();
	}

}
