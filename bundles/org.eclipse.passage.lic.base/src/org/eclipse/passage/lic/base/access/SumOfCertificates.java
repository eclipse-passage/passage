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

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.SumOfMaps;
import org.eclipse.passage.lic.base.restrictions.BaseExaminationCertificate;

/**
 * @since 2.1
 */
public final class SumOfCertificates implements BinaryOperator<ExaminationCertificate> {

	@Override
	public ExaminationCertificate apply(ExaminationCertificate first, ExaminationCertificate second) {

		return new BaseExaminationCertificate(//
				new SumOfMaps<Requirement, Permission>().apply(satisfied(first), satisfied(second)), //
				new SumOfCollections<Restriction>().apply(first.restrictions(), second.restrictions()), //
				new SumOfCollections<AgreementToAccept>().apply(first.agreements(), second.agreements())//
		);

	}

	private Map<Requirement, Permission> satisfied(ExaminationCertificate certificate) {
		return certificate.satisfied().stream() //
				.collect(Collectors.toMap(Function.identity(), certificate::satisfaction));
	}

}
