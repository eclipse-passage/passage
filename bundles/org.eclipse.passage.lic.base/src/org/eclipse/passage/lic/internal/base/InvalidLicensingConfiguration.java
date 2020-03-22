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
package org.eclipse.passage.lic.internal.base;

import org.eclipse.passage.lic.api.LicensingConfiguration;

public final class InvalidLicensingConfiguration implements LicensingConfiguration {

	private final BaseLicensingConfiguration delegate;

	public InvalidLicensingConfiguration() {
		delegate = new BaseLicensingConfiguration(//
				"org.eclipse.passage.lic.api.configuration.invalid", //$NON-NLS-1$ ,
				"0.0.0"); //$NON-NLS-1$
	}

	@Override
	public String getProductIdentifier() {
		return delegate.getProductIdentifier();
	}

	@Override
	public String getProductVersion() {
		return delegate.getProductVersion();
	}

	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

}
