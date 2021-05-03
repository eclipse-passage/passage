/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.emf.migration;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @since 2.0
 */
public final class DelegateClassifiers {

	private final String uri;

	public DelegateClassifiers(String uri) {
		this.uri = uri;
	}

	public void delegate(EPackage delegate, Iterable<String> classifierNames) {
		DelegatingEPackage delegating = new DelegateEPackage().apply(uri);
		Map<EClass, EClass> delegated = new HashMap<>();
		for (String name : classifierNames) {
			EClassifier eClassifier = delegate.getEClassifier(name);
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				EClass key = EcoreUtil.copy(eClass);
				delegated.put(key, eClass);
			}
		}
		delegating.getEClassifiers().addAll(delegated.keySet());
		delegating.getDelegatingEFactory().addEClassDelegate(delegate.getEFactoryInstance(), delegated);
	}

}
