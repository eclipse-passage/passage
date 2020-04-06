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
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.moveto.lic.internal.emf.edit.i18n.EmfEditMessages;

/**
 * 
 * Retrieves localized name for a new {@link EObject}
 *
 */
public class EObjectDefaultName implements Supplier<String> {

	private final EClassResources resources;

	public EObjectDefaultName(EClassResources resources) {
		Objects.requireNonNull(resources, "Null EClassResources"); //$NON-NLS-1$
		this.resources = resources;
	}

	public EObjectDefaultName(EClass eClass) {
		Objects.requireNonNull(eClass, "Null EClass"); //$NON-NLS-1$
		this.resources = new EClassResources(eClass);
	}

	@Override
	public String get() {
		return NLS.bind(EmfEditMessages.EObjectDefaultName_new, new EClassName(resources).get());
	}

}
