/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.workbench.emfforms.renderers;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.IDecoratingObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.loc.internal.workbench.emfforms.i18n.WorkbenchEmfformsMessages;
import org.eclipse.passage.loc.workbench.dialogs.FileContentDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class FileContentRenderer<O> extends TextWithButtonRenderer {

	private static final String IDENTIFIER_EMPTY = ""; //$NON-NLS-1$

	private final Class<O> observedClass;

	@Inject
	public FileContentRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider, Class<O> observed) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		this.observedClass = observed;
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		Control control = super.createSWTControl(parent);
		button.setText(WorkbenchEmfformsMessages.FileContentRenderer_button_text);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showFileContent();
			}
		});
		return control;
	}

	@Override
	protected Button createButton(Composite parent) {
		Button created = super.createButton(parent);
		created.setEnabled(true);
		return created;
	}

	@Override
	protected String getUnsetText() {
		return IDENTIFIER_EMPTY;
	}

	protected void showFileContent() {
		Shell shell = Display.getDefault().getActiveShell();
		String currentValue = getCurrentValue();
		String filePath = extractFilePath(currentValue);
		if (filePath == null) {
			String message = NLS.bind(WorkbenchEmfformsMessages.FileContentRenderer_e_content_title, currentValue);
			MessageDialog.openError(shell, WorkbenchEmfformsMessages.FileContentRenderer_e_content_message, message);
			return;
		}
		FileContentDialog dialog = new FileContentDialog(shell, filePath);
		dialog.open();
	}

	protected String extractFilePath(String currentValue) {
		try {
			IObservableValue<?> modelValue = getModelValue();
			if (modelValue instanceof IDecoratingObservable) {
				IDecoratingObservable decorating = (IDecoratingObservable) modelValue;
				IObservable decorated = decorating.getDecorated();
				if (decorated instanceof IObserving) {
					IObserving ipo = (IObserving) decorated;
					Object source = ipo.getObserved();
					if (observedClass.isInstance(source)) {
						return extractFilePath(currentValue, observedClass.cast(source));
					}
				}
			}
		} catch (DatabindingFailedException e) {
			getReportService().report(new DatabindingFailedReport(e));
		}
		return null;
	}

	protected abstract String extractFilePath(String currentValue, O observed);

}
