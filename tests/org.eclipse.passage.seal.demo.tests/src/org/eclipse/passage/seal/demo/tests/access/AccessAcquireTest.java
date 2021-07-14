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
import org.eclipse.passage.lic.base.access.Access;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.diagnostic.code.NoRequirements;
import org.eclipse.passage.lic.base.diagnostic.code.TentativeAccess;
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
		successfullyAcquireAndRelease("frog-to-prince", //$NON-NLS-1$
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
		successfullyAcquireAndRelease("prince-to-frog", //$NON-NLS-1$
				diagnostic -> System.out.println(new DiagnosticExplained(diagnostic).get()));
	}

	@Test
	/**
	 * No licensing requirement is declared for this feature: severe error is
	 * expected
	 */
	public void denyUnknownFeatureAcquisition() {
		ServiceInvocationResult<GrantLockAttempt> acquire = new Access(new TestFramework()).acquire("unknown"); //$NON-NLS-1$
		assertFalse(new NoSevereErrors().test(acquire.diagnostic()));
		assertFalse(acquire.data().isPresent());
		assertTrue(acquire.diagnostic().severe().size() > 0);
		assertContainsCode(new NoRequirements(), acquire.diagnostic().severe());
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
		ServiceInvocationResult<GrantLockAttempt> acquire = new Access(new TestFramework()).acquire("frog-firework"); //$NON-NLS-1$
		assertTrue(//
				new DiagnosticExplained(acquire.diagnostic()).get(), //
				new NoSevereErrors().test(acquire.diagnostic()));
		assertTrue(acquire.data().isPresent());
		assertFalse(acquire.data().get().successful());
	}

	private void successfullyAcquireAndRelease(String feature, Consumer<Diagnostic> onDiagnostic) {
		Access access = new Access(new TestFramework());
		GrantLockAttempt lock = successfullyAcquire(feature, access, onDiagnostic);
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
