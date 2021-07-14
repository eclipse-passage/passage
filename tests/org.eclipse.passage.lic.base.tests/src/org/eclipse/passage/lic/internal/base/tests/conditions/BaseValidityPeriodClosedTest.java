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
package org.eclipse.passage.lic.internal.base.tests.conditions;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.junit.Test;

public final class BaseValidityPeriodClosedTest {

	@Test(expected = NullPointerException.class)
	public void fromIsMandatory() {
		new BaseValidityPeriodClosed(null, ZonedDateTime.now());
	}

	@Test(expected = NullPointerException.class)
	public void toIsMandatory() {
		new BaseValidityPeriodClosed(ZonedDateTime.now(), null);
	}

}
