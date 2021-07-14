/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Creates new licensing object, either root of resource or not. Can be asked
 * for a reference to a created instance.
 * 
 * @param <N> a sub-type of {@link BaseClassifierWizardPage} to be used
 *
 */
public abstract class BaseClassifierWizard<N extends BaseClassifierWizardPage> extends Wizard {

	protected final EntityMetadata metadata;
	protected final EditingDomainRegistry<?> registry;

	protected N newClassifierPage;

	/**
	 * Creates a new wizard with given metadata and initializer
	 * 
	 * @param metadata describes EMF metadata for an object to be created, must not
	 *                 be <code>null</code>
	 * @param registry registry for an object to be created, must not be
	 *                 <code>null</code>
	 * @see EntityMetadata
	 * @see EditingDomainRegistry
	 * 
	 */
	protected BaseClassifierWizard(EntityMetadata metadata, EditingDomainRegistry<?> registry) {
		this.metadata = metadata;
		this.registry = registry;
	}

	/**
	 * Creates an instance of wizard page to fulfill the field values for an object
	 * to be created, implementors expected to return just constructed page object,
	 * all the content initialization will be done later.
	 * 
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

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(false, false, m -> store());
			return true;
		} catch (InvocationTargetException exception) {
			failed(exception.getTargetException());
			return false;
		} catch (InterruptedException exception) {
			cancelled();
			return false;
		}
	}

	protected abstract ServiceInvocationResult<Boolean> store();

	protected void failed(Throwable target) {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		IStatus status = new Status(IStatus.ERROR, bundle.getSymbolicName(), //
				Optional.ofNullable(target).map(t -> t.getMessage())//
						.orElse(WorkbenchMessages.BaseClassifierWizard_message_e_create),
				target);
		Platform.getLog(getClass()).log(status);
		ErrorDialog.openError(getShell(), WorkbenchMessages.BaseClassifierWizard_title_e_create,
				WorkbenchMessages.BaseClassifierWizard_message_e_create, status);
	}

	protected void cancelled() {
		MessageDialog.openError(getShell(), WorkbenchMessages.BaseClassifierWizard_title_e_create,
				WorkbenchMessages.BaseClassifierWizard_message_e_create);
	}

}
