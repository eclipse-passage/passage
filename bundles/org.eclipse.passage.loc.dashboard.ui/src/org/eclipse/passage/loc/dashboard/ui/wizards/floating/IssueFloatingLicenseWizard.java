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
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;

public final class IssueFloatingLicenseWizard extends Wizard {

	private final IEclipseContext context;

	public IssueFloatingLicenseWizard(IEclipseContext context) {
		this.context = context;
		setWindowTitle(IssueLicensePageMessages.IssueFloatingLicenseWizard_title);
	}

	@Override
	public void addPages() {
		addPage(new GrabDataPage(context));
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
