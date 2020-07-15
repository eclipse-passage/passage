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
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeLicensedProduct;
import org.eclipse.passage.lic.api.tests.resrictions.PermissionsExaminationServiceContractTest;
import org.eclipse.passage.lic.internal.api.diagnostic.code.LicenseInvalid;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.restrictions.BasePermissionsExaminationService;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BasePermissionsExaminationServiceTest extends PermissionsExaminationServiceContractTest {

	@Test
	public void detectsSingleRequirementSatisfaction() {
		TestState state = new TestState();
		Collection<Restriction> restrictions = examiner().examine(//
				Collections.singleton(state.requirementFirst()), //
				Collections.singleton(state.permissionFirst()), //
				state.product());
		assertNotNull(restrictions);
		assertTrue(restrictions.isEmpty());
	}

	@Test
	public void detectsSeveralRequirementsSatisfaction() {
		TestState state = new TestState();
		Collection<Restriction> restrictions = examiner().examine(//
				Arrays.asList(state.requirementFirst(), state.requirementSecond()), //
				Arrays.asList(state.permissionSecond(), state.permissionFirst()), //
				state.product());
		assertNotNull(restrictions);
		assertTrue(restrictions.isEmpty());
	}

	@Test
	public void detectsRequirementUnsatisfaction() {
		TestState state = new TestState();
		Collection<Restriction> restrictions = examiner().examine(//
				Arrays.asList(state.requirementFirst(), state.requirementSecond()), //
				Arrays.asList(state.permissionSecond()), //
				state.product());
		assertNotNull(restrictions);
		assertEquals(1, restrictions.size());
		Restriction restriction = restrictions.iterator().next();
		assertEquals(state.requirementFirst(), restriction.unsatisfiedRequirement());
		assertEquals(state.product(), restriction.product());
		assertEquals(new LicenseInvalid(), restriction.reason());
	}

	@Test
	public void detectsRequirementUnsatisfactionOnObsoleteCondition() {
		TestState state = new TestState();
		Collection<Restriction> restrictions = examiner().examine(//
				Arrays.asList(state.requirementSecond()), //
				Arrays.asList(state.permissionSecondObsolete()), //
				state.product());
		assertNotNull(restrictions);
		assertEquals(1, restrictions.size());
		Restriction restriction = restrictions.iterator().next();
		assertEquals(state.requirementSecond(), restriction.unsatisfiedRequirement());
		assertEquals(state.product(), restriction.product());
		assertEquals(new LicenseInvalid(), restriction.reason());
	}

	@Test
	public void restrictsNothingOnEmptyRequest() {
		Collection<Restriction> restrictions = examiner().examine(//
				Collections.emptyList(), //
				Collections.emptyList(), //
				new FakeLicensedProduct());
		assertNotNull(restrictions);
		assertTrue(restrictions.isEmpty());
	}

	@Test
	public void restrictsNothingOnEmptyRequirements() {
		TestState state = new TestState();
		Collection<Restriction> restrictions = examiner().examine(//
				Collections.emptyList(), //
				Collections.singleton(state.permissionFirst()), //
				state.product());
		assertNotNull(restrictions);
		assertTrue(restrictions.isEmpty());
	}

	@Override
	protected PermissionsExaminationService examiner() {
		return new BasePermissionsExaminationService();
	}

}
