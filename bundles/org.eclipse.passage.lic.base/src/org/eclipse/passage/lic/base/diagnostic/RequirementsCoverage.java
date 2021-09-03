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
package org.eclipse.passage.lic.base.diagnostic;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;

/**
 * @since 2.1
 */
public final class RequirementsCoverage implements Supplier<List<RequirementStatus>> {

	private final ExaminationCertificate certificate;

	public RequirementsCoverage(ExaminationCertificate certificate) {
		this.certificate = certificate;
	}

	@Override
	public List<RequirementStatus> get() {
		return new SumOfLists<RequirementStatus>().apply(//
				restrictions(), //
				satisfactions()//
		);
	}

	private List<RequirementStatus> restrictions() {
		return certificate.restrictions().stream()//
				.map(RequirementStatus::new) //
				.collect(Collectors.toList());
	}

	private List<RequirementStatus> satisfactions() {
		return certificate.satisfied().stream() //
				.map(RequirementStatus::new)//
				.collect(Collectors.toList());
	}

}
