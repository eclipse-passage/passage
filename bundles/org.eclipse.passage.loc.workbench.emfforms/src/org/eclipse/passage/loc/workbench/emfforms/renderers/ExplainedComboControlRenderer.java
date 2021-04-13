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
package org.eclipse.passage.loc.workbench.emfforms.renderers;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class ExplainedComboControlRenderer extends WithComboRenderer {

	protected StyledText description;

	protected ExplainedComboControlRenderer(VControl element, ViewModelContext context, ReportService report,
			EMFFormsDatabinding databinding, EMFFormsLabelProvider labels, VTViewTemplateProvider templates) {
		super(element, context, report, databinding, labels, templates);
	}

	@Override
	protected final Control createSWTControl(Composite parent) {
		Composite owner = owner(parent);
		createCombo(owner);
		createDescription(owner);
		bindDescriptioWithCombo();
		layoutPieces();
		return owner;
	}

	private void layoutPieces() {
		GridDataFactory.fillDefaults()//
				.align(SWT.FILL, SWT.FILL)//
				.grab(false, false)//
				.applyTo(combo);
		GridDataFactory.fillDefaults()//
				.align(SWT.FILL, SWT.FILL)//
				.grab(true, false)//
				.applyTo(description);
	}

	private void bindDescriptioWithCombo() {
		combo.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> describe(combo.getSelectionIndex())));
	}

	private void createDescription(Composite owner) {
		description = new StyledText(owner, SWT.READ_ONLY);
		description.setBackground(owner.getBackground());
		describeUnset();
	}

	private Composite owner(Composite parent) {
		Composite owner = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 0;
		owner.setLayout(layout);
		owner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		return owner;
	}

	protected abstract void describeUnset();

	protected abstract void describe(int index);

}
