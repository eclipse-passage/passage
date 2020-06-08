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

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.PageObserver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

final class ConfigPage extends WizardPage {

	private final PageObserver observer;
	private Button explain;
	private DateTime from;
	private DateTime to;

	protected ConfigPage(PageObserver observer) {
		super("scope"); //$NON-NLS-1$
		this.observer = observer;
		setTitle(ExportLicenseReportWizardMessages.ConfigPage_title);
		installPageDescription();
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
		installDateToControl(from, initialFrom());
		installDateToControl(to, initialTo());
		explain.setSelection(initialExplain());
		updateControls();
	}

	boolean explain() {
		return explain.getSelection();
	}

	Date from() {
		return date(from, this::initialFrom);
	}

	Date to() {
		return date(to, this::initialTo);
	}

	private Date date(DateTime control, Supplier<LocalDate> initial) {
		LocalDate source = Optional.ofNullable(control).isPresent() //
				? LocalDate.of(control.getYear(), control.getMonth() + 1, control.getDay())//
				: initial.get();
		return Date.from(source//
				.atStartOfDay()//
				.atZone(ZoneId.systemDefault())//
				.toInstant());
	}

	private LocalDate initialFrom() {
		return LocalDate.now().minus(6, ChronoUnit.MONTHS);
	}

	private LocalDate initialTo() {
		return LocalDate.now().minus(1, ChronoUnit.DAYS);
	}

	private boolean initialExplain() {
		return false;
	}

	private void installDateToControl(DateTime control, LocalDate date) {
		control.setYear(date.getYear());
		control.setMonth(date.getMonth().getValue() - 1);
		control.setDay(date.getDayOfMonth());
	}

	private void createPeriodSection(Composite parent) {
		Composite content = row(parent, 4);
		new Label(content, SWT.NONE).setText(ExportLicenseReportWizardMessages.ConfigPage_dateFrom_title);
		from = new DateTime(content, SWT.DROP_DOWN);
		SelectionListener listener = validateAndUpdate();
		from.addSelectionListener(listener);
		new Label(content, SWT.NONE).setText(ExportLicenseReportWizardMessages.ConfigPage_dateTo_title);
		to = new DateTime(content, SWT.DROP_DOWN);
		to.addSelectionListener(listener);
	}

	private Composite row(Composite parent, int columns) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(columns, false));
		content.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		return content;
	}

	private void createAdditionalContentSection(Composite parent) {
		Composite content = row(parent, 2);
		Label label = new Label(content, SWT.WRAP);
		label.setText(ExportLicenseReportWizardMessages.ConfigPage_explain_title);
		label.setToolTipText(ExportLicenseReportWizardMessages.ConfigPage_explain_tooltip);
		explain = new Button(content, SWT.CHECK);
		explain.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> updateControls()));
	}

	private void updateControls() {
		observer.update();
	}

	private void validate() {
		if (!from().before(to())) {
			setMessage(ExportLicenseReportWizardMessages.ConfigPage_invalidDates, IMessageProvider.ERROR);
		} else {
			installPageDescription();
		}
	}

	private SelectionListener validateAndUpdate() {
		return SelectionListener.widgetSelectedAdapter(e -> {
			validate();
			updateControls();
		});
	}

	private void installPageDescription() {
		setMessage(ExportLicenseReportWizardMessages.ConfigPage_description, IMessageProvider.NONE);
	}

}
