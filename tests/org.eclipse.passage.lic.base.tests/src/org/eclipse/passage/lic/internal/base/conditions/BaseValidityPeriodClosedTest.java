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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.Date;

import org.junit.Test;

@SuppressWarnings("restriction")
public final class BaseValidityPeriodClosedTest {

	@Test(expected = NullPointerException.class)
	public void fromIsMandatory() {
		new BaseValidityPeriodClosed(null, new Date());
	}

	@Test(expected = NullPointerException.class)
	public void toIsMandatory() {
		new BaseValidityPeriodClosed(new Date(), null);
	}

}
