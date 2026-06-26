/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.api.tests.registry;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.junit.jupiter.api.Test;

public final class StringServiceIdTest extends ServiceIdContractTest {

	@Test
	public void idIsMandatory() {
		assertThrows(NullPointerException.class, () -> new StringServiceId(null));
	}

	@Override
	protected ServiceId ofSameData() {
		return new StringServiceId("same-id-value"); //$NON-NLS-1$
	}

}
