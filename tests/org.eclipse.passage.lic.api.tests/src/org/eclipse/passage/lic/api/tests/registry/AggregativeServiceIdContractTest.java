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
package org.eclipse.passage.lic.api.tests.registry;

import org.eclipse.passage.lic.api.registry.AggregativeServiceId;
import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.api.registry.StringServiceId;

public class AggregativeServiceIdContractTest extends ServiceIdContractTest {

	@Override
	protected ServiceId ofSameData() {
		return new AggregativeServiceId<StringServiceId, StringServiceId>(//
				new StringServiceId("first"), //$NON-NLS-1$
				new StringServiceId("second")); //$NON-NLS-1$
	}

}
