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
package org.eclipse.passage.lic.emf.edit;

import java.nio.file.Path;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.passage.lic.registry.DescriptorRegistry;
import org.eclipse.passage.lic.registry.Identified;

public interface EditingDomainRegistry extends DescriptorRegistry, IEditingDomainProvider, ComposedAdapterFactoryProvider {
	
	void registerContent(Identified content);

	void unregisterContent(String identifier);

	String getFileExtension();
	
	Path getBasePath();
	
	EClass getContentClassifier();

	EStructuralFeature getContentIdentifierAttribute();

	EStructuralFeature getContentNameAttribute();

}
