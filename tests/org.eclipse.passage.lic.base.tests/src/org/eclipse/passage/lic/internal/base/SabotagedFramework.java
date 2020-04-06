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
package org.eclipse.passage.lic.internal.base;

import java.util.Collections;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.base.registry.BaseRegistry;

@SuppressWarnings("restriction")
final class SabotagedFramework implements Framework {

	@Override
	public ResolvedRequirementsRegistry requirementsRegistry() {
		return new NoResolvers();
	}

	private static class NoResolvers implements ResolvedRequirementsRegistry {

		@Override
		public Registry<StringServiceId, ResolvedRequirements> get() {
			return new BaseRegistry<StringServiceId, ResolvedRequirements>(Collections::emptyList);
		}

	}

}
