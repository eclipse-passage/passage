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

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.loc.internal.workbench.wizards.BaseClassifierWizard;
import org.eclipse.passage.loc.internal.workbench.wizards.InnerClassifierWizard;
import org.eclipse.passage.loc.internal.workbench.wizards.RootClassifierWizard;

/**
 * Creates the root classifier and corresponding resource for the given domain
 * with {@link RootClassifierWizard}. Will return either root object of created
 * resource or {@link Optional#empty()}
 * 
 * @since 0.6
 *
 * @param <I> classifier to be selected see {@link EClass#getName()}
 */
public final class CreateInner<I, R> extends CreateClassifier<I> {

	private final SelectRequest<R> request;

	/**
	 * Constructs the new instance with given context, domain and classifier.
	 * Actually either domain or classifier should be enough - to be fixed later.
	 * 
	 * @param context    the {@link IEclipseContext} to resolve services, must not
	 *                   be <code>null</code>
	 * @param domain     the licensing domain to create resource for, must not be
	 *                   <code>null</code>
	 * @param classifier the class of object to be created and stored in resource,
	 *                   must not be <code>null</code>
	 */
	public CreateInner(IEclipseContext context, String domain, Class<I> classifier, SelectRequest<R> request) {
		super(context, domain, classifier);
		this.request = request;
	}

	@Override
	protected BaseClassifierWizard<?> createWizard(ClassifierMetadata metadata, ClassifierInitializer initializer,
			EditingDomainRegistry<?> registry) {
		return new InnerClassifierWizard<R>(metadata, initializer, registry, request, context);
	}

}
