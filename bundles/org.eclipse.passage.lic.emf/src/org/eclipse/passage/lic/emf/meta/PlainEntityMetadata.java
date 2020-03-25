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
package org.eclipse.passage.lic.emf.meta;

import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;

/**
 * Encapsulates the ECore metadata for the object to reduce the number of
 * arguments in methods
 * 
 * @since 0.6
 *
 */
public final class PlainEntityMetadata implements EntityMetadata {

	private final EClass eClass;
	private final EStructuralFeature identification;
	private final EStructuralFeature naming;

	/**
	 * Creates a plain entity metadata descriptor with the given
	 * non-<code>null</code> type, identification and naming
	 * 
	 * @param type type for the object of interest, should not be <code>null</code>
	 * @param id   identification feature for the object of interest, must not be
	 *             <code>null</code>
	 * @param name naming feature for the object of interest, must not be
	 *             <code>null</code>
	 * 
	 */
	public PlainEntityMetadata(EClass type, EStructuralFeature id, EStructuralFeature name) {
		Objects.requireNonNull(type, EmfMessages.PlainEntityMetadata_e_null_eclass);
		Objects.requireNonNull(id, EmfMessages.PlainEntityMetadata_e_null_identification);
		Objects.requireNonNull(name, EmfMessages.PlainEntityMetadata_e_null_naming);
		this.eClass = type;
		this.identification = id;
		this.naming = name;
	}

	@Override
	public EClass eClass() {
		return eClass;
	}

	@Override
	public EStructuralFeature identification() {
		return identification;
	}

	@Override
	public EStructuralFeature naming() {
		return naming;
	}

}
