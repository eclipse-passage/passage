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
package org.eclipse.passage.loc.internal.equinox;

import org.eclipse.passage.lic.equinox.GearAware;
import org.eclipse.passage.loc.internal.api.OperatorGear;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;

public final class OperatorGearAware extends GearAware<OperatorGear, OperatorGearSupplier> {

	@Override
	protected Class<OperatorGearSupplier> supplier() {
		return OperatorGearSupplier.class;
	}

}
