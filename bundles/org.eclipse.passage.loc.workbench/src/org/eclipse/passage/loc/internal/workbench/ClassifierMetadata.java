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

import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;

/**
 * Encapsulates the ECore metadata for the object to reduce the number of
 * arguments in methods
 * 
 * @since 0.6
 *
 */
public class ClassifierMetadata {

	private final EClass eClass;
	private final EStructuralFeature identification;
	private final EStructuralFeature naming;

	/**
	 * Creates the classifier metadata descriptor with the given
	 * non-<code>null</code> type, identification and naming
	 * 
	 * @param type type for the object of interest, should not be <code>null</code>
	 * @param id   identification feature for the object of interest, must not be
	 *             <code>null</code>
	 * @param name naming feature for the object of interest, must not be
	 *             <code>null</code>
	 * 
	 */
	public ClassifierMetadata(EClass type, EStructuralFeature id, EStructuralFeature name) {
		Objects.requireNonNull(type, WorkbenchMessages.ClassifierMetadata_e_null_eclass);
		Objects.requireNonNull(id, WorkbenchMessages.ClassifierMetadata_e_null_identification);
		Objects.requireNonNull(name, WorkbenchMessages.ClassifierMetadata_e_null_naming);
		this.eClass = type;
		this.identification = id;
		this.naming = name;
	}

	/**
	 * 
	 * @return the {@link EClass} for the object of interest
	 */
	public EClass eClass() {
		return eClass;
	}

	/**
	 * The {@link EStructuralFeature} to be used as identifier
	 * 
	 * @return non-<code>null</code> identification feature
	 */
	public EStructuralFeature identification() {
		return identification;
	}

	/**
	 * The {@link EStructuralFeature} to be used as name
	 * 
	 * @return non-<code>null</code> naming feature
	 */
	public EStructuralFeature naming() {
		return naming;
	}

}
