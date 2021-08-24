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
package org.eclipse.passage.lic.base.restrictions;

import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;

/**
 * 
 * @since 2.1
 */
public final class CertificateWorthAttention implements Predicate<Optional<ExaminationCertificate>> {

	@Override
	public boolean test(Optional<ExaminationCertificate> certificate) {
		if (new CertificateIsRestrictive().test(certificate)) {
			return true;
		}
		if (certificate.get().restrictions().stream().anyMatch(new RestrictionMustPauseExecution())) {
			return true;
		}
		return false;
	}

}
