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
package org.eclipse.passage.lic.base;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.Passage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.access.Access;

/**
 * @since 2.1
 */
public final class BasePassage implements Passage {

	private final FrameworkAware delegate;

	public BasePassage(FrameworkAware delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean canUse(String feature) {
		return delegate.withFramework(framework -> new Access(framework).canUse(feature)).orElse(Boolean.FALSE);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assess() {
		return delegate.withFrameworkService(framework -> new Access(framework).assess());
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
