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
package org.eclipse.passage.lic.api.requirements;

import java.util.List;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;

/**
 * The usage constraint defined for specific feature identifier with given
 * version. It is declared at a program development phase during creation of a
 * feature under licensing. Accessed at the program runtime by
 * {@link ResolvedRequirements}s.
 *
 * @see ResolvedRequirements
 * @since 2.1
 */
public interface Requirement {

	/**
	 * Coordinates of a feature under licensing
	 */
	Feature feature();

	/**
	 * The default restriction level, as defined by component vendor, may be
	 * redefined by specific configuration. Used as a hint for
	 * <code>RestrictionExaminer</code>>
	 * 
	 * @return restriction level, <code>warn</code> by default
	 */
	RestrictionLevel restrictionLevel();

	/**
	 * @return not-null, can be empty list of bundle-local paths to license
	 *         agreement content files, that a user must actively accept to be
	 *         allowed to use the feature
	 */
	List<ResolvedAgreement> agreements();

	/**
	 * The original physical source under the program installation, where this
	 * requirement has been read from by some {@code ResolvedRequirements} service.
	 *
	 * @return physical source (like file) defined by a program under protection
	 */
	Object source();

}
