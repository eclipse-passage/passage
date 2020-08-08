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
package org.eclipse.passage.lic.jface.dialogs.licensingstatus;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.restrictions.ExaminationExplained;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class LicenseStausDialog extends TitleAreaDialog {

	private final ExaminationCertificate origin;
	private final Map<Integer, ButtonConfig> buttons = new TreeMap<>();
	private TableViewer viewer;

	public LicenseStausDialog(Shell shell, ExaminationCertificate certificate) {
		super(shell);
		this.origin = certificate;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite owner = owner(parent);
		restrictionsTable(owner);
		return owner;
	}

	@Override
	protected void buttonPressed(int id) {
		Optional.ofNullable(buttons.get(id)).ifPresent(button -> button.action().run());
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		initButtons();
		((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
		buttons.keySet().forEach(id -> createButton(parent, buttons.get(id)));
		createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, true);
	}

	private void createButton(Composite parent, ButtonConfig config) {
		Button button = createButton(parent, config.id(), config.name(), false);
		button.setToolTipText(config.tooltip());
		button.setImage(LicensingImages.getImage(config.image()));
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Licensing status"); //$NON-NLS-1$
		shell.setImage(getDefaultImage());
		shell.setSize(520, 300);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private Composite owner(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1, false));
		return composite;
	}

	private void restrictionsTable(Composite parent) {
		viewer = new LicensingTable(parent) //
				.withColumn("Name", 300) //$NON-NLS-1$
				.withColumn("Version", 100) //$NON-NLS-1$
				.withColumn("Verdict", 100) //$NON-NLS-1$
				.viewer();
		viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	private void initButtons() {
		new ButtonConfig(1, this::requestLicense, "&Request License...", "", LicensingImages.IMG_DEFAULT) //$NON-NLS-1$ //$NON-NLS-2$
				.reside(buttons);
		new ButtonConfig(2, this::importLicense, "&Import License...", "", LicensingImages.IMG_IMPORT)//$NON-NLS-1$ //$NON-NLS-2$
				.reside(buttons);
		new ButtonConfig(3, new CopyToClipboard(this::getShell, new ExaminationExplained(origin)), "Co&py", "", "")//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
	}

	private void requestLicense() {

	}

	private void importLicense() {

	}

}
