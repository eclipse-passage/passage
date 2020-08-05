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

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

final class NoSevereRestrictions implements Predicate<ExaminationCertificate> {
	private final List<RestrictionLevel> severe = Arrays.asList(//
			new RestrictionLevel.Error(), //
			new RestrictionLevel.Fatal());

	@Override
	public boolean test(ExaminationCertificate certificate) {
		if (!certificate.examinationPassed()) {
			return false;
		}
		return !certificate.restrictions().stream()//
				.map(r -> r.unsatisfiedRequirement().restrictionLevel())//
				.filter(severe::contains) //
				.findFirst()//
				.isPresent();
	}

}
