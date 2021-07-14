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
package org.eclipse.passage.lic.api.version;

/**
 * <p>
 * Well structured representation of a version.
 * </p>
 * 
 * @since 2.1
 */
public interface SemanticVersion {

	int major();

	int minor();

	int service();

	/**
	 * Qualifier structure is not formalized (raw string) and it is optional.
	 * 
	 * @return {@code true} is qualification is defined for the version and
	 *         {@code false} otherwise.
	 */
	boolean hasQualifier();

	/**
	 * <p>
	 * Get qualifier, if it is defined for the version.
	 * </p>
	 * <p>
	 * The call must be prevented by appealing to
	 * {@linkplain SemanticVersion#hasQualifier()}.
	 * </p>
	 * <p>
	 * Requesting absent qualifier must fire {@linkplain RuntimeException}.
	 * </p>
	 */
	String qualifier();

}
