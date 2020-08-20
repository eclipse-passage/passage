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
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.access.Access;

@SuppressWarnings("restriction")
public final class EquinoxPassage extends FrameworkAware implements Passage {

	@Override
	public boolean canUse(String feature) {
		return withFramework(framework -> //
		new BaseServiceInvocationResult<>(new Access(framework).canUse(feature))).data()//
				.orElse(Boolean.FALSE);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> acquireLicense(String feature) {
		return withFramework(framework -> new Access(framework).acquire(feature));
	}

	@Override
	public ServiceInvocationResult<Boolean> releaseLicense(ExaminationCertificate certificate) {
		return withFramework(framework -> new Access(framework).release(certificate));
	}

	@Override
	public ServiceInvocationResult<LicensedProduct> product() {
		return withFramework(framework -> new BaseServiceInvocationResult<>(framework.product()));
	}

}
