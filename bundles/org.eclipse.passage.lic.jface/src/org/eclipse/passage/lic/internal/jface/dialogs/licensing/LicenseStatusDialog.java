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

import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.restrictions.ExaminationExplained;
import org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class LicenseStatusDialog extends NotificationDialog {

	private final ExaminationCertificate certificate;
	private final Diagnostic diagnostic;
	private GoodIntention intention = new GoodIntention.Nope(); // truly mutable ^:(

	public LicenseStatusDialog(Shell shell, ExaminationCertificate certificate, Diagnostic diagnostic) {
		super(shell);
		this.certificate = certificate;
		this.diagnostic = diagnostic;
	}

	public GoodIntention goodIntention() {
		return intention;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(LicenseStatusDialogMessages.LicenseStatusDialog_title);
		shell.setImage(getDefaultImage());
		shell.setSize(740, 300);
	}

	@Override
	protected void buildUI(Composite parent) {
		viewer = new HereTable<RequirementStatus>(parent, RequirementStatus.class) //
				.withColumn(LicenseStatusDialogMessages.LicenseStatusDialog_column_id, //
						500, RequirementStatus::feature)
				.withColumn(LicenseStatusDialogMessages.LicenseStatusDialog_column_status, //
						200, RequirementStatus::status)
				.viewer();
	}

	@Override
	protected void inplaceData() {
		viewer.setInput(new LicensingStatus(certificate).get());
	}

	@Override
	protected void initButtons() {
		new ButtonConfig(1, this::requestLicense, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_request, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_request_tooltip, "")//$NON-NLS-1$
						.reside(buttons);
		new ButtonConfig(2, this::importLicense, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_import, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_import_tooltip, "") //$NON-NLS-1$
						.reside(buttons);
		new ButtonConfig(3, copy(), //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_copy,
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_copy_tooltip, "") //$NON-NLS-1$
						.reside(buttons);
	}

	@Override
	protected void updateButtonsEnablement() {
		// do nothing
	}

	@Override
	protected void initMessage() {
		new CertificateSummary(certificate).accept(this);
	}

	private void requestLicense() {
		intention = new GoodIntention.RequestLicense(this::getShell);
		super.okPressed();
	}

	private void importLicense() {
		intention = new GoodIntention.ImportLicense(this::getShell);
		super.okPressed();
	}

	private Runnable copy() {
		return new CopyToClipboard(this::getShell, //
				new ExaminationExplained(certificate), //
				new DiagnosticExplained(diagnostic));
	}

}
