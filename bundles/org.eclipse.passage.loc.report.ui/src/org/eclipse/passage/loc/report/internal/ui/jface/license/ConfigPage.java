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

import java.util.Date;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.PageObserver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

final class ConfigPage extends WizardPage {

	private final PageObserver observer;
	private Button explain;

	protected ConfigPage(PageObserver observer) {
		super("scope"); //$NON-NLS-1$
		this.observer = observer;
		setTitle(ExportLicenseReportWizardMessages.ConfigPage_title);
		setMessage(ExportLicenseReportWizardMessages.ConfigPage_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(1, false));
		createPeriodSection(content);
		createAdditionalContentSection(content);
		setControl(content);
	}

	void installInitial() {
		updateControls();
	}

	Date from() {
		return null;// TODO:read from ui
	}

	Date to() {
		return null;// TODO:read from ui
	}

	boolean explain() {
		return explain.getSelection();
	}

	private void createPeriodSection(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(1, false));
		content.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	private void createAdditionalContentSection(Composite content) {
		Composite controls = new Composite(content, SWT.NONE);

		explain = new Button(controls, SWT.CHECK);

	}

	/**
	 * Update {@code Select All}, {@code Select None}, wizard's {@code Finish}
	 * buttons and {@code Preview} page
	 */
	private void updateControls() {
		observer.update();
	}

}
