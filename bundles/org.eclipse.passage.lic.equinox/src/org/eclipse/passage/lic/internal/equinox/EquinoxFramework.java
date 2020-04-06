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
package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@SuppressWarnings("restriction")
@Component
public final class EquinoxFramework implements Framework {

	private ResolvedRequirementsRegistry requirements;

	@Override
	public ResolvedRequirementsRegistry requirementsRegistry() {
		return requirements;
	}

	@Reference
	public void bind(ResolvedRequirementsRegistry registry) {
		this.requirements = registry;
	}

	public void unbind(ResolvedRequirementsRegistry registry) {
		if (this.requirements.equals(registry)) {
			this.requirements = null;
		}
	}

}
