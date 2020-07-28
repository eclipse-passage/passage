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
import java.util.Optional;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

/**
 * Top-level access cycle
 */
public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		return new WithExaminationCertificate<Boolean>(framework, feature).apply(this::noSevereRestrictions);
	}

	public void check(String feature) {
		new WithExaminationCertificate<Boolean>(framework, feature).apply(this::track);
	}

	private boolean noSevereRestrictions(Optional<ExaminationCertificate> certificate) {
		if (!certificate.isPresent()) {
			return false;
		}
		List<RestrictionLevel> severe = Arrays.asList(//
				new RestrictionLevel.Error(), //
				new RestrictionLevel.Fatal());
		return !certificate.get().restrictions().stream()//
				.map(r -> r.unsatisfiedRequirement().restrictionLevel())//
				.filter(severe::contains) //
				.findFirst()//
				.isPresent();
	}

	private boolean track(Optional<ExaminationCertificate> certificate) {
		// FIXME: run observation for active permissions (no matter how patient
		// FIXME: execute restrictions
		return true; // is dropped, just to cover 'void' value with an object
	}

}
