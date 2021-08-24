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
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeLicensedProduct;
import org.eclipse.passage.lic.api.tests.resrictions.PermissionsExaminationServiceContractTest;
import org.eclipse.passage.lic.base.agreements.BaseAgreementAcceptanceService;
import org.eclipse.passage.lic.base.diagnostic.code.InsufficientLicenseCoverage;
import org.eclipse.passage.lic.base.io.MD5Hashes;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.base.restrictions.BasePermissionsExaminationService;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BasePermissionsExaminationServiceTest extends PermissionsExaminationServiceContractTest {

	@Test
	public void detectsSingleRequirementSatisfaction() {
		TestState state = new TestState();
		testSuccess(//
				Collections.singleton(state.requirementFirst()), //
				Collections.singleton(state.permissionFirst()), //
				state.product());
	}

	@Test
	public void detectsSeveralRequirementsSatisfaction() {
		TestState state = new TestState();
		testSuccess(//
				Arrays.asList(state.requirementFirst(), state.requirementSecond()), //
				Arrays.asList(state.permissionSecond(), state.permissionFirst()), //
				state.product());
	}

	@Test
	public void detectsPartialRequirementsUnsatisfaction() {
		// given
		TestState state = new TestState();
		// when examine two requirements against one permission for the requirement2
		ExaminationCertificate certificate = examiner(state::product).examine(//
				Arrays.asList(state.requirementFirst(), state.requirementSecond()), //
				Arrays.asList(state.permissionSecond()));
		// then: examination is failed
		assertTrue(new CertificateIsRestrictive().test(Optional.of(certificate)));
		// then: the first of the requirement stays unsatisfied
		assertNotNull(certificate.restrictions());
		assertEquals(1, certificate.restrictions().size());
		// then: and the restriction describes all the data correctly
		Restriction restriction = certificate.restrictions().iterator().next();
		assertEquals(state.requirementFirst(), restriction.unsatisfiedRequirement());
		assertEquals(state.product(), restriction.product());
		assertEquals(new InsufficientLicenseCoverage(), restriction.reason());
		// then: our only permission is counted as active
		assertPermissionHasDoneItsWork(state.permissionSecond(), certificate);
	}

	private void assertPermissionHasDoneItsWork(Permission permission, ExaminationCertificate certificate) {
		Collection<Requirement> satisfied = certificate.satisfied();
		assertEquals(1, satisfied.size());
		Permission satisfaction = certificate.satisfaction(satisfied.iterator().next());
		assertEquals(permission, satisfaction);
	}

	@Test
	public void detectsRequirementUnsatisfactionOnObsoleteCondition() {
		TestState state = new TestState();
		Collection<Restriction> restrictions = examiner(state::product).examine(//
				Arrays.asList(state.requirementSecond()), //
				Arrays.asList(state.permissionSecondObsolete()))//
				.restrictions();
		assertNotNull(restrictions);
		assertEquals(1, restrictions.size());
		Restriction restriction = restrictions.iterator().next();
		assertEquals(state.requirementSecond(), restriction.unsatisfiedRequirement());
		assertEquals(state.product(), restriction.product());
		assertEquals(new InsufficientLicenseCoverage(), restriction.reason());
	}

	@Test
	public void restrictsNothingOnEmptyRequest() {
		testSuccess(Collections.emptyList(), Collections.emptyList(), new FakeLicensedProduct());
	}

	@Test
	public void restrictsNothingOnEmptyRequirements() {
		TestState state = new TestState();
		testSuccess(//
				Collections.emptyList(), //
				Collections.singleton(state.permissionFirst()), //
				state.product());
	}

	@Override
	protected PermissionsExaminationService examiner(Supplier<LicensedProduct> product) {
		return new BasePermissionsExaminationService(//
				new BaseAgreementAcceptanceService(() -> new ReadOnlyRegistry<>(new MD5Hashes()), product), //
				product);
	}

	private void testSuccess(//
			Collection<Requirement> requirements, //
			Collection<Permission> permissions, //
			LicensedProduct product) {
		ExaminationCertificate certificate = examiner(() -> product).examine(requirements, permissions);
		assertNotNull(certificate);
		assertTrue(certificate.restrictions().isEmpty());
	}
}
