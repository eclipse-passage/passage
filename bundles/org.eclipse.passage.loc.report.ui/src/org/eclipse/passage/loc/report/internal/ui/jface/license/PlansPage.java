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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.PageObserver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

final class PlansPage extends WizardPage {

	private final LicensePlanDescriptor[] plans;
	private final Set<LicensePlanDescriptor> selected;
	private final PageObserver preview;
	private Button all;
	private Button none;
	private CheckboxTableViewer viewer;

	protected PlansPage(LicensePlans plans, PreviewPage preview) {
		super("scope"); //$NON-NLS-1$
		this.preview = preview;
		this.plans = plans.get();
		this.selected = new HashSet<>();
		setTitle(ExportLicenseReportWizardMessages.PlansPage_title);
		setMessage(ExportLicenseReportWizardMessages.PlansPage_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
		createViewer(content);
		createButtons(content);
		setControl(content);
	}

	void installInitial() {
		selected.addAll(Arrays.asList(this.plans));
		viewer.refresh();
		updateControls();
	}

	Set<String> identifiers() {
		return selected.stream()//
				.map(LicensePlanDescriptor::getIdentifier) //
				.collect(Collectors.toSet());
	}

	private CheckboxTableViewer createViewer(Composite content) {
		viewer = CheckboxTableViewer.newCheckList(content,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		Table table = viewer.getTable();
		table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		createColumns();
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setCheckStateProvider(new ICheckStateProvider() {
			@Override
			public boolean isGrayed(Object element) {
				return false;
			}

			@Override
			public boolean isChecked(Object element) {
				return selected.contains(element);
			}
		});

		viewer.setInput(plans);
		return viewer;
	}

	private void createButtons(Composite content) {
		Composite controls = new Composite(content, SWT.NONE);
		controls.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		controls.setLayout(new FillLayout(SWT.VERTICAL));
		all = new Button(controls, SWT.PUSH);
		all.setText(ExportWizardMessages.ScopePage_selectAll);
		none = new Button(controls, SWT.PUSH);
		none.setText(ExportWizardMessages.ScopePage_selctNone);
		all.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
			Arrays.stream(plans).forEach(selected::add);
			viewer.refresh();
			updateControls();
		}));
		none.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
			selected.clear();
			viewer.refresh();
			updateControls();
		}));
		viewer.addCheckStateListener(e -> {
			if (e.getChecked()) {
				selected.add((LicensePlanDescriptor) e.getElement());
			} else {
				selected.remove(e.getElement());
			}
			updateControls();
		});
	}

	/**
	 * Update {@code Select All}, {@code Select None}, wizard's {@code Finish}
	 * buttons and {@code Preview} page
	 */
	private void updateControls() {
		updateLocalControls();
		preview.update();
	}

	private void updateLocalControls() {
		all.setEnabled(plans.length > 0 && selected.size() < plans.length);
		none.setEnabled(!selected.isEmpty());
	}

	private void createColumns() {
		TableViewerColumn id = new TableViewerColumn(viewer, SWT.NONE);
		id.getColumn().setWidth(250);
		id.getColumn().setText(ExportLicenseReportWizardMessages.PlansPage_columnId);
		id.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((LicensePlanDescriptor) element).getIdentifier();
			}
		});
		TableViewerColumn name = new TableViewerColumn(viewer, SWT.NONE);
		name.getColumn().setWidth(250);
		name.getColumn().setText(ExportLicenseReportWizardMessages.PlansPage_columnName);
		name.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((LicensePlanDescriptor) element).getName();
			}
		});
	}

}
