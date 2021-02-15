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

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.Passage;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.access.Access;

public final class EquinoxPassage implements Passage {

	private final FrameworkAware<?> delegate;

	public EquinoxPassage() {
		this(new SuppliedFrameworkAware());
	}

	public EquinoxPassage(FrameworkAware<?> delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean canUse(String feature) {
		return delegate.withFramework(framework -> new Access(framework).canUse(feature)).orElse(Boolean.FALSE);
	}

	@Override
	public ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature) {
		return delegate.withFrameworkService(framework -> new Access(framework).acquire(feature));
	}

	@Override
	public ServiceInvocationResult<Boolean> releaseLicense(GrantLockAttempt lock) {
		return delegate.withFrameworkService(framework -> new Access(framework).release(lock));
	}

	@Override
	public ServiceInvocationResult<LicensedProduct> product() {
		return delegate.withFrameworkService(framework -> new BaseServiceInvocationResult<>(framework.product()));
	}

}
