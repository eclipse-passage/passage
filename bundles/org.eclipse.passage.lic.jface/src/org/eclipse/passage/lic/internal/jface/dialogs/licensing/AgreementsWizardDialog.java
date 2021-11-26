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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Collection;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.internal.jface.i18n.AgreementsDialogMessages;
import org.eclipse.swt.widgets.Shell;

final class AgreementsWizardDialog extends WizardDialog {

	public AgreementsWizardDialog(Shell shell, Collection<AgreementToAccept> agreements) {
		super(shell, new AgreementsWizard(agreements));
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(AgreementsDialogMessages.AgreementsWizardDialog_title);
		shell.setImage(getDefaultImage());
		shell.setSize(840, 600);
	}
}
