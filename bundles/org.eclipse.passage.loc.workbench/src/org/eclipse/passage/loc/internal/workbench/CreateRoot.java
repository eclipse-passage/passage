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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Optional;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.internal.workbench.wizards.BaseClassifierWizard;
import org.eclipse.passage.loc.internal.workbench.wizards.RootClassifierWizard;

/**
 * Creates the root classifier and corresponding resource for the given domain
 * with {@link RootClassifierWizard}. Will return either root object of created
 * resource or {@link Optional#empty()}
 * 
 * @param <R> root classifier to be created
 */
public final class CreateRoot<R> extends CreateClassifier<R> {

	/**
	 * Constructs the new instance with given context, domain and classifier.
	 * Actually either domain or classifier should be enough - to be fixed later.
	 * 
	 * @param context    the {@link MandatoryService} to resolve services, must not
	 *                   be <code>null</code>
	 * @param domain     the licensing domain to create resource for, must not be
	 *                   <code>null</code>
	 * @param classifier the class of object to be created and stored in resource,
	 *                   must not be <code>null</code>
	 */
	public CreateRoot(MandatoryService context, String domain, Class<R> classifier) {
		super(context, domain, classifier);
	}

	@Override
	protected BaseClassifierWizard<?> createWizard(Class<R> clazz, EntityMetadata metadata,
			EditingDomainRegistry<?> registry) {
		return new RootClassifierWizard(metadata, registry);
	}

	@Override
	protected String dialogMessage(String typeName) {
		return NLS.bind(WorkbenchMessages.CreateRoot_message_new_type, typeName);
	}

}
