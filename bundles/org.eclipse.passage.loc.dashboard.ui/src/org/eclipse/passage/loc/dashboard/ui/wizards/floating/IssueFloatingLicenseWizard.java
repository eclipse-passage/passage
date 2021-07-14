/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.loc.dashboard.ui.wizards.LicenseIssuedNotification;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.WizardInfoBar;
import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;

public final class IssueFloatingLicenseWizard extends Wizard {

	private final IEclipseContext context;
	private final FloatingDataPack initial;
	private Supplier<FloatingLicensePack> license;
	private Supplier<ServerConfigsRequest> personals;

	public IssueFloatingLicenseWizard(IEclipseContext context, FloatingDataPack initial) {
		this.context = context;
		this.initial = initial;
		setWindowTitle(IssueLicensePageMessages.IssueFloatingLicenseWizard_title);
	}

	@Override
	public void addPages() {
		IssueLicenseRequestPage request = new IssueLicenseRequestPage(context, initial);
		addPage(request.get());
		IssueLicensePackPage pack = new IssueLicensePackPage("License information", request::request, context); //$NON-NLS-1$
		addPage(pack);
		IssueUserConfigsRequestPage configs = new IssueUserConfigsRequestPage(context);
		addPage(configs.get());
		personals = configs::request;
		license = pack::pack;
	}

	@Override
	public boolean performFinish() {
		ServiceInvocationResult<IssuedFloatingLicense> result = new IssueCommand(context, license.get(),
				personals.get()).issue();
		if (!new NoSevereErrors().test(result.diagnostic())) {
			new WizardInfoBar(this).installError(IssueLicensePageMessages.IssueFloatingLicenseWizard_failure);
			new DiagnosticDialog(getShell(), result.diagnostic()).open();
			return false;
		}
		new WizardInfoBar(this).wipe();
		new LicenseIssuedNotification(getShell()).showFloating(result.data().get());
		return true;
	}

}
