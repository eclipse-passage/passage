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

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.resource.ResourceSaveFailed;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;

/**
 * Creates new root licensing object. Can be asked for a reference to a created
 * instance.
 * 
 * @param <I> inner classifier to be created
 * @param <R> root classifier to store created if not present
 * 
 */
public final class InnerClassifierWizard<I, R> extends BaseClassifierWizard<InnerClassifierWizardPage<R>> {

	private final Class<I> clazz;
	private final MandatoryService context;
	private final SelectRequest<R> request;

	/**
	 * Creates a new wizard for root licensing object with given metadata,
	 * initializer and registry
	 * 
	 * @param metadata describes EMF metadata for an object to be created, must not
	 *                 be <code>null</code>
	 * @param registry
	 * @param registry registry for an object to be created, must not be
	 *                 <code>null</code>
	 * 
	 * @see BaseClassifierWizard
	 * @see ClassifierMetadata
	 * @see EditingDomainRegistry
	 * 
	 */
	public InnerClassifierWizard(Class<I> clazz, EntityMetadata metadata, EditingDomainRegistry<?> registry,
			SelectRequest<R> request, MandatoryService context) {
		super(metadata, registry);
		this.clazz = clazz;
		this.request = request;
		this.context = context;
	}

	@Override
	protected InnerClassifierWizardPage<R> createNewClassifierPage() {
		Optional<EntityMetadata> find = context.get(ComposableClassMetadata.class).find(clazz);
		if (find.isPresent()) {
			return new InnerClassifierWizardPage<R>(find.get(), request, context);
		}
		// FIXME: AF: provide nice error page
		throw new NoSuchElementException(clazz.getName());
	}

	@Override
	protected ServiceInvocationResult<Boolean> store() {
		return store(newClassifierPage.container(), newClassifierPage.candidate());
	}

	// FIXME: AF: rework to remove Optional
	protected ServiceInvocationResult<Boolean> store(Optional<R> container, EObject candidate) {
		if (!container.isPresent()) {
			return new BaseServiceInvocationResult<>(Boolean.FALSE);
		}
		EReference reference = containerEReference(candidate.eClass()).get();
		candidate.eSet(reference, container.get());
		Resource resource = candidate.eResource();
		if (resource == null) {
			return new BaseServiceInvocationResult<>(Boolean.FALSE);
		}
		try {
			resource.save(new HashMap<>());
			return new BaseServiceInvocationResult<>(Boolean.TRUE);
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(
					new Trouble(new ResourceSaveFailed(), WorkbenchMessages.InnerClassifierWizard_e_store, e));
		}
	}

	private Optional<EReference> containerEReference(EClass eClass) {
		return eClass.getEAllReferences().stream()//
				.filter(EReference::isContainer)//
				.findFirst();
	}

}
