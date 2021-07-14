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

import java.util.function.Predicate;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevelComparator;

public final class RequirementDemandsExecutionStop implements Predicate<Requirement> {

	@Override
	public boolean test(Requirement requirement) {
		return new RestrictionLevelComparator()//
				.compare(//
						new RestrictionLevel.Error(), //
						requirement.restrictionLevel()//
				) <= 0;
	}

}
