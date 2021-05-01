/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 2.0
 */
public interface EntityMetadata {

	/**
	 * 
	 * @return the {@link EClass} for the object of interest
	 */
	EClass eClass();

	/**
	 * The {@link EStructuralFeature} to be used as identifier
	 * 
	 * @return non-<code>null</code> identification feature
	 */
	EStructuralFeature identification();

	/**
	 * The {@link EStructuralFeature} to be used as name
	 * 
	 * @return non-<code>null</code> naming feature
	 */
	EStructuralFeature naming();

}
