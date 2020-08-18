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

import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.restrictions.ExaminationExplained;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class LicenseStatusDialog extends NotificationDialog {

	private final ExaminationCertificate origin;
	private GoodIntention intention = new GoodIntention.Nope(); // truly mutable ^:(

	public LicenseStatusDialog(Shell shell, ExaminationCertificate certificate) {
		super(shell);
		this.origin = certificate;
		super.setMessage("Point a license file to see what's inside"); //$NON-NLS-1$
	}

	public GoodIntention goodIntention() {
		return intention;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Licensing status"); //$NON-NLS-1$
		shell.setImage(getDefaultImage());
		shell.setSize(730, 300);
	}

	@Override
	protected void buildUI(Composite parent) {
		viewer = new LicensingTable<Restriction>(parent, Restriction.class) //
				.withColumn("Name", 500, r -> r.unsatisfiedRequirement().feature().identifier()) //$NON-NLS-1$
				.withColumn("Version", 100, r -> r.unsatisfiedRequirement().feature().version()) //$NON-NLS-1$
				.withColumn("Verdict", 200, r -> r.reason().explanation()) //$NON-NLS-1$
				.viewer();
	}

	@Override
	protected void inplaceData() {
		viewer.setInput(origin.restrictions());
	}

	@Override
	protected void initButtons() {
		new ButtonConfig(1, this::requestLicense, "&Request License...", "", "") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
		new ButtonConfig(2, this::importLicense, "&Import License...", "", "")//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
		new ButtonConfig(3, new CopyToClipboard(this::getShell, new ExaminationExplained(origin)), "Co&py", "", "")//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	}

	private void importLicense() {
		intention = new GoodIntention.ImportLicense(this::getShell);
	}

}
