/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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

import java.util.function.Supplier;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.osgi.util.NLS;

/**
 * 
 * Retrieves localized name for a given {@link EClass}
 *
 */
public class EClassName implements Supplier<String> {

	private final EClass eClass;

	public EClassName(EClass eClass) {
		this.eClass = eClass;
	}

	@Override
	public String get() {
		ResourceLocator resourceLocator = new LocateResources(eClass).get();
		return resourceLocator.getString(NLS.bind("_UI_{0}_type", eClass.getName())); //$NON-NLS-1$
	}

}
