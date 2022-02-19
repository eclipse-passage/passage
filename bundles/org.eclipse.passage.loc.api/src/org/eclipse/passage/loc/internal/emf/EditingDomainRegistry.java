/*******************************************************************************
 * Copyright (c) 2018, 2022 ArSysOp
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
package org.eclipse.passage.loc.internal.emf;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.passage.lic.api.ServiceInvocationResult;

public interface EditingDomainRegistry<I> {

	ServiceInvocationResult<Boolean> registerSource(URI uri);

	void unregisterSource(URI uri);

	List<URI> getSources();

	Class<I> getContentClass();

	String resolveIdentifier(I content);

	void registerContent(I content);

	void unregisterContent(String identifier);

	String getFileExtension();

	// TODO: is not used
	EClass getContentClassifier();

	EStructuralFeature getContentIdentifierAttribute();

	EStructuralFeature getContentNameAttribute();

}
