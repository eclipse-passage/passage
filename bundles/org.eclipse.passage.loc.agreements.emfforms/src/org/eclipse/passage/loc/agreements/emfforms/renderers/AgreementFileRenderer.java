/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.agreements.emfforms.renderers;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.IDecoratingObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.swt.reporting.RenderingFailedReport;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.agreements.model.api.Agreement;
import org.eclipse.passage.loc.internal.equinox.AgreementsService;
import org.eclipse.passage.loc.workbench.emfforms.renderers.TextWithButtonRenderer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

@SuppressWarnings("restriction")
/**
 * TODO: (1) validity: Workspace.Agreements must report existence for content
 * file denoted by [file] attribute (2) rename: there should be a way to rename
 * content file
 */
public final class AgreementFileRenderer extends TextWithButtonRenderer {

	@Inject
	public AgreementFileRenderer(VControl element, ViewModelContext context, ReportService report,
			EMFFormsDatabinding databinding, EMFFormsLabelProvider labeling, VTViewTemplateProvider template) {
		super(element, context, report, databinding, labeling, template);
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		Control control = super.createSWTControl(parent);
		text.setEditable(false);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				locateAgreementContentFile();
			}
		});
		button.setText("Agreement Content File..."); //$NON-NLS-1$
		return control;
	}

	@Override
	protected String getUnsetText() {
		return ""; //$NON-NLS-1$
	}

	protected void locateAgreementContentFile() {
		Optional<File> file = new LocatedAgreementFile().get();
		if (file.isEmpty()) {
			return;
		}
		try {
			reflect(reside(file.get()));
		} catch (Exception e) {
			getReportService().report(new RenderingFailedReport(e));
		}
	}

	private String reside(File file) throws Exception {
		// rename in already defined name if any:
		// String name = definedName().orElse(file.getName());
		String name = file.getName();
		new AgreementsService().get().located(name).write(Files.readAllBytes(file.toPath()));
		return name;
	}

	private void reflect(String name) {
		if (definedName().orElse("").equals(name)) { //$NON-NLS-1$
			return;
		}
		reflectFileName(name);
		reflectMimeType(name);
	}

	private void reflectFileName(String name) {
		text.setText(name);
	}

	private void reflectMimeType(String name) {
		Optional<AgreementFormat> format = new AgreementFormat.Supported().forFile(name);
		if (format.isEmpty()) {
			return; // cannot assist for not supported file types
		}
		agreement().ifPresent(agreement -> agreement.setMime(format.get().mime()));
	}

	private Optional<String> definedName() {
		try {
			Object value = getModelValue().getValue();
			if (!String.class.isInstance(value)) {
				return Optional.empty();
			}
			String name = (String) value;
			return Optional.of(name);
		} catch (DatabindingFailedException e) {
			return Optional.empty();
		}
	}

	private Optional<Agreement> agreement() {
		try {
			IObservableValue<?> value = getModelValue();
			if (!IDecoratingObservable.class.isInstance(value)) {
				return Optional.empty();
			}
			IObservable decorated = ((IDecoratingObservable) value).getDecorated();
			if (!IObserving.class.isInstance(decorated)) {
				return Optional.empty();
			}
			Object source = ((IObserving) decorated).getObserved();
			if (!Agreement.class.isInstance(source)) {
				return Optional.empty();
			}
			return Optional.of((Agreement) source);
		} catch (DatabindingFailedException e) {
			getReportService().report(new DatabindingFailedReport(e));
			return Optional.empty();
		}
	}
}
