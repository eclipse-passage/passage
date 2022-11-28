/*******************************************************************************
 * Copyright (c) 2019, 2022 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.DiagnosticDialog;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.WizardInfoBar;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.users.ui.UsersUi;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class IssueLicenseWizard extends Wizard {

	private final IEclipseContext context;
	private final PersonalDataPack initial;
	private IssueLicenseRequestPage request;
	private IssueLicensePackPage pack;
	private IssueLicenseDetailsPage info;

	public IssueLicenseWizard(IEclipseContext context, PersonalDataPack initial) {
		this.context = context;
		this.initial = initial;
		setWindowTitle(IssueLicensePageMessages.IssueLicenseWizard_window_title);
	}

	@Override
	public void addPages() {
		request = new IssueLicenseRequestPage(context, initial);
		addPage(request.get());
		pack = new IssueLicensePackPage(IssueLicensePackPage.class.getName(), request::request, context);
		addPage(pack);
		info = new IssueLicenseDetailsPage(IssueLicenseDetailsPage.class.getName(), pack::pack);
		addPage(info);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage next = super.getNextPage(page);
		if (next instanceof TwoPhaseWizardPage) {
			((TwoPhaseWizardPage) next).init();
		}
		return next;
	}

	@Override
	public boolean performFinish() {
		OperatorLicenseService licenseService = context.get(OperatorLicenseService.class);
		PersonalLicensePackDescriptor licensePack = pack.pack();
		ServiceInvocationResult<IssuedLicense> result = licenseService.issueLicensePack(request.request(), licensePack);
		if (!new NoSevereErrors().test(result.diagnostic())) {
			new WizardInfoBar(this).installError("Export failed"); //$NON-NLS-1$
			new DiagnosticDialog(getShell(), result.diagnostic()).open();
			return false;
		} else {
			new WizardInfoBar(this).wipe();
			new LicenseIssuedNotification(getShell()).showPersonal(result.data().get());
			broadcast(result.data().get());
			return true;
		}
	}

	private void broadcast(IssuedLicense result) {
		PersonalLicensePack userLicense = result.license();
		String perspectiveId = UsersUi.PERSPECTIVE_MAIN;
		LocWokbench.switchPerspective(context, perspectiveId);
		IEventBroker broker = context.get(IEventBroker.class);
		broker.post(LocWokbench.TOPIC_SHOW, userLicense);
	}

}
