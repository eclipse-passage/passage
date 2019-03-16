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
package org.eclipse.passage.lic.emf.ecore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface EditingDomainRegistry<I> {

	void registerSource(String source);

	void unregisterSource(String source);

	Iterable<String> getSources();

	Class<I> getContentClass();

	String resolveIdentifier(I content);

	void registerContent(I content);

	void unregisterContent(String identifier);

	String getFileExtension();

	EClass getContentClassifier();

	EStructuralFeature getContentIdentifierAttribute();

	EStructuralFeature getContentNameAttribute();

}
