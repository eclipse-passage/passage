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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;

final class LicensingOperatorRequirement implements Supplier<Optional<Requirement>> {

	private final ExaminationCertificate certificate;
	private final LicensedProduct operator;

	LicensingOperatorRequirement(ExaminationCertificate certificate, LicensedProduct operator) {
		this.certificate = certificate;
		this.operator = operator;
	}

	@Override
	public Optional<Requirement> get() {
		return satisfiedOperatorRequirement().or(this::unsatisfiedOperatorRequirement);
	}

	private Optional<Requirement> satisfiedOperatorRequirement() {
		return certificate.satisfied().stream()//
				.filter(this::describesOperator)//
				.findAny();
	}

	private Optional<Requirement> unsatisfiedOperatorRequirement() {
		return certificate.restrictions().stream()//
				.map(Restriction::unsatisfiedRequirement)//
				.filter(this::describesOperator)//
				.findAny();
	}

	private boolean describesOperator(Requirement requirement) {
		return requirement.feature().identifier().equals(operator.identifier());
	}

}
