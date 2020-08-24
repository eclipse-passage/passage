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
package org.eclipse.passage.lic.internal.json;

import java.time.ZonedDateTime;
import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;

@SuppressWarnings("restriction")
public final class AcquiredExaminationCertificate implements ExaminationCertificate {

	private final String stamp;
	private final Collection<Restriction> restrictions;
	private final Collection<Permission> permissions;

	public AcquiredExaminationCertificate(Collection<Permission> permissions, Collection<Restriction> restrictions,
			String stamp) {
		this.restrictions = restrictions;
		this.permissions = permissions;
		this.stamp = stamp;
	}

	@Override
	public Collection<Restriction> restrictions() {
		return restrictions;
	}

	@Override
	public ZonedDateTime stamp() {
		return ZonedDateTime.parse(stamp);
	}

	@Override
	public Collection<Requirement> satisfied() {
		// FIXME #566331
		return null;
	}

	@Override
	public Permission satisfaction(Requirement satisfied) {
		// FIXME #566331
		return null;
	}

}
