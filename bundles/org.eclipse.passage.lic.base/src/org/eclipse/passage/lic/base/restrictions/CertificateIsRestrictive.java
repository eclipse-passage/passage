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
public final class CertificateIsRestrictive implements Predicate<Optional<ExaminationCertificate>> {

	@Override
	public boolean test(Optional<ExaminationCertificate> certificate) {
		if (!certificate.isPresent()) {
			return true;
		}
		if (!new NoSevereRestrictions().test(certificate.get())) {
			return true;
		}
		if (!new AllAgreementsAccepted().test(certificate.get())) {
			return true;
		}
		return false;
	}

}
