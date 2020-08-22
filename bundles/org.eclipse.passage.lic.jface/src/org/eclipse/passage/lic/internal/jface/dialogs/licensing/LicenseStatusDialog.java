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
import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.restrictions.ExaminationExplained;
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
		shell.setText("Licensing status"); //$NON-NLS-1$
		shell.setImage(getDefaultImage());
		shell.setSize(740, 300);
	}

	@Override
	protected void buildUI(Composite parent) {
		viewer = new HereTable<Restriction>(parent, Restriction.class) //
				.withColumn("Name", 500, r -> feature(r.unsatisfiedRequirement().feature())) //$NON-NLS-1$
				.withColumn("Verdict", 200, r -> r.reason().explanation()) //$NON-NLS-1$
				.viewer();
	}

	@Override
	protected void inplaceData() {
		viewer.setInput(certificate.restrictions());
	}

	@Override
	protected void initButtons() {
		new ButtonConfig(1, this::requestLicense, "&Request License...", "", "") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
		new ButtonConfig(2, this::importLicense, "&Import License...", "", "")//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
		new ButtonConfig(3, copy(), "Co&py", "", "")//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
	}

	@Override
	protected void updateButtonsEnablement() {
		// do nothing
	}

	@Override
	protected String defaultMessage() {
		return "License coverage is not sufficient"; //$NON-NLS-1$
	}

	private void requestLicense() {
		intention = new GoodIntention.RequestLicense();
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

	private String feature(Feature feature) {
		return String.format("%s v%s", feature.identifier(), feature.version()); //$NON-NLS-1$
	}

}
