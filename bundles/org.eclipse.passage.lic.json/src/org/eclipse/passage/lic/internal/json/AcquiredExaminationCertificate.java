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
package org.eclipse.passage.lic.internal.json;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;

public final class AcquiredExaminationCertificate implements ExaminationCertificate {

	private final ZonedDateTime stamp;
	private final Map<Requirement, Permission> permissions;
	private final Collection<Restriction> restrictions;

	public AcquiredExaminationCertificate(Map<Requirement, Permission> permissions,
			Collection<Restriction> restrictions, ZonedDateTime stamp) {
		Objects.requireNonNull(restrictions, "AcquiredExaminationCertificate::restrictions"); //$NON-NLS-1$
		Objects.requireNonNull(permissions, "AcquiredExaminationCertificate::permissions"); //$NON-NLS-1$
		Objects.requireNonNull(stamp, "AcquiredExaminationCertificate::stamp"); //$NON-NLS-1$
		this.permissions = permissions;
		this.restrictions = restrictions;
		this.stamp = stamp;
	}

	@Override
	public Collection<Restriction> restrictions() {
		return restrictions;
	}

	@Override
	public ZonedDateTime stamp() {
		return stamp;
	}

	@Override
	public Collection<Requirement> satisfied() {
		return new HashSet<>(permissions.keySet());
	}

	@Override
	public Permission satisfaction(Requirement satisfied) {
		if (!permissions.containsKey(satisfied)) {
			throw new IllegalArgumentException("The requirement has not been satisifed"); //$NON-NLS-1$ dev error
		}
		return permissions.get(satisfied);
	}

}
