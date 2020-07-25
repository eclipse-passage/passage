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
import java.util.Collection;
import java.util.List;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

/**
 * Top-level access cycle
 */
@SuppressWarnings("restriction")
public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		Collection<Requirement> requirements = requirements(feature);
		if (requirements.isEmpty()) {
			return true;
		}
		ServiceInvocationResult<Collection<ConditionPack>> mining = conditions(feature);
		if (!mining.successful()) {
			return false;
		}
		if (mining.data().isEmpty()) {
			return false;
		}
		Collection<Permission> emission = permissions(mining.data());
		ServiceInvocationResult<Collection<Restriction>> examination = restrictions(emission, requirements);
		if (!examination.successful()) {
			return false;
		}
		return noSevereRestrictions(examination.data());
	}

	private Collection<Requirement> requirements(String feature) {
		return new Requirements(//
				framework.accessCycleConfiguration().requirementResolvers().get(), //
				feature).get();
	}

	private ServiceInvocationResult<Collection<ConditionPack>> conditions(String feature) {
		return new Conditions(//
				framework.accessCycleConfiguration().conditionMiners().get(), //
				framework.product(), //
				feature).get();
	}

	private Collection<Permission> permissions(Collection<ConditionPack> conditions) {
		return new Permissions(//
				framework.accessCycleConfiguration().permissionEmitters().get(), //
				conditions, //
				framework.product()).get();
	}

	private ServiceInvocationResult<Collection<Restriction>> restrictions(Collection<Permission> permissions,
			Collection<Requirement> requirements) {
		return new Restrictions(//
				framework.accessCycleConfiguration().examinators().get(), //
				requirements, //
				permissions, //
				framework.product()).get();

	}

	private boolean noSevereRestrictions(Collection<Restriction> restrictions) {
		List<RestrictionLevel> severe = Arrays.asList(//
				new RestrictionLevel.Error(), //
				new RestrictionLevel.Fatal());
		return !restrictions.stream()//
				.map(r -> r.unsatisfiedRequirement().restrictionLevel())//
				.filter(severe::contains) //
				.findFirst()//
				.isPresent();

	}

}
