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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

final class PreviewPage extends WizardPage implements PageObserver {

	private final LicenseStorage storage;
	private List plans;
	private Text period;
	private Text path;
	private final DataForExport data;

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
		content.setLayout(new GridLayout(1, false));
		plans = new List(content, SWT.BORDER | SWT.READ_ONLY);
		plans.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		path = new Text(content, SWT.BORDER | SWT.READ_ONLY);
		path.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));
		period = new Text(content, SWT.BORDER | SWT.READ_ONLY);
		period.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));
		setControl(content);
	}

	@Override
	public void update() {
		updateTargetPath();
		updatePlans();
		updatePeriod();
		// TODO: show and update `explain`
		getWizard().getContainer().updateButtons();
	}

	private void updateTargetPath() {
		path.setText(data.target().toString());
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
				data.from(), data.to())); // TODO: format
	}

	private String planInfo(LicensePlanDescriptor plan) {
		return NLS.bind("{0} ({1})", plan.getName(), plan.getIdentifier()); //$NON-NLS-1$
	}

}
