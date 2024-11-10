/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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
package org.eclipse.passage.lic.equinox;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;

/**
 * @since 2.1
 */
public abstract class LicensedRunnable implements Runnable {

	private final FeatureIdentifier feature;
	private final Runnable action;
	private final Consumer<ServiceInvocationResult<GrantLockAttempt>> fallback;

	protected LicensedRunnable(FeatureIdentifier feature, Runnable action,
			Consumer<ServiceInvocationResult<GrantLockAttempt>> fallback) {
		this.feature = feature;
		this.action = action;
		this.fallback = fallback;
	}

	public LicensedRunnable(FeatureIdentifier feature, Runnable action) {
		this(feature, action, response -> {
		});
	}

	@Override
	public final void run() {
		Optional<ServiceInvocationResult<GrantLockAttempt>> response = Optional.empty();
		try {
			response = Optional.of(acquireLicense(feature));
			if (grantAcquired(response)) {
				action.run();
			} else {
				fallback.accept(response.get());
			}
		} finally {
			response.flatMap(ServiceInvocationResult::data)//
					.ifPresent(new EquinoxPassage()::releaseLicense);
		}
	}

	private boolean grantAcquired(Optional<ServiceInvocationResult<GrantLockAttempt>> response) {
		return response//
				.flatMap(ServiceInvocationResult::data)//
				.map(GrantLockAttempt::successful)//
				.orElse(false);
	}

	@SuppressWarnings("hiding")
	protected abstract ServiceInvocationResult<GrantLockAttempt> acquireLicense(FeatureIdentifier feature);

	public static final class Default extends LicensedRunnable {

		public Default(FeatureIdentifier feature, Runnable action) {
			super(feature, action);
		}

		public Default(FeatureIdentifier feature, Runnable action,
				Consumer<ServiceInvocationResult<GrantLockAttempt>> fallback) {
			super(feature, action, fallback);
		}

		@Override
		protected ServiceInvocationResult<GrantLockAttempt> acquireLicense(FeatureIdentifier feature) {
			return new EquinoxPassage().acquireLicense(feature);
		}

	}

}
