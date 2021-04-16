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
package org.eclipse.passage.lic.internal.jface.actions;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.jface.EquinoxPassageUI;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public final class LicensedRunnable implements Runnable {

	private final Supplier<Shell> shell;
	private final String feature;
	private final Runnable action;

	public LicensedRunnable(Supplier<Shell> shell, String feature, Runnable action) {
		this.shell = shell;
		this.feature = feature;
		this.action = action;
	}

	public LicensedRunnable(String feature, Runnable action) {
		this(Display.getDefault()::getActiveShell, feature, action);
	}

	@Override
	public void run() {
		Optional<ServiceInvocationResult<GrantLockAttempt>> response = Optional.empty();
		try {
			response = Optional.of(new EquinoxPassageUI(shell).acquireLicense(feature));
			if (grantAcquired(response)) {
				action.run();
			}
		} finally {
			response.flatMap(ServiceInvocationResult::data)//
					.ifPresent(lock -> new EquinoxPassage().releaseLicense(lock));
		}
	}

	private boolean grantAcquired(Optional<ServiceInvocationResult<GrantLockAttempt>> response) {
		return response//
				.flatMap(ServiceInvocationResult::data)//
				.map(GrantLockAttempt::successful)//
				.orElse(false);
	}

}
