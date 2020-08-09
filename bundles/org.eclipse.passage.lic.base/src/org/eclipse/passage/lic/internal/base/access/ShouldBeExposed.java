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
package org.eclipse.passage.lic.internal.base.access;

import java.util.function.Predicate;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * Tells if the examination result should cause use notification
 */
public final class ShouldBeExposed implements Predicate<ServiceInvocationResult<ExaminationCertificate>> {

	@Override
	public boolean test(ServiceInvocationResult<ExaminationCertificate> result) {
		if (!new NoSevereErrors().test(result.diagnostic())) {
			return true;
		}
		if (!result.data().isPresent()) {
			return true;
		}
		if (!new NoSevereRestrictions().test(result.data().get())) {
			return true;
		}
		return false;
	}

}
