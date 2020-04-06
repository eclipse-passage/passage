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
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * Retrieves localized name for a new {@link EObject}
 *
 */
public class EObjectDefaultIdentifier implements Supplier<String> {

	private final EObjectDefaultName name;

	public EObjectDefaultIdentifier(EClassResources resources) {
		Objects.requireNonNull(resources, "Null EClassResources"); //$NON-NLS-1$
		this.name = new EObjectDefaultName(resources);
	}

	public EObjectDefaultIdentifier(EClass eClass) {
		Objects.requireNonNull(eClass, "Null EClass"); //$NON-NLS-1$
		this.name = new EObjectDefaultName(eClass);
	}

	@Override
	public String get() {
		return name.get().replace(' ', '.').toLowerCase();
	}

}
