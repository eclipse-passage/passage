/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.emf.ecore.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class DelegatingEPackage extends EPackageImpl {

	public static Diagnostic delegate(String nsUri, EPackage delegate, Iterable<String> classifierNames) {
		EPackage existing = EPackage.Registry.INSTANCE.getEPackage(nsUri);
		DelegatingEPackage delegatingEPackage;
		if (existing instanceof DelegatingEPackage) {
			delegatingEPackage = (DelegatingEPackage) existing;
		} else {
			delegatingEPackage = new DelegatingEPackage(nsUri);
			if (existing != null) {
				// FIXME: AF: ensure that delegation occurs only once
				Map<EClass, EClass> wrapped = new HashMap<>();
				EList<EClassifier> classifiers = existing.getEClassifiers();
				for (EClassifier eClassifier : classifiers) {
					if (eClassifier instanceof EClass) {
						EClass eClass = (EClass) eClassifier;
						EClass key = EcoreUtil.copy(eClass);
						wrapped.put(key, eClass);
					}
				}
				delegatingEPackage.getEClassifiers().addAll(wrapped.keySet());
				DelegatingEFactory delegatingEFactory = delegatingEPackage.getDelegatingEFactory();
				delegatingEFactory.addEClassDelegate(existing.getEFactoryInstance(), wrapped);
			}
			EPackage.Registry.INSTANCE.put(nsUri, delegatingEPackage);
		}
		Map<EClass, EClass> delegated = new HashMap<>();
		for (String name : classifierNames) {
			EClassifier eClassifier = delegate.getEClassifier(name);
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				EClass key = EcoreUtil.copy(eClass);
				delegated.put(key, eClass);
			}
		}
		delegatingEPackage.getEClassifiers().addAll(delegated.keySet());
		DelegatingEFactory delegatingEFactory = delegatingEPackage.getDelegatingEFactory();
		delegatingEFactory.addEClassDelegate(delegate.getEFactoryInstance(), delegated);
		return Diagnostic.OK_INSTANCE;
	}

	private final DelegatingEFactory delegatingEFactory = new DelegatingEFactory();

	private DelegatingEPackage(String nsUri) {
		setNsURI(nsUri);
		setEFactoryInstance(delegatingEFactory);
	}

	public DelegatingEFactory getDelegatingEFactory() {
		return delegatingEFactory;
	}

}
