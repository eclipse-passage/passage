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
package org.eclipse.passage.lic.emf.ecore.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class DelegatingEPackage extends EPackageImpl {

	public static Diagnostic delegate(String nsUri, EPackage delegate, Iterable<String> classifiers) {
		EPackage eFactory = EPackage.Registry.INSTANCE.getEPackage(nsUri);
		DelegatingEPackage delegatingEPackage;
		if (eFactory instanceof DelegatingEPackage) {
			delegatingEPackage = (DelegatingEPackage) eFactory;
		} else {
			delegatingEPackage = new DelegatingEPackage(nsUri);
			EPackage.Registry.INSTANCE.put(nsUri, delegatingEPackage);
		}
		Map<EClass, EClass> delegated = new HashMap<>();
		for (String name : classifiers) {
			EClassifier eClassifier = delegate.getEClassifier(name);
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				EClass key = EcoreUtil.copy(eClass);
				delegated.put(key, eClass);
			} else {

			}
		}
		delegatingEPackage.getEClassifiers().addAll(delegated.keySet());
		DelegatingEFactory delegatingEFactory = delegatingEPackage.getDelegatingEFactory();
		delegatingEFactory.addEClassDelegate(delegate.getEFactoryInstance(), delegated);
		return Diagnostic.OK_INSTANCE;
	}

	private final DelegatingEFactory delegatingEFactory = new DelegatingEFactory();

	public DelegatingEPackage(String nsUri) {
		setNsURI(nsUri);
		setEFactoryInstance(delegatingEFactory);
	}

	public DelegatingEFactory getDelegatingEFactory() {
		return delegatingEFactory;
	}

}
