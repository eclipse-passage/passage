/*******************************************************************************
 * Copyright (c) 2021, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation, further support
 *******************************************************************************/
package org.eclipse.passage.lic.emf.migration;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.passage.lic.internal.emf.migration.DelegateEPackage;
import org.eclipse.passage.lic.internal.emf.migration.DelegatingEFactory;
import org.eclipse.passage.lic.internal.emf.migration.DelegatingEPackage;

/**
 * @since 2.0
 */
public final class DelegateClassifiers {

	private final String uri;

	public DelegateClassifiers(String uri) {
		this.uri = uri;
	}

	public void delegate(EClassRoutes routes) {
		synchronized (DelegateClassifiers.class) {
			DelegatingEPackage delegating = new DelegateEPackage().apply(uri);
			DelegatingEFactory factory = delegating.getDelegatingEFactory();
			for (Entry<String, EClass> entry : routes.defined().entrySet()) {
				EClass to = entry.getValue();
				EClass from = EcoreUtil.copy(to);
				from.setName(entry.getKey());
				delegating.getEClassifiers().add(from);
				factory.delegateEClass(to.getEPackage().getEFactoryInstance(), from, to);
			}
		}
	}

}
