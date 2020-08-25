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

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.SumOfMaps;
import org.eclipse.passage.lic.internal.base.restrictions.BaseExaminationCertificate;

@SuppressWarnings("restriction")
public final class SumOfCertificates implements BinaryOperator<ExaminationCertificate> {

	@Override
	public ExaminationCertificate apply(ExaminationCertificate first, ExaminationCertificate second) {

		return new BaseExaminationCertificate(//
				new SumOfMaps<Requirement, Permission>().apply(satisfied(first), satisfied(second)), //
				new SumOfCollections<Restriction>().apply(first.restrictions(), second.restrictions())//
		);

	}

	private Map<Requirement, Permission> satisfied(ExaminationCertificate certificate) {
		return certificate.satisfied().stream() //
				.collect(Collectors.toMap(Function.identity(), certificate::satisfaction));
	}

}
