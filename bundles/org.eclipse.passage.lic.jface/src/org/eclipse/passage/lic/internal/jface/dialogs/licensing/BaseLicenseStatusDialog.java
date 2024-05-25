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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.base.diagnostic.RequirementStatus;
import org.eclipse.passage.lic.base.diagnostic.RequirementsCoverage;
import org.eclipse.passage.lic.base.restrictions.ExaminationExplained;
import org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public abstract class BaseLicenseStatusDialog extends NotificationDialog {

	protected final ExaminationCertificate certificate;
	protected final Diagnostic diagnostic;
	private GoodIntention intention = new GoodIntention.Nope(); // truly mutable ^:(
	private StyledText notice;

	protected BaseLicenseStatusDialog(Shell shell, ExaminationCertificate certificate, Diagnostic diagnostic) {
		super(shell);
		this.certificate = certificate;
		this.diagnostic = diagnostic;
	}

	public final GoodIntention goodIntention() {
		return intention;
	}

	@Override
	protected final void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(LicenseStatusDialogMessages.LicenseStatusDialog_title);
		shell.setImage(getDefaultImage());
		shell.setSize(1200, 600);
	}

	@Override
	protected final void buildUI(Composite parent) {
		viewer = new HereTable<RequirementStatus>(parent, RequirementStatus.class) //
				.withColumn(LicenseStatusDialogMessages.LicenseStatusDialog_column_id, //
						600, RequirementStatus::feature)
				.withColumn(LicenseStatusDialogMessages.LicenseStatusDialog_column_status, //
						500, RequirementStatus::status)
				.viewer();
		notice = new StyledText(parent, SWT.BORDER | SWT.READ_ONLY);
		notice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	@Override
	protected final void inplaceData() {
		viewer.setInput(new RequirementsCoverage(certificate).get());
		notice.setText(new ProductContacts().get());
	}

	@Override
	protected final void initButtons() {
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
	protected final void updateButtonsEnablement() {
		// do nothing
	}

	@Override
	protected final void initMessage() {
		new CertificateSummary(certificate).accept(this);
	}

	protected abstract GoodIntention requestLicenseIntention();

	protected abstract GoodIntention importLicenseIntention();

	protected abstract GoodIntention diagnoseIntention();

	protected abstract GoodIntention exposeLicenseAgreementsIntention(Collection<AgreementToAccept> collection);

	private void requestLicense() {
		intention = requestLicenseIntention();
		super.okPressed();
	}

	private void importLicense() {
		intention = importLicenseIntention();
		super.okPressed();
	}

	private void diagnose() {
		intention = diagnoseIntention();
		super.okPressed();
	}

	private void exposeAgreements() {
		intention = exposeLicenseAgreementsIntention(toExpose(certificate.agreements()));
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
