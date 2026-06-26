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
package org.eclipse.passage.lic.api.tests.resrictions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeLicensedProduct;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.eclipse.passage.lic.api.tests.fakes.requirements.FakeRequirement;
import org.junit.jupiter.api.Test;

public abstract class PermissionsExaminationServiceContractTest {

	@Test
	public void prohibitsNullRequirements() {
		assertThrows(NullPointerException.class,
				() -> examiner(FakeLicensedProduct::new).examine(null, Collections.singleton(new FakePermission())));
	}

	@Test
	public void prohibitsNullPermissions() {
		assertThrows(NullPointerException.class,
				() -> examiner(FakeLicensedProduct::new).examine(Collections.singleton(new FakeRequirement()), null));
	}

	@Test
	public void prohibitsNullProduct() {
		assertThrows(NullPointerException.class, () -> examiner(null)
				.examine(Collections.singleton(new FakeRequirement()), Collections.singleton(new FakePermission())));
	}

	protected abstract PermissionsExaminationService examiner(Supplier<LicensedProduct> product);
}
