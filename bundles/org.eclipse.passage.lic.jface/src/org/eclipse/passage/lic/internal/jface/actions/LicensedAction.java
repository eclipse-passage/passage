/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import org.eclipse.jface.action.Action;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.passage.lic.internal.jface.EquinoxPassageUI;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

/**
 * @since 1.1
 */
public abstract class LicensedAction extends Action {

	protected abstract void doAction();

	@Override
	public final void runWithEvent(Event event) {
		runEverywhere(event.display);
	}

	@Override
	public final void run() {
		runEverywhere(Display.getDefault());
	}

	private void runEverywhere(Display display) {
		Optional<ServiceInvocationResult<GrantLockAttempt>> response = Optional.empty();
		try {
			response = Optional.of(new EquinoxPassageUI(display::getActiveShell).acquireLicense(getId()));
			if (grantAcquired(response)) {
				doAction();
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
