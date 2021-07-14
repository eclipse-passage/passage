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
package org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;

public final class FakePermission implements Permission {

	@Override
	public LicensedProduct product() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Condition condition() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ConditionOrigin conditionOrigin() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ZonedDateTime leaseDate() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ZonedDateTime expireDate() {
		throw new UnsupportedOperationException();
	}

}
