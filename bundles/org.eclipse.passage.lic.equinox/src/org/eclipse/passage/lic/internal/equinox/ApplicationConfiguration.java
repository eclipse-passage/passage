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
package org.eclipse.passage.lic.internal.equinox;

import java.util.function.Supplier;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.internal.base.BaseLicensingConfiguration;

@SuppressWarnings("restriction")
public final class ApplicationConfiguration implements Supplier<LicensingConfiguration> {

	private final IApplicationContext context;

	public ApplicationConfiguration(IApplicationContext context) {
		this.context = context;
	}

	@Override
	public LicensingConfiguration get() {
		return new BaseLicensingConfiguration(//
				new ApplicationIdentifier(context).get(), //
				new ApplicationVersion(context).get());
	}

}
