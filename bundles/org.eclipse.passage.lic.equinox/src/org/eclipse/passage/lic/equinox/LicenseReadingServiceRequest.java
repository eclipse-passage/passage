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

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.conditions.mining.BaseLicenseReadingService;

/**
 * @since 2.1
 */
public final class LicenseReadingServiceRequest implements Supplier<ServiceInvocationResult<LicenseReadingService>> {

	private final EquinoxFrameworkAware<?> delegate;

	public LicenseReadingServiceRequest() {
		this(new SuppliedFrameworkAware());
	}

	public LicenseReadingServiceRequest(EquinoxFrameworkAware<?> delegate) {
		this.delegate = delegate;
	}

	@Override
	public ServiceInvocationResult<LicenseReadingService> get() {
		return delegate.withFrameworkService(framework -> new BaseServiceInvocationResult<>(//
				new BaseLicenseReadingService(//
						framework.product(), //
						framework.accessCycleConfiguration().miningEquipment()//
				)//
		));
	}

}
