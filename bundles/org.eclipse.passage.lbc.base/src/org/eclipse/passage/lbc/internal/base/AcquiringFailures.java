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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.base.troubles.LicenseOutOfcapacity;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.restrictions.BaseRestriction;

/**
 * @since 1.0
 */
@SuppressWarnings("restriction")
public final class AcquiringFailures implements Function<Map<Requirement, Permission>, Collection<Restriction>> {

	private final Function<Condition, Boolean> takeResult;

	public AcquiringFailures(Function<Condition, Boolean> takeResult) {
		this.takeResult = takeResult;
	}

	/**
	 * 
	 * @param satisfied a map of satisfied requirements and permissions
	 * @return a HashSet of Restrictions for all license that were failed to be
	 *         taken or an empty HashSet if all licenses were taken successfully
	 */
	@Override
	public Collection<Restriction> apply(Map<Requirement, Permission> satisfied) {
		Collection<Restriction> restrictions = new HashSet<>();
		satisfied.entrySet().stream() //
				.forEach(entry -> {
					if (!takeResult.apply(entry.getValue().condition())) {
						restrictions.add(new BaseRestriction(entry.getValue().product(), entry.getKey(),
								new LicenseOutOfcapacity()));
					}
				});
		return restrictions;
	}

}
