/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.access.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.access.NoSevereRestrictions;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class WorkbenchNotification {

	private final Supplier<Shell> shell;

	public WorkbenchNotification(Supplier<Shell> shell) {
		this.shell = shell;
	}

	public boolean expose(ServiceInvocationResult<ExaminationCertificate> result) {
		if (!new NoSevereErrors().test(result.diagnostic())) {
			new DiagnosticDialog(shell.get(), result.diagnostic()).open();
			return false;
		}
		if (!result.data().isPresent()) {
			System.err.println("No data in result!"); //$NON-NLS-1$
			return false;
		}
		if (!new NoSevereRestrictions().test(result.data().get())) {
			LicenseStatusDialog dialog = new LicenseStatusDialog(shell.get(), result.data().get());
			if (Window.OK == dialog.open()) {
				return dialog.licenseHasBeenImported();
			}
			return false;
		}
		return false;
	}
}
