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
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeCondition;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.conditions.UnknownConditionOrigin;
import org.eclipse.passage.lic.base.conditions.evaluation.BasePermission;
import org.junit.jupiter.api.Test;

@SuppressWarnings("restriction")
public final class BasePermissionTest {

	@Test
	public void productIsMandatory() {
		assertThrows(NullPointerException.class, () -> new BasePermission(null, condition(), ZonedDateTime.now(),
				ZonedDateTime.now().plusDays(1), new UnknownConditionOrigin()));
	}

	@Test
	public void conditionIsMandatory() {
		assertThrows(NullPointerException.class, () -> new BasePermission(product(), null, ZonedDateTime.now(),
				ZonedDateTime.now().plusDays(1), new UnknownConditionOrigin()));
	}

	@Test
	public void leaseDateIsMandatory() {
		assertThrows(NullPointerException.class, () -> new BasePermission(product(), condition(), null,
				ZonedDateTime.now(), new UnknownConditionOrigin()));
	}

	@Test
	public void expirationDateIsMandatory() {
		assertThrows(NullPointerException.class, () -> new BasePermission(product(), condition(), ZonedDateTime.now(),
				null, new UnknownConditionOrigin()));
	}

	@Test
	public void expiredAfterLeasing() {
		assertThrows(IllegalArgumentException.class, () -> new BasePermission(product(), condition(),
				ZonedDateTime.now().plusDays(1), ZonedDateTime.now(), new UnknownConditionOrigin()));
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("t", "v"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private Condition condition() {
		return new FakeCondition();
	}

}
