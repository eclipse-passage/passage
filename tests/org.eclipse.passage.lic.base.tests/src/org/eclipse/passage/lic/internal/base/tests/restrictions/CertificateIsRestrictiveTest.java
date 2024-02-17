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
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;
import org.junit.Test;

public final class CertificateIsRestrictiveTest {

	@Test
	public void noCertificateMeansNoRestriction() {
		assertTrue(new CertificateIsRestrictive().test(Optional.empty()));
	}

	@Test
	public void onlyBearableRestrictions() {
		assertFalse(new CertificateIsRestrictive().test(Optional.of(bearable())));
	}

	@Test
	public void withSevereRestrictions() {
		assertTrue(new CertificateIsRestrictive().test(Optional.of(severe())));
	}

	@Test
	public void withAgreementRestrictions() {
		assertTrue(new CertificateIsRestrictive().test(Optional.of(agreementNotAccepted())));
	}

	@Test
	public void noRestrictions() {
		assertFalse(new CertificateIsRestrictive().test(Optional.of(agreementAccepted())));
	}

	private ExaminationCertificate bearable() {
		return new TestCertificates().onlyBearableRestrictions();
	}

	private ExaminationCertificate severe() {
		return new TestCertificates().withSevereRestrictions();
	}

	private ExaminationCertificate agreementNotAccepted() {
		return new TestCertificates().withAgreementRestrictions();
	}

	private ExaminationCertificate agreementAccepted() {
		return new TestCertificates().agreementAccepted();
	}

}
