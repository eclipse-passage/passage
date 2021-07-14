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
package org.eclipse.passage.lic.base.access;

import java.util.function.Predicate;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.restrictions.NoSevereRestrictions;

/**
 * @since 1.1
 */
public final class CanProceed implements Predicate<ServiceInvocationResult<ExaminationCertificate>> {

	@Override
	public boolean test(ServiceInvocationResult<ExaminationCertificate> result) {
		return new NoSevereErrors().test(result.diagnostic()) && //
				result.data().map(certificate -> new NoSevereRestrictions().test(certificate))//
						.orElse(false);
	}

}
