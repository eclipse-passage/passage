/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.lic.jface;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.PassageUI;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.BaseLicenseStatusDialog;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.LicenseStatusDialog;
import org.eclipse.swt.widgets.Shell;

public final class EquinoxPassageUI extends BaseEquinoxPassageUI implements PassageUI {

	public EquinoxPassageUI(Supplier<Shell> shell) {
		super(shell);
	}

	@Override
	protected BaseLicenseStatusDialog licenseStatusDialog(Shell sh, ExaminationCertificate certificate,
			Diagnostic diagnostic) {
		return new LicenseStatusDialog(shell.get(), certificate, diagnostic);
	}

}
