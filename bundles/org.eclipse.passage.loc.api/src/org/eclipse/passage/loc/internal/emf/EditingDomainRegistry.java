/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.passage.lic.api.ServiceInvocationResult;

public interface EditingDomainRegistry<I> {

	ServiceInvocationResult<Boolean> registerSource(String source);

	void unregisterSource(String source);

	Iterable<String> getSources();

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
