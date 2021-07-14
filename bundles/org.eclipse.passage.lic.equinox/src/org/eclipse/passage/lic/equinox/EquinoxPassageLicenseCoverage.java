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
package org.eclipse.passage.lic.equinox;

import org.eclipse.passage.lic.api.PassageLicenseCoverage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.access.Access;

public final class EquinoxPassageLicenseCoverage implements PassageLicenseCoverage {

	private final FrameworkAware<?> delegate;

	public EquinoxPassageLicenseCoverage() {
		this(new SuppliedFrameworkAware());
	}

	public EquinoxPassageLicenseCoverage(FrameworkAware<?> delegate) {
		this.delegate = delegate;
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assess() {
		return delegate.withFrameworkService(framework -> new Access(framework).assess());
	}

}
