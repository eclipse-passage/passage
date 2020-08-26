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
package org.eclipse.passage.lbc.internal.base;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * @since 1.0
 */
@SuppressWarnings("restriction")
public class SatisfiedRequirements implements Function<ExaminationCertificate, Map<Requirement, Permission>> {

	@Override
	public Map<Requirement, Permission> apply(ExaminationCertificate certificate) {
		return certificate.satisfied().stream() //
				.collect(Collectors.toMap(Function.identity(), certificate::satisfaction));
	}

}
