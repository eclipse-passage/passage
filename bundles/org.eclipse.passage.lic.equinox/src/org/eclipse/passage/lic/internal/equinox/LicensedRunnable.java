/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Optional;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;

public abstract class LicensedRunnable implements Runnable {

	private final String feature;
	private final Runnable action;

	public LicensedRunnable(String feature, Runnable action) {
		this.feature = feature;
		this.action = action;
	}

	@Override
	public final void run() {
		Optional<ServiceInvocationResult<GrantLockAttempt>> response = Optional.empty();
		try {
			response = Optional.of(acquireLicense(feature));
			if (grantAcquired(response)) {
				action.run();
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
	protected abstract ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature);

	public static final class Default extends LicensedRunnable {

		public Default(String feature, Runnable action) {
			super(feature, action);
		}

		@Override
		protected ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature) {
			return new EquinoxPassage().acquireLicense(feature);
		}

	}

}
