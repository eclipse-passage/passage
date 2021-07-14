/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lbc.internal.base.api;

import org.eclipse.passage.lic.equinox.GearAware;

public final class FlsGearAwre extends GearAware<FlsGear, FlsGearSupplier> {

	@Override
	protected Class<FlsGearSupplier> supplier() {
		return FlsGearSupplier.class;
	}

}
