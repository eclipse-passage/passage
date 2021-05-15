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
package org.eclipse.passage.lic.internal.emf.migration;

import org.eclipse.emf.ecore.impl.EPackageImpl;

public final class DelegatingEPackage extends EPackageImpl {

	private final DelegatingEFactory factory = new DelegatingEFactory();

	DelegatingEPackage(String nsUri) {
		setNsURI(nsUri);
		setEFactoryInstance(factory);
	}

	public DelegatingEFactory getDelegatingEFactory() {
		return factory;
	}

}
