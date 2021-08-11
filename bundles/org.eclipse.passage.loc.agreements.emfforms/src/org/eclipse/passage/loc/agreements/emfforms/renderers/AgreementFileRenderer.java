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

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.loc.internal.api.workspace.Agreements;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.workbench.emfforms.renderers.TextWithButtonRenderer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

@SuppressWarnings("restriction")
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
//TODO: expose error to user
		}
	}

	private String reside(File file) throws Exception {
		Optional<String> defined = definedName();
		String name = defined.orElse(file.getName());
		agreements().located(name).write(Files.readAllBytes(file.toPath()));
		return name;
	}

	private void reflect(String name) {
		if (definedName().orElse("").equals(name)) { //$NON-NLS-1$
			return;
		}
		text.setText(name);
		// TODO affect not only 'text' field, but also mime type
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

	private Agreements agreements() throws LicensingException {
		Optional<Agreements> service = new OperatorGearAware()
				.withGear(gear -> Optional.of(gear.workspace().agreements()));
		if (!service.isPresent()) {
			throw new LicensingException("There is no Agreements service supplied by Operator Workspace"); //$NON-NLS-1$
		}
		return service.get();
	}

}
