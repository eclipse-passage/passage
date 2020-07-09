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
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeCondition;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermission;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BasePermissionTest {

	@Test(expected = NullPointerException.class)
	public void productIsMandatory() {
		new BasePermission(null, condition(), ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
	}

	@Test(expected = NullPointerException.class)
	public void conditionIsMandatory() {
		new BasePermission(product(), null, ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
	}

	@Test(expected = NullPointerException.class)
	public void leaseDateIsMandatory() {
		new BasePermission(product(), condition(), null, ZonedDateTime.now());
	}

	@Test(expected = NullPointerException.class)
	public void expirationDateIsMandatory() {
		new BasePermission(product(), condition(), ZonedDateTime.now(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void expiredAfterLeasing() {
		new BasePermission(product(), condition(), ZonedDateTime.now().plusDays(1), ZonedDateTime.now());
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("t", "v"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private Condition condition() {
		return new FakeCondition();
	}

}
