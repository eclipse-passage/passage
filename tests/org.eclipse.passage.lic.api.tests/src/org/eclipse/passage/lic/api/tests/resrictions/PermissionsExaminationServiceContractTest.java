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
package org.eclipse.passage.lic.api.tests.resrictions;

import java.util.Collections;

import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeLicensedProduct;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.eclipse.passage.lic.api.tests.fakes.requirements.FakeRequirement;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class PermissionsExaminationServiceContractTest {

	@Test(expected = NullPointerException.class)
	public void prohibitsNullRequirements() {
		examiner().examine(null, Collections.singleton(new FakePermission()), new FakeLicensedProduct());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitsNullPermissions() {
		examiner().examine(Collections.singleton(new FakeRequirement()), null, new FakeLicensedProduct());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitsNullProduct() {
		examiner().examine(Collections.singleton(new FakeRequirement()), Collections.singleton(new FakePermission()),
				null);
	}

	protected abstract PermissionsExaminationService examiner();
}
