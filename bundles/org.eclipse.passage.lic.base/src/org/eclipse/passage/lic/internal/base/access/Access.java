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

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;

/**
 * Top-level access cycle
 */
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

		// FIXME: EP: implement further
		return false;
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

	private void permissions(String feature) {
		framework.accessCycleConfiguration().permissionEmitters().get();
	}

}
