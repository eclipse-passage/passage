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
package org.eclipse.passage.loc.internal.workbench.wizards;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.internal.workbench.ClassifierMetadata;

/**
 * Creates new licensing object, either root of resource or not. Can be asked
 * for a reference to a created instance.
 * 
 * @param <N> a sub-type of {@link BaseClassifierWizardPage} to be used
 * @since 0.6
 *
 */
public abstract class BaseClassifierWizard<N extends BaseClassifierWizardPage> extends Wizard {

	protected final ClassifierMetadata metadata;
	protected final ClassifierInitializer initializer;
	protected final EditingDomainRegistry<?> registry;

	protected N newClassifierPage;

	/**
	 * Creates a new wizard with given metadata and initializer
	 * 
	 * @param metadata    describes EMF metadata for an object to be created, must
	 *                    not be <code>null</code>
	 * @param initializer describer initial values for an object to be created, must
	 *                    not be <code>null</code>
	 * @param registry    registry for an object to be created, must not be
	 *                    <code>null</code>
	 * 
	 * @see ClassifierMetadata
	 * @see ClassifierInitializer
	 * @see EditingDomainRegistry
	 * 
	 */
	protected BaseClassifierWizard(ClassifierMetadata metadata, ClassifierInitializer initializer,
			EditingDomainRegistry<?> registry) {
		this.metadata = metadata;
		this.initializer = initializer;
		this.registry = registry;
	}

	/**
	 * Creates an instance of wizard page to fulfill the field values for an object
	 * to be created, implementors expected to return just constructed page object,
	 * all the content initialization will be done later.
	 * 
	 * @param metadata    describes EMF metadata for an object to be created, must
	 *                    not be <code>null</code>
	 * @param initializer describer initial values for an object to be created, must
	 *                    not be <code>null</code>
	 * @return a just created instance of the {@link WizardPage}
	 */
	protected abstract N createNewClassifierPage();

	@Override
	public void addPages() {
		this.newClassifierPage = createNewClassifierPage();
		addPage(newClassifierPage);
	}

	/**
	 * An optional reference to a created instance, may be empty in case of errors
	 * during creation of because wizard was cancelled.
	 * 
	 * @return created {@link EObject} or {@link Optional#empty()}
	 */
	public Optional<EObject> created() {
		return newClassifierPage.candidate().eResource() != null ? Optional.of(newClassifierPage.candidate())
				: Optional.empty();
	}

	protected ResourceSet resourceSet() {
		if (registry instanceof IEditingDomainProvider) {
			IEditingDomainProvider edProvider = (IEditingDomainProvider) registry;
			return edProvider.getEditingDomain().getResourceSet();
		}
		return new ResourceSetImpl();
	}

}
