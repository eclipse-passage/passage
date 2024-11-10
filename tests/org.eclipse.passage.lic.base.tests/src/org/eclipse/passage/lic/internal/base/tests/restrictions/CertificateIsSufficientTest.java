/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.restrictions.CertificateIsSufficient;
import org.junit.Test;

public final class CertificateIsSufficientTest {

	@Test
	public void absentCertificateIsNotSufficient() {
		assertFalse(new CertificateIsSufficient(new BaseFeatureIdentifier("any")).test(Optional.empty())); //$NON-NLS-1$
	}

	@Test
	public void assessedFeatureIsRestricted() {
		FeatureIdentifier feature = feature();
		assertFalse(new CertificateIsSufficient(feature).test(Optional.of(severe(feature))));
	}

	@Test
	public void assessedFeatureIsSoftlyRestricted() {
		FeatureIdentifier feature = feature();
		assertFalse(new CertificateIsSufficient(feature).test(Optional.of(warn(feature))));
	}

	@Test
	public void notAssessedFeatureIsRestricted() {
		FeatureIdentifier feature = feature();
		assertTrue(new CertificateIsSufficient(feature).test(Optional.of(severe())));
	}

	@Test
	public void assessedFeatureDemandsAgreementNotAccepted() {
		FeatureIdentifier feature = feature();
		Optional<ExaminationCertificate> certificate = Optional.of(agreementNotAccepted(feature));
		assertFalse(new CertificateIsSufficient(feature).test(certificate));
	}

	@Test
	public void notAssessedFeatureDemandsAgreementNotAccepted() {
		FeatureIdentifier feature = feature();
		assertTrue(new CertificateIsSufficient(feature).test(Optional.of(agreementNotAccepted())));
	}

	private FeatureIdentifier feature() {
		return new BaseFeatureIdentifier("feature-under-assessment-" + Long.toHexString(System.currentTimeMillis())); //$NON-NLS-1$
	}

	private ExaminationCertificate severe(FeatureIdentifier feature) {
		return new TestCertificates().withSevereRestrictions(feature);
	}

	private ExaminationCertificate warn(FeatureIdentifier feature) {
		return new TestCertificates().withWarningRestrictions(feature);
	}

	private ExaminationCertificate severe() {
		return new TestCertificates().withSevereRestrictions();
	}

	private ExaminationCertificate agreementNotAccepted(FeatureIdentifier feature) {
		return new TestCertificates().withAgreementRestrictions(feature);
	}

	private ExaminationCertificate agreementNotAccepted() {
		return new TestCertificates().withAgreementRestrictions();
	}

}
