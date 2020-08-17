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
package org.eclipse.passage.lic.internal.base.restrictions;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;

@SuppressWarnings("restriction")
public final class BaseExaminationCertificate implements ExaminationCertificate {

	private final Collection<Permission> participants;
	private final Collection<Restriction> restrictions;
	private final ZonedDateTime stamp;

	public BaseExaminationCertificate(Collection<Permission> participants, Collection<Restriction> restrictions) {
		Objects.requireNonNull(participants, "BaseExaminationCertificate::participants"); //$NON-NLS-1$
		Objects.requireNonNull(restrictions, "BaseExaminationCertificate::restrictions"); //$NON-NLS-1$
		this.participants = participants;
		this.restrictions = restrictions;
		this.stamp = ZonedDateTime.now();
	}

	@Override
	public boolean examinationPassed() {
		return restrictions.isEmpty();
	}

	@Override
	public Collection<Restriction> restrictions() {
		return restrictions;
	}

	@Override
	public Collection<Permission> participants() {
		return participants;
	}

	@Override
	public ZonedDateTime stamp() {
		return stamp;
	}

}
