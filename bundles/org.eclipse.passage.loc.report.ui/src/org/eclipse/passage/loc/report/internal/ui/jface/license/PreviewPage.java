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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.license;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.loc.report.internal.core.license.LicenseStorage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.PageObserver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

final class PreviewPage extends WizardPage implements PageObserver {

	private final LicenseStorage storage;
	private List plans;
	private Label period;
	private Label path;
	private Button explained;
	private final DataForExport data;
	private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY"); //$NON-NLS-1$

	protected PreviewPage(LicenseStorage storage, DataForExport data) {
		super("preview"); //$NON-NLS-1$
		this.storage = storage;
		this.data = data;
		setTitle(ExportLicenseReportWizardMessages.PreviewPage_title);
		setMessage(ExportLicenseReportWizardMessages.PreviewPage_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
		plans = new List(content, SWT.BORDER | SWT.READ_ONLY);
		plans.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		path = new Label(content, SWT.NONE);
		path.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1));
		period = new Label(content, SWT.NONE);
		period.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1));
		new Label(content, SWT.NONE).setText(ExportLicenseReportWizardMessages.PreviewPage_explained);
		explained = new Button(content, SWT.CHECK);
		explained.setEnabled(false);
		setControl(content);
	}

	@Override
	public void update() {
		updateTargetPath();
		updatePlans();
		updatePeriod();
		updateExplained();
		getWizard().getContainer().updateButtons();
	}

	private void updateTargetPath() {
		path.setText(NLS.bind(ExportLicenseReportWizardMessages.PreviewPage_path, data.target().toString()));
	}

	private void updatePlans() {
		plans.removeAll();
		data.plans().stream() //
				.map(storage::plan) //
				.filter(Optional::isPresent) //
				.map(Optional::get)//
				.map(this::planInfo) //
				.forEach(plans::add);

	}

	private void updatePeriod() {
		period.setText(NLS.bind(ExportLicenseReportWizardMessages.PreviewPage_period, //
				format.format(data.from()), format.format(data.to())));
	}

	private String planInfo(LicensePlanDescriptor plan) {
		return NLS.bind("{0} ({1})", plan.getName(), plan.getIdentifier()); //$NON-NLS-1$
	}

	private void updateExplained() {
		explained.setSelection(data.explain());
	}

}
