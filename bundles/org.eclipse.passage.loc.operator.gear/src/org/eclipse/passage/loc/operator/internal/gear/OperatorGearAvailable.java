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
package org.eclipse.passage.loc.operator.internal.gear;

import org.eclipse.passage.loc.internal.api.OperatorGear;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.osgi.service.component.annotations.Component;

@Component
public final class OperatorGearAvailable implements OperatorGearSupplier {

	@Override
	public OperatorGear get() {
		return DefaultGear.gear;
	}

}
