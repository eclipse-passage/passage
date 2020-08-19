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

import java.time.format.DateTimeFormatter;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("restriction")
public final class ImportLicenseDialog extends NotificationDialog {

	private Text path;
	private final DateTimeFormatter dates = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //$NON-NLS-1$

	public ImportLicenseDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Import License"); //$NON-NLS-1$
		shell.setImage(LicensingImages.getImage(LicensingImages.IMG_IMPORT));
		shell.setSize(850, 300);
	}

	@Override
	protected void buildUI(Composite parent) {
		buildSelector(parent);
		buildViewer(parent);
	}

	@Override
	protected void initButtons() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void inplaceData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateButtonsEnablement() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String defaultMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	private void buildSelector(Composite parent) {
		Composite composite = row(parent, 3);
		new Label(composite, SWT.NONE).setText("From license file:"); //$NON-NLS-1$
		path = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		path.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Button browse = new Button(composite, SWT.PUSH);
		browse.setText("B&rowse..."); //$NON-NLS-1$
		browse.addListener(SWT.Selection, this::loadLicense);
		setButtonLayoutData(browse);
	}

	private Composite row(Composite parent, int columns) {
		Composite row = new Composite(parent, SWT.NONE);
		row.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		row.setLayout(new GridLayout(columns, false));
		return row;
	}

	private void buildViewer(Composite parent) {
		viewer = new HereTable<Condition>(parent, Condition.class) //
				.withColumn("Feature", 300, this::feature) //$NON-NLS-1$
				.withColumn("Valid", 300, this::period) //$NON-NLS-1$
				.withColumn("Evaluation", 220, this::evaluation) //$NON-NLS-1$
				.viewer();
	}

	private String feature(Condition condition) {
		return String.format("%s version %s (%s)", //$NON-NLS-1$
				condition.feature(), //
				condition.versionMatch().version(), //
				condition.versionMatch().rule().identifier());
	}

	private String period(Condition condition) {
		ValidityPeriod period = condition.validityPeriod();
		if (!BaseValidityPeriodClosed.class.isInstance(period)) { // to be eliminated #566015
			return "unknown"; //$NON-NLS-1$
		}
		BaseValidityPeriodClosed closed = (BaseValidityPeriodClosed) condition.validityPeriod();
		return String.format("%s - %s", dates.format(closed.from()), dates.format(closed.to())); //$NON-NLS-1$
	}

	private String evaluation(Condition condition) {
		return String.format("%s (%s)", //$NON-NLS-1$
				condition.evaluationInstructions().expression(), //
				condition.evaluationInstructions().type().identifier());
	}

	private void loadLicense(Event e) {
// FIXME
	}

}
