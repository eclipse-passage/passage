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
package org.eclipse.passage.lic.base.restrictions;

import java.util.function.Predicate;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;

/**
 * 
 * @since 2.1
 */
public final class AllAgreementsAccepted implements Predicate<ExaminationCertificate> {

	@Override
	public boolean test(ExaminationCertificate certificate) {
		return certificate.agreements().stream()//
				.allMatch(this::accepted); // true for 'no agreements' case
	}

	private boolean accepted(AgreementToAccept agreement) {
		return agreement.acceptance().accepted();
	}

}
