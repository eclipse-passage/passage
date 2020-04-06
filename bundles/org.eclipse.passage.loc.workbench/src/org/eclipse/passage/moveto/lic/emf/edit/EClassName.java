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
package org.eclipse.passage.moveto.lic.emf.edit;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.osgi.util.NLS;

/**
 * 
 * Retrieves localized name for a given {@link EClass}
 *
 */
public class EClassName implements Supplier<String> {

	private final EClassResources resources;

	public EClassName(EClassResources resources) {
		Objects.requireNonNull(resources, "Null EClassResources"); //$NON-NLS-1$
		this.resources = resources;
	}

	public EClassName(EClass eClass) {
		Objects.requireNonNull(eClass, "Null EClass"); //$NON-NLS-1$
		this.resources = new EClassResources(eClass);
	}

	@Override
	public String get() {
		return resources.get().getString(NLS.bind("_UI_{0}_type", resources.eClass().getName())); //$NON-NLS-1$
	}

}
