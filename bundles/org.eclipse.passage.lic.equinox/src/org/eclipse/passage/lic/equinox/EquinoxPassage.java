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
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.equinox;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.Passage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BasePassage;
import org.eclipse.passage.lic.base.FrameworkAware;

/**
 * @since 2.1
 */
public final class EquinoxPassage implements Passage {

	private final BasePassage delegate;

	public EquinoxPassage() {
		this(new SuppliedFrameworkAware());
	}

	public EquinoxPassage(FrameworkAware delegate) {
		this.delegate = new BasePassage(delegate);
	}

	@Override
	public boolean canUse(FeatureIdentifier feature) {
		return delegate.canUse(feature);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> assess() {
		return delegate.assess();
	}

	@Override
	public ServiceInvocationResult<GrantLockAttempt> acquireLicense(FeatureIdentifier feature) {
		return delegate.acquireLicense(feature);
	}

	@Override
	public ServiceInvocationResult<Boolean> releaseLicense(GrantLockAttempt lock) {
		return delegate.releaseLicense(lock);
	}

	@Override
	public ServiceInvocationResult<LicensedProduct> product() {
		return delegate.product();
	}

}
