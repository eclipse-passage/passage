/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.workbench.emfforms;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.passage.loc.workbench.emfforms.wizards.CreateFormWizard;
import org.eclipse.swt.widgets.Shell;

public class LocWorkbenchEmfforms {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.workbench.emfforms"; //$NON-NLS-1$

	// FIXME: check if we still need this one
	public static void createDomainContentObject(IEclipseContext context, String domain, String perspectiveId) {
		EditingDomainRegistryAccess registryAccess = context.get(EditingDomainRegistryAccess.class);

		EditingDomainRegistry<?> registry = registryAccess.getDomainRegistry(domain);
		ClassifierInitializer initializer = registryAccess.getClassifierInitializer(domain);

		EClass eClass = registry.getContentClassifier();

		Wizard wizard = new CreateFormWizard(context, domain, perspectiveId);
		Shell shell = context.get(Shell.class);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.setTitle(initializer.newObjectTitle());
		dialog.setMessage(initializer.newFileMessage());

		Shell createdShell = dialog.getShell();
		createdShell.setText(initializer.newObjectMessage());
		createdShell.setImage(LicensingImages.getImage(eClass.getName()));

		int open = dialog.open();
		if (open == Window.OK) {
			LocWokbench.switchPerspective(context, perspectiveId);
		}
	}
}
