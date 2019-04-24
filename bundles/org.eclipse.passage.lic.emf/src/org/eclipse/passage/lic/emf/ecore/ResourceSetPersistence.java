/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.emf.ecore;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.passage.lic.runtime.LicensingResult;

/**
 * The service to (re)store {@link ResourceSet} {@link URI}(s)
 * 
 * @since 0.5.0
 *
 */
public interface ResourceSetPersistence {

	/**
	 * Reads the {@link ResourceSet} state from the given location
	 * 
	 * @param resourceSet the {@link ResourceSet} that needs to be restored
	 * @param location    the location to restore the state from
	 * @return the result of the operation
	 */
	LicensingResult readResourceSet(ResourceSet resourceSet, String location);

	/**
	 * Writes the {@link ResourceSet} state to the given location
	 * 
	 * @param resourceSet the {@link ResourceSet} that needs to be stored
	 * @param location    the location to store the state to
	 * @return the result of the operation
	 */
	LicensingResult writeResourceSet(ResourceSet resourceSet, String location);

}
