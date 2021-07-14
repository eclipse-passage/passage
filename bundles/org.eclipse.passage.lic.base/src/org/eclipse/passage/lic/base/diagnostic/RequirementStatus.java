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
package org.eclipse.passage.lic.base.diagnostic;

import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.i18n.ExaminationExplanedMessages;

/**
 * 
 * @since 2.1
 */
public final class RequirementStatus {

	private final String feature;
	private final String status;
	private final RestrictionLevel level;

	public RequirementStatus(Restriction restriction) {
		this.feature = explain(restriction.unsatisfiedRequirement().feature());
		this.status = explain(restriction.reason());
		this.level = restriction.unsatisfiedRequirement().restrictionLevel();
	}

	public RequirementStatus(Requirement requirement) {
		this.feature = explain(requirement.feature());
		this.status = ExaminationExplanedMessages.getString("RequirementStatus.covered"); //$NON-NLS-1$
		this.level = requirement.restrictionLevel();
	}

	private String explain(Feature functionality) {
		return String.format("%s v.%s", functionality.name(), functionality.version()); //$NON-NLS-1$
	}

	private String explain(TroubleCode trouble) {
		return String.format("%s (%d)", trouble.explanation(), trouble.code()); //$NON-NLS-1$
	}

	public String feature() {
		return feature;
	}

	public String status() {
		return status;
	}

	public RestrictionLevel level() {
		return level;
	}
}
