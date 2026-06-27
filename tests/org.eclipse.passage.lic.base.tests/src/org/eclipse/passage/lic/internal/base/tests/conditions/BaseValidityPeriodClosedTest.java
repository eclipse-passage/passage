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
package org.eclipse.passage.lic.internal.base.tests.conditions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.junit.jupiter.api.Test;

public final class BaseValidityPeriodClosedTest {

	@Test
	public void fromIsMandatory() {
		assertThrows(NullPointerException.class, () -> new BaseValidityPeriodClosed(null, ZonedDateTime.now()));
	}

	@Test
	public void toIsMandatory() {
		assertThrows(NullPointerException.class, () -> new BaseValidityPeriodClosed(ZonedDateTime.now(), null));
	}

}
