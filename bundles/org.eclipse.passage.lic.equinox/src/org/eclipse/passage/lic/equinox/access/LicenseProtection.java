/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.equinox.access;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.equinox.EquinoxPassage;

public final class LicenseProtection {

	private final Logger log = LogManager.getLogger(getClass());
	private Optional<GrantLockAttempt> lock = Optional.empty();
	private final Supplier<Interaction> interaction;

	public LicenseProtection() {
		this(ConsoleInteraction::new);
	}

	public LicenseProtection(Supplier<Interaction> interaction) {
		this.interaction = interaction;
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
		return !LicenseCoverageCheck.Result.proceed.equals(new LicenseCoverageCheck(interaction.get()).run());
	}

	private Optional<GrantLockAttempt> acquireLicense() {
		return product().flatMap(this::acquireLicense);
	}

	private Optional<LicensedProduct> product() {
		ServiceInvocationResult<LicensedProduct> product = new EquinoxPassage().product();
		if (!product.data().isPresent()) {
			log.error(String.format(//
					"Failed to read product credentials:%s", //$NON-NLS-1$
					new DiagnosticExplained(product.diagnostic()).get()));

		}
		return product.data();
	}

	private Optional<GrantLockAttempt> acquireLicense(LicensedProduct product) {
		ServiceInvocationResult<GrantLockAttempt> response = new EquinoxPassage().acquireLicense(product.identifier());
		if (!successful(response)) {
			log.error(String.format(//
					"Failed to acquire license \nfor product %s:\n%s\n", //$NON-NLS-1$
					product, //
					new DiagnosticExplained(response.diagnostic()).get()));
			return Optional.empty();
		}
		return Optional.of(response.data().get());
	}

	private boolean successful(ServiceInvocationResult<GrantLockAttempt> response) {
		return response.data().map(GrantLockAttempt::successful).orElse(Boolean.FALSE);
	}

}
