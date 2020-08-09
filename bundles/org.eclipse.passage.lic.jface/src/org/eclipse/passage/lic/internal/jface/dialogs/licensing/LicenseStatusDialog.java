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

import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.restrictions.ExaminationExplained;
import org.eclipse.passage.lic.jface.dialogs.ImportLicenseDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class LicenseStatusDialog extends NotificationDialog {

	private final ExaminationCertificate origin;
	private boolean imported = false; // truly mutable ^:(

	public LicenseStatusDialog(Shell shell, ExaminationCertificate certificate) {
		super(shell);
		this.origin = certificate;
	}

	public boolean licenseHasBeenImported() {
		return imported;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Licensing status"); //$NON-NLS-1$
		shell.setImage(getDefaultImage());
		shell.setSize(520, 300);
	}

	@Override
	protected void buildUI(Composite parent) {
		viewer = new LicensingTable<Restriction>(parent, Restriction.class) //
				.withColumn("Name", 300, r -> r.unsatisfiedRequirement().feature().identifier()) //$NON-NLS-1$
				.withColumn("Version", 100, r -> r.unsatisfiedRequirement().feature().version()) //$NON-NLS-1$
				.withColumn("Verdict", 100, r -> r.reason().explanation()) //$NON-NLS-1$
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

	private void requestLicense() {
		// FIXME: do it, request!
	}

	private void importLicense() {
		// FIXME: reimplement this one too
		ImportLicenseDialog dialog = new ImportLicenseDialog(getShell(), null);
		imported = (Window.OK == dialog.open());
	}

}
