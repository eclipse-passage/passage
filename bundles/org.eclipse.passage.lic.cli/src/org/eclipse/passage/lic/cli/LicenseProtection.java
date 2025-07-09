/*******************************************************************************
 * Copyright (c) 2021, 2025 ArSysOp
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
package org.eclipse.passage.lic.cli;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.equinox.EquinoxPassage;

@SuppressWarnings("restriction")
public final class LicenseProtection {

	private Optional<GrantLockAttempt> lock = Optional.empty();
	private final String name;
	private final TheOtherSide communication;
	private final OptionDefinitions opts;

	public LicenseProtection() {
		this(ConsoleInteraction::new);
	}

	public LicenseProtection(Supplier<Interaction> interaction) {
		this(new TheOtherSide.Blind(interaction.get()));
	}

	public LicenseProtection(TheOtherSide communication) {
		this("application", communication); //$NON-NLS-1$
	}

	public LicenseProtection(String name, TheOtherSide communication) {
		this(name, communication, new OptionDefinitions.Default(communication));
	}

	public LicenseProtection(String name, TheOtherSide communication, OptionDefinitions opts) {
		this.name = name;
		this.communication = communication;
		this.opts = opts;
	}

	public boolean check() {
		if (licenseCoverageIsNotSufficient()) {
			return false;
		}
		return locAcquired();
	}

	public void release() {
		lock.ifPresent(acq -> new EquinoxPassage().releaseLicense(acq));
	}

	private boolean locAcquired() {
		Optional<GrantLockAttempt> attempt = acquireLicense();
		if (!attempt.isPresent()) {
			return false;
		}
		if (!attempt.get().successful()) {
			return false;
		}
		this.lock = attempt;
		return true;
	}

	private boolean licenseCoverageIsNotSufficient() {
		return !LicenseCoverageCheck.Result.proceed.equals(new LicenseCoverageCheck(name, communication, opts).run());
	}

	private Optional<GrantLockAttempt> acquireLicense() {
		return acquireLicense(new Product().get());
	}

	private Optional<GrantLockAttempt> acquireLicense(LicensedProduct product) {
		ServiceInvocationResult<GrantLockAttempt> response = new EquinoxPassage()
				.acquireLicense(new BaseFeatureIdentifier(product.identifier()));
		if (!successful(response)) {
			System.err.printf("Failed to acquire license \nfor product %s:\n%s\n", //$NON-NLS-1$
					product, //
					new DiagnosticExplained(response.diagnostic()).get());
			return Optional.empty();
		}
		return Optional.of(response.data().get());
	}

	private boolean successful(ServiceInvocationResult<GrantLockAttempt> response) {
		return response.data().map(GrantLockAttempt::successful).orElse(Boolean.FALSE);
	}

}
