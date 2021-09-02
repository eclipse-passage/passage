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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.RequirementsCoverage;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.base.diagnostic.RequirementStatus;
import org.eclipse.passage.lic.base.restrictions.ExaminationExplained;
import org.eclipse.passage.lic.equinox.ProductContacts;
import org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public final class LicenseStatusDialog extends NotificationDialog {

	private final ExaminationCertificate certificate;
	private final Diagnostic diagnostic;
	private GoodIntention intention = new GoodIntention.Nope(); // truly mutable ^:(
	private StyledText notice;

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
		shell.setSize(840, 600);
	}

	@Override
	protected void buildUI(Composite parent) {
		viewer = new HereTable<RequirementStatus>(parent, RequirementStatus.class) //
				.withColumn(LicenseStatusDialogMessages.LicenseStatusDialog_column_id, //
						600, RequirementStatus::feature)
				.withColumn(LicenseStatusDialogMessages.LicenseStatusDialog_column_status, //
						200, RequirementStatus::status)
				.viewer();
		notice = new StyledText(parent, SWT.BORDER | SWT.READ_ONLY);
		notice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	@Override
	protected void inplaceData() {
		viewer.setInput(new RequirementsCoverage(certificate).get());
		notice.setText(new ProductContacts().get());
	}

	@Override
	protected void initButtons() {
		int button = 1;
		new ButtonConfig(button++, this::requestLicense, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_request, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_request_tooltip, "")//$NON-NLS-1$
						.reside(buttons);
		new ButtonConfig(button++, this::importLicense, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_import, //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_import_tooltip, "") //$NON-NLS-1$
						.reside(buttons);
		new ButtonConfig(button++, copy(), //
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_copy,
				LicenseStatusDialogMessages.LicenseStatusDialog_intention_copy_tooltip, "") //$NON-NLS-1$
						.reside(buttons);
		if (haveUnacceptedAgreements()) {
			new ButtonConfig(button++, this::exposeAgreements, //
					LicenseStatusDialogMessages.LicenseStatusDialog_intention_accept,
					LicenseStatusDialogMessages.LicenseStatusDialog_intention_accept_tooltip, "") //$NON-NLS-1$
							.reside(buttons);
		}
		if (!new NoErrors().test(diagnostic)) {
			new ButtonConfig(button++, this::diagnose, //
					LicenseStatusDialogMessages.LicenseStatusDialog_intention_diagnose,
					LicenseStatusDialogMessages.LicenseStatusDialog_intention_diagnose_tooltip, "") //$NON-NLS-1$
							.reside(buttons);
		}
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

	private void diagnose() {
		intention = new GoodIntention.Diagnose(this::getShell, diagnostic);
		super.okPressed();
	}

	private void exposeAgreements() {
		intention = new GoodIntention.ExposeLicenseAgreements(this::getShell, toExpose(certificate.agreements()));
		super.okPressed();
	}

	private Collection<AgreementToAccept> toExpose(Collection<AgreementToAccept> agreements) {
		return agreements.stream()//
				.filter(this::toExpose)//
				.collect(Collectors.toList());
	}

	private boolean toExpose(AgreementToAccept agreement) {
		return !agreement.acceptance().accepted() && !agreement.acceptance().error().isPresent();
	}

	private boolean haveUnacceptedAgreements() {
		return certificate.agreements().stream()//
				.filter(this::toExpose)//
				.findAny()//
				.isPresent();
	}

	private Runnable copy() {
		return new CopyToClipboard(this::getShell, //
				new ExaminationExplained(certificate), //
				new DiagnosticExplained(diagnostic));
	}

}
