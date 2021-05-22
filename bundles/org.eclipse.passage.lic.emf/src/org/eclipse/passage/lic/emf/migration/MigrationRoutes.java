/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.emf.migration;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;

/**
 * @since 2.0
 */
public interface MigrationRoutes {

	void define(String found, EAttributeRoute path);

	void define(String found, EReferenceRoute path);

	void ignore(String found, EClass location);

	Optional<EAttributeRoute> attribute(String found, EClass scope);

	Optional<EReferenceRoute> reference(String found, EClass scope);

	boolean ignored(String found, EClass location);

}
