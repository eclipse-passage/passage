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
package org.eclipse.passage.lic.base.restrictions;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;

/**
 * 
 * @since 2.1
 */
public final class BaseExaminationCertificate implements ExaminationCertificate {

	private final Map<Requirement, Permission> satisfied;
	private final Collection<Restriction> restrictions;
	private final Collection<AgreementToAccept> agreements;
	private final ZonedDateTime stamp;

	public BaseExaminationCertificate(Map<Requirement, Permission> satisfied, Collection<Restriction> restrictions,
			Collection<AgreementToAccept> agreements) {
		Objects.requireNonNull(satisfied, "BaseExaminationCertificate::satisfied"); //$NON-NLS-1$
		Objects.requireNonNull(restrictions, "BaseExaminationCertificate::restrictions"); //$NON-NLS-1$
		Objects.requireNonNull(agreements, "BaseExaminationCertificate::agreements"); //$NON-NLS-1$
		this.satisfied = satisfied;
		this.restrictions = restrictions;
		this.agreements = agreements;
		this.stamp = ZonedDateTime.now();
	}

	public BaseExaminationCertificate(Map<Requirement, Permission> satisfied, Collection<Restriction> restrictions) {
		this(satisfied, restrictions, Collections.emptyList());
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
		return new HashSet<>(satisfied.keySet());
	}

	@Override
	public Permission satisfaction(Requirement requirement) {
		if (!satisfied.containsKey(requirement)) {
			throw new IllegalArgumentException("The requirement has not been satisifed"); //$NON-NLS-1$ dev error
		}
		return satisfied.get(requirement);
	}

	@Override
	public Collection<AgreementToAccept> agreements() {
		return agreements;
	}

}
