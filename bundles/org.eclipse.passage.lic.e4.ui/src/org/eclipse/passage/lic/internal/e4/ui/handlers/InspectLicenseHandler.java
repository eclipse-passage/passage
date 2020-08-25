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
package org.eclipse.passage.lic.internal.e4.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassageLicenseCoverage;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.LicenseStatusDialog;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class InspectLicenseHandler {

	@Execute
	public void execute(Shell shell) {
		ServiceInvocationResult<ExaminationCertificate> result = new EquinoxPassageLicenseCoverage().assess();
		if (result.data().isPresent()) {
			new LicenseStatusDialog(shell, result.data().get(), result.diagnostic()).open();
		} else {
			new DiagnosticDialog(shell, result.diagnostic()).open();
		}

	}
}
