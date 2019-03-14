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
package org.eclipse.passage.loc.workbench.wizards;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class CreateFileWizard extends Wizard {

	protected final EObject eObject;
	protected final ClassifierInitializer initializer;
	protected final EStructuralFeature identifierFeature;
	protected final EStructuralFeature nameFeature;

	private final IEclipseContext eclipseContext;
	private final EditingDomainRegistry<?> domainRegistry;
	private final String perspectiveId;

	private CreateFileWizardPage filePage;

	public CreateFileWizard(IEclipseContext context, String domain, String perspectiveId) {
		this.eclipseContext = context;
		this.perspectiveId = perspectiveId;
		DomainRegistryAccess registryAccess = context.get(DomainRegistryAccess.class);
		EditingDomainRegistry<?> registry = registryAccess.getDomainRegistry(domain);
		EClass eClass = registry.getContentClassifier();
		this.domainRegistry = registry;
		this.eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		this.identifierFeature = registry.getContentIdentifierAttribute();
		this.nameFeature = registry.getContentNameAttribute();
		this.initializer = registryAccess.getClassifierInitializer(domain);
	}

	@Override
	public void addPages() {
		filePage = createFilePage(domainRegistry);
		addPage(filePage);
	}

	protected CreateFileWizardPage createFilePage(EditingDomainRegistry<?> registry) {
		return new CreateFileWizardPage(CreateFileWizardPage.class.getName(), eObject, registry.getFileExtension(),
				initializer, identifierFeature != null, nameFeature != null);
	}

	@Override
	public boolean performFinish() {
		try {
			final URI fileURI = filePage.getFileURI();
			File file = new File(fileURI.toFileString());
			if (file.exists()) {
				String absolutePath = file.getAbsolutePath();
				String message = String.format(
						"The file \"%s\" already exists.  Do you want to replace the existing file?", absolutePath);
				if (!MessageDialog.openQuestion(getShell(), "Question", message)) {
					filePage.selectFileField();
					return false;
				}
			}
			if (identifierFeature != null) {
				eObject.eSet(identifierFeature, filePage.getIdentifier());
			}

			if (nameFeature != null) {
				eObject.eSet(nameFeature, filePage.getName());
			}

			IRunnableWithProgress operation = new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor progressMonitor) {
					ResourceSet resourceSet = new ResourceSetImpl();
					Resource resource = resourceSet.createResource(fileURI);
					resource.getContents().add(eObject);
					LocWokbench.save(resource);
					LocWokbench.switchPerspective(eclipseContext, perspectiveId);
					domainRegistry.registerSource(fileURI.toFileString());
				}
			};

			getContainer().run(false, false, operation);
			return true;
		} catch (Exception exception) {
			// FIXME:
			exception.printStackTrace();
			return false;
		}
	}

}
