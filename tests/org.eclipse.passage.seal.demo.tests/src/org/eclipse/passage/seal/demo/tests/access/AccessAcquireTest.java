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
package org.eclipse.passage.seal.demo.tests.access;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.Consumer;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.diagnostic.code.LicenseExpired;
import org.eclipse.passage.lic.base.diagnostic.code.LicenseNotStarted;
import org.eclipse.passage.lic.base.diagnostic.code.NoRequirements;
import org.eclipse.passage.lic.base.diagnostic.code.TentativeAccess;
import org.eclipse.passage.lic.internal.base.access.Access;
import org.junit.Test;

/**
 * Integration test: demands OSGi running
 */
public final class AccessAcquireTest {

	@Test
	/**
	 * Is not covered by license, but has {@code info} restriction
	 */
	public void acquireTentativeGrant() {
		successfullyAcquireAndRelease(//
				new TestFramework.Everlasting(), //
				"frog-to-prince", //$NON-NLS-1$
				diagnostic -> {
					assertTrue(diagnostic.bearable().size() > 0);
					assertContainsCode(new TentativeAccess(), diagnostic.bearable());
				});
	}

	@Test
	/**
	 * Is covered by license, has {@code error} restriction
	 */
	public void acquireLicenseGrant() {
		successfullyAcquireAndRelease(//
				new TestFramework.Everlasting(), //
				"prince-to-frog", //$NON-NLS-1$
				diagnostic -> System.out.println(new DiagnosticExplained(diagnostic).get()));
	}

	@Test
	/**
	 * No licensing requirement is declared for this feature: severe error is
	 * expected
	 */
	public void denyUnknownFeatureAcquisition() {
		ServiceInvocationResult<GrantLockAttempt> acquire = new Access(new TestFramework.Everlasting())
				.acquire("unknown"); //$NON-NLS-1$
		assertFalse(new NoSevereErrors().test(acquire.diagnostic()));
		assertFalse(acquire.data().isPresent());
		assertTrue(acquire.diagnostic().severe().size() > 0);
		assertContainsCode(new NoRequirements(), acquire.diagnostic().severe());
	}

	@Test
	/**
	 * License is expired. For warn feature tentative access must be granted, but
	 * license expiration must be reported on a morsel failure level
	 */
	public void denyFromExpiredLicense() {
		ServiceInvocationResult<GrantLockAttempt> acquire = new Access(new TestFramework.Expired())
				.acquire("anti-human-magic.product"); //$NON-NLS-1$
		assertTrue(new NoSevereErrors().test(acquire.diagnostic()));
		assertTrue(acquire.data().isPresent());
		assertContainsCode(new LicenseExpired(), acquire.diagnostic().bearable());
	}

	@Test
	/**
	 * License not started. For warn feature tentative access must be granted, but
	 * license-not-started must be reported on a morsel failure level
	 */
	public void denyFromNotStartedLicense() {
		ServiceInvocationResult<GrantLockAttempt> acquire = new Access(new TestFramework.NotStarted())
				.acquire("anti-human-magic.product"); //$NON-NLS-1$
		assertTrue(new NoSevereErrors().test(acquire.diagnostic()));
		assertTrue(acquire.data().isPresent());
		assertContainsCode(new LicenseNotStarted(), acquire.diagnostic().bearable());
	}

	private void assertContainsCode(TroubleCode expected, List<Trouble> actual) {
		assertTrue(actual.stream()//
				.map(Trouble::code)//
				.anyMatch(trouble -> trouble.code() == expected.code()));
	}

	@Test
	/**
	 * Required and error-restricted feature is not covered by license: supply
	 * assessment and deny acquisition
	 */
	public void denyUnlicensedFeatureAcquisition() {
		ServiceInvocationResult<GrantLockAttempt> acquire = new Access(new TestFramework.Everlasting())
				.acquire("frog-firework"); //$NON-NLS-1$
		assertTrue(//
				new DiagnosticExplained(acquire.diagnostic()).get(), //
				new NoSevereErrors().test(acquire.diagnostic()));
		assertTrue(acquire.data().isPresent());
		assertFalse(acquire.data().get().successful());
	}

	private void successfullyAcquireAndRelease(TestFramework framework, String feature, Consumer<Diagnostic> report) {
		Access access = new Access(framework);
		GrantLockAttempt lock = successfullyAcquire(feature, access, report);
		successfullyRelease(lock, access);
	}

	private void successfullyRelease(GrantLockAttempt lock, Access access) {
		ServiceInvocationResult<Boolean> release = access.release(lock);
		assertTrue(new NoErrors().test(release.diagnostic()));
		assertTrue(release.data().isPresent());
		assertTrue(release.data().get());
	}

	private GrantLockAttempt successfullyAcquire(String feature, Access access, Consumer<Diagnostic> onDiagnostic) {
		ServiceInvocationResult<GrantLockAttempt> acquire = access.acquire(feature);
		assertTrue(//
				new DiagnosticExplained(acquire.diagnostic()).get(), //
				new NoSevereErrors().test(acquire.diagnostic()));
		onDiagnostic.accept(acquire.diagnostic());
		assertTrue(acquire.data().isPresent());
		assertTrue(acquire.data().get().successful());
		return acquire.data().get();
	}

}
