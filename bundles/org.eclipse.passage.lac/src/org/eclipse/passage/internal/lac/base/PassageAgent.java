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
package org.eclipse.passage.internal.lac.base;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.Passage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.net.NetFrameworkAware;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

@SuppressWarnings("restriction")
final class PassageAgent implements Passage {

	private final EquinoxPassage delegate;

	public PassageAgent(ProductUserRequest<NetRequest> request) {
		this.delegate = new EquinoxPassage(new NetFrameworkAware<>(request));
	}

	@Override
	public ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature) {
		return delegate.acquireLicense(feature);
	}

	@Override
	public ServiceInvocationResult<Boolean> releaseLicense(GrantLockAttempt lock) {
		return delegate.releaseLicense(lock);
	}

	@Override
	public boolean canUse(String feature) {
		return delegate.canUse(feature);
	}

	@Override
	public ServiceInvocationResult<LicensedProduct> product() {
		return delegate.product();
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assess() {
		return delegate.assess();
	}

}
