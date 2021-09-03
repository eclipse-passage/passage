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
package org.eclipse.passage.lic.jface.actions;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.equinox.LicensedRunnable;
import org.eclipse.passage.lic.jface.EquinoxPassageUI;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @since 2.1
 */
public final class LicensedRunnableUI extends LicensedRunnable {

	private final Supplier<Shell> shell;

	public LicensedRunnableUI(Supplier<Shell> shell, String feature, Runnable action,
			Consumer<ServiceInvocationResult<GrantLockAttempt>> fallback) {
		super(feature, action, fallback);
		this.shell = shell;
	}

	public LicensedRunnableUI(Supplier<Shell> shell, String feature, Runnable action) {
		this(shell, feature, action, response -> {
		});
	}

	public LicensedRunnableUI(String feature, Runnable action) {
		this(Display.getDefault()::getActiveShell, feature, action);
	}

	@Override
	protected ServiceInvocationResult<GrantLockAttempt> acquireLicense(String feature) {
		return new EquinoxPassageUI(shell).acquireLicense(feature);
	}

}
