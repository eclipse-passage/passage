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
package org.eclipse.passage.lic.base.requirements;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;

/**
 * Base <i>data-driven</i> implementation of a {@linkplain Requirement}.
 * 
 * @since 2.1
 */
public final class BaseRequirement implements Requirement {

	private final Feature feature;
	private final RestrictionLevel restriction;
	private final List<ResolvedAgreement> agreements;
	private final Object source;

	public BaseRequirement(Feature feature, RestrictionLevel restriction, List<ResolvedAgreement> agreements,
			Object source) {
		Objects.requireNonNull(feature, "Feature cannot be null on requirement definition"); //$NON-NLS-1$
		Objects.requireNonNull(restriction,
				"Restriction cannot be null on requirement definition. Use DefaultrestrictionLevel should the need arise"); //$NON-NLS-1$
		Objects.requireNonNull(agreements, "Agreements collection cannot be null, but can be empty"); //$NON-NLS-1$
		Objects.requireNonNull(source, "Source is mandatory for requirement definition"); //$NON-NLS-1$
		this.feature = feature;
		this.restriction = restriction;
		this.agreements = agreements;
		this.source = source;
	}

	public BaseRequirement(Feature feature, RestrictionLevel restriction, Object source) {
		this(feature, restriction, Collections.emptyList(), source);
	}

	@Override
	public Feature feature() {
		return feature;
	}

	@Override
	public RestrictionLevel restrictionLevel() {
		return restriction;
	}

	@Override
	public Object source() {
		return source;
	}

	@Override
	public boolean equals(Object another) {
		if (!getClass().isInstance(another)) {
			return false;
		}
		Requirement requirement = (Requirement) another;
		return feature.equals(requirement.feature()) //
				&& restriction.equals(requirement.restrictionLevel()) //
				&& source.equals(requirement.source());
	}

	@Override
	public int hashCode() {
		return Objects.hash(feature, restriction, source);
	}

	@Override
	public String toString() {
		return "BaseRequirement [feature=" + feature + // //$NON-NLS-1$
				", restriction=" + restriction + // //$NON-NLS-1$
				", source=" + source + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public List<ResolvedAgreement> agreements() {
		return agreements;
	}

}
