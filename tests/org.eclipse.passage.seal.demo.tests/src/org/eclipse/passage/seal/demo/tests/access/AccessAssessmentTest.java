/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
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
package org.eclipse.passage.seal.demo.tests.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.access.Access;
import org.junit.Test;

/**
 * Integration test: demands OSGi running
 */
@SuppressWarnings("restriction")
public final class AccessAssessmentTest {

	@Test
	public void assessWithValidLicense() {
		assess(//
				new TestFramework.Everlasting(), //
				Set.of(//
						new BaseFeatureIdentifier("prince-to-frog"), //$NON-NLS-1$
						new BaseFeatureIdentifier("anti-human-magic.product")) //$NON-NLS-1$
		);
	}

	@Test
	public void assessWithExpiredLicense() {
		assess(//
				new TestFramework.Expired(), //
				new HashSet<>());
	}

	@Test
	public void assessWithNotStartedLicense() {
		assess(//
				new TestFramework.NotStarted(), //
				new HashSet<>());
	}

	private void assess(TestFramework framework, Set<FeatureIdentifier> expected) {
		ServiceInvocationResult<ExaminationCertificate> result = new Access(framework).assess();
		assertTrue(new NoSevereErrors().test(result.diagnostic()));
		assertTrue(result.data().isPresent());
		assertEquals(//
				expected, //
				new HashSet<>(coveredFeatures(result.data().get())));
	}

	private Set<FeatureIdentifier> coveredFeatures(ExaminationCertificate certificate) {
		return certificate.satisfied().stream() //
				.map(Requirement::feature)//
				.map(Feature::identifier)//
				.collect(Collectors.toSet());
	}

}
