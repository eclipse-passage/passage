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

import java.util.Set;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;

/**
 * Top-level access cycle management is to be implemented here. Just started
 * with what we have for now: requirements resolution.
 */
public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		Set<Requirement> requirements = new Requirements(
				framework.accessCycleConfiguration().requirementsRegistry().get(), feature).get();
		if (requirements.isEmpty()) {
			return true;
		}
		// FIXME: EP: implement further
		return false;
	}

}
