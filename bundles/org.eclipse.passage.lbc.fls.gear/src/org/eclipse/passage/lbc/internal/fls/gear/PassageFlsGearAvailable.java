/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.fls.gear;

import org.eclipse.passage.lbc.internal.base.api.FlsGear;
import org.eclipse.passage.lbc.internal.base.api.FlsGearSupplier;
import org.osgi.service.component.annotations.Component;

@SuppressWarnings("restriction")
@Component
public final class PassageFlsGearAvailable implements FlsGearSupplier {

	@Override
	public FlsGear get() {
		return PassageFlsGear.gear;
	}

}
