/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.workbench.emfforms.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.spi.model.VViewFactory;
import org.eclipse.emf.ecp.view.spi.model.VViewModelProperties;
import org.eclipse.emfforms.swt.core.EMFFormsSWTConstants;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.workbench.wizards.CreateFileWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class CreateFormWizardPage extends CreateFileWizardPage {

	public CreateFormWizardPage(String pageName, String extension, EObject object, ClassifierInitializer initializer,
			boolean createId, boolean createName) {
		super(pageName, object, extension, initializer, createId, createName);
	}

	@Override
	protected void createFileControls(Composite composite) {
		super.createFileControls(composite);
		createOtherControls(composite);
	}

	protected void createOtherControls(Composite composite) {
		Composite base = new Composite(composite, SWT.NONE);

		GridLayout layout = new GridLayout(1, false);
		base.setLayout(layout);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		base.setLayoutData(data);

		final VViewModelProperties properties = VViewFactory.eINSTANCE.createViewModelLoadingProperties();
		properties.addInheritableProperty(EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_KEY,
				EMFFormsSWTConstants.USE_ON_MODIFY_DATABINDING_VALUE);
		try {
			ECPSWTViewRenderer.INSTANCE.render(base, eObject, properties);
		} catch (ECPRendererException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
