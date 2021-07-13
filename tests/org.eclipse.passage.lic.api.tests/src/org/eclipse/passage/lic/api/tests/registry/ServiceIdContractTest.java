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

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.lic.api.registry.ServiceId;
import org.junit.Test;

public abstract class ServiceIdContractTest {

	/**
	 * It is expected that two {@linkplain ServiceId} instances built of the same
	 * data are `equal` id data-class sense.
	 */
	@Test
	public void isDataDriven() {
		assertEquals(ofSameData(), ofSameData());
		assertEquals(ofSameData().hashCode(), ofSameData().hashCode());
		assertEquals(ofSameData().toString(), ofSameData().toString());
	}

	/**
	 * @return new instance each time build of the same data
	 */
	protected abstract ServiceId ofSameData();

}
