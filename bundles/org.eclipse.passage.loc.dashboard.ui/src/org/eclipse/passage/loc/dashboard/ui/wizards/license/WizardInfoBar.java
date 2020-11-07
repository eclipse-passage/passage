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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.Optional;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;

public final class WizardInfoBar {

	private final IWizard wizard;

	public WizardInfoBar(IWizard wizard) {
		this.wizard = wizard;
	}

	public void installError(String error) {
		infoBar().ifPresent(dialog -> dialog.setErrorMessage(error));
	}

	public void wipe() {
		infoBar().ifPresent(dialog -> dialog.setMessage("")); //$NON-NLS-1$
	}

	private Optional<TitleAreaDialog> infoBar() {
		IWizardContainer container = wizard.getContainer();
		if (container instanceof TitleAreaDialog) {
			return Optional.of((TitleAreaDialog) container);
		}
		return Optional.empty();
	}

}
