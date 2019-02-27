/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.workbench.emfforms.wizards;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.loc.workbench.wizards.CreateFileWizard;
import org.eclipse.passage.loc.workbench.wizards.CreateFileWizardPage;

public class CreateFormWizard extends CreateFileWizard {

	public CreateFormWizard(IEclipseContext context, String domain, String perspectiveId) {
		super(context, domain, perspectiveId);
	}

	@Override
	protected CreateFileWizardPage createFilePage(EditingDomainRegistry registry) {
		return new CreateFormWizardPage(CreateFileWizardPage.class.getName(), registry.getFileExtension(),
				eObject, initializer, identifierFeature != null, nameFeature != null);
	}
}
