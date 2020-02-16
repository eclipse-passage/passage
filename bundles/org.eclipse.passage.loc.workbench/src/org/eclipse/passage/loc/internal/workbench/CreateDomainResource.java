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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.workbench;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.workbench.wizards.CreateFileWizard;
import org.eclipse.swt.widgets.Shell;

/**
 * Creates the resource for the given domain with {@link CreateFileWizard}. Will
 * return either root object of created resource or {@link Optional#empty()}
 * 
 * @since 0.6
 *
 * @param <C> classifier to be selected see {@link EClass#getName()}
 */
public class CreateDomainResource<C> implements Supplier<Optional<C>> {

	private final IEclipseContext context;
	private final String domain;
	private final Class<C> classifierClass;

	/**
	 * Constructs the new instance with given context, domain and classifier.
	 * Actually either domain or classifier should be enough - to be fixed later.
	 * 
	 * @param context    the {@link IEclipseContext} to resolve services
	 * @param domain     the licensing domain to create resource for
	 * @param classifier the class of object to be created and stored in resource
	 */
	public CreateDomainResource(IEclipseContext context, String domain, Class<C> classifier) {
		this.context = context;
		this.domain = domain;
		this.classifierClass = classifier;
	}

	@Override
	public Optional<C> get() {
		EditingDomainRegistryAccess registryAccess = context.get(EditingDomainRegistryAccess.class);
		ClassifierInitializer initializer = registryAccess.getClassifierInitializer(domain);
		EClass eClass = registryAccess.getDomainRegistry(domain).getContentClassifier();
		CreateFileWizard wizard = showWizard(eClass, initializer);
		return wizard.created().filter(classifierClass::isInstance).flatMap(e -> Optional.of(classifierClass.cast(e)));
	}

	private CreateFileWizard showWizard(EClass eClass, ClassifierInitializer initializer) {
		CreateFileWizard wizard = new CreateFileWizard(context, domain);
		WizardDialog dialog = new WizardDialog(context.get(Shell.class), wizard);
		dialog.create();
		dialog.setTitle(initializer.newObjectTitle());
		dialog.setMessage(initializer.newFileMessage());
		Shell createdShell = dialog.getShell();
		createdShell.setText(initializer.newObjectMessage());
		createdShell.setImage(LicensingImages.getImage(eClass.getName()));
		dialog.open();
		return wizard;
	}

}
