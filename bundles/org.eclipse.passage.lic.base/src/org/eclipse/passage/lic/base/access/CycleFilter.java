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
package org.eclipse.passage.lic.base.access;

import java.util.Collection;
import java.util.function.Function;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.conditions.ConditionsFeatureFilter;
import org.eclipse.passage.lic.base.requirements.RequirementsFeatureFilter;

final class CycleFilter {

	private final Function<//
			ServiceInvocationResult<Collection<Requirement>>, //
			ServiceInvocationResult<Collection<Requirement>>> requiremental;
	private final Function<//
			ServiceInvocationResult<Collection<ConditionPack>>, //
			ServiceInvocationResult<Collection<ConditionPack>>> conditional;

	CycleFilter() {
		this(Function.identity(), Function.identity());
	}

	CycleFilter(String feature) {
		this(new RequirementsFeatureFilter(feature).get(), new ConditionsFeatureFilter(feature).get());
	}

	private CycleFilter(
			Function<ServiceInvocationResult<Collection<Requirement>>, ServiceInvocationResult<Collection<Requirement>>> requiremental,
			Function<ServiceInvocationResult<Collection<ConditionPack>>, ServiceInvocationResult<Collection<ConditionPack>>> conditional) {
		this.requiremental = requiremental;
		this.conditional = conditional;
	}

	Function<ServiceInvocationResult<Collection<Requirement>>, ServiceInvocationResult<Collection<Requirement>>> requiremental() {
		return requiremental;
	}

	Function<ServiceInvocationResult<Collection<ConditionPack>>, ServiceInvocationResult<Collection<ConditionPack>>> conditional() {
		return conditional;
	}

}
