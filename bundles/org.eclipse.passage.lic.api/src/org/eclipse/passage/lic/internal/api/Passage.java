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
package org.eclipse.passage.lic.internal.api;

import org.eclipse.passage.lic.api.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;

/**
 * <p>
 * Main entry point for a code base under license protection.
 * </p>
 * FIXME: explain exhaustively how to use, with code examples
 * 
 * @see org.eclipse.passage.lic.api
 */
public interface Passage {

	/**
	 * FIXME: add `see` to (1) dedicated parts of the new version of AccessManager
	 * (2) licensing dialog
	 * 
	 * <p>
	 * Direct access to full access cycle up to all found restrictions execution.
	 * Can cause UI interference in the form of Licensing dialog.
	 * <p>
	 * 
	 * <p>
	 * Call this method from your {@code feature} code to ensure it is not used
	 * without proper license.
	 * </p>
	 * 
	 * @param feature string identifier of the feature under licensing.
	 * @see org.eclipse.passage.lic.api
	 */
	void checkLicense(String feature);

	/**
	 * FIXME: add `see` to dedicated parts of the new version of AccessManager
	 * <p>
	 * Headless way to check if the given {@code feature} is properly licensed. No
	 * actual {@linkplain RestrictionExecutor}s are going to be involved. FIXME:
	 * point to particular place in access cycle
	 * </p>
	 * <p>
	 * Use it in the case you cannot afford full feather License Dialog appearance.
	 * Like to implement {@code action::isEnabled} of sorts or to guide simple
	 * control flow. FIXME: samples
	 * </p>
	 * 
	 * @param feature string identifier of the feature under licensing.
	 * @return true if license check found all licensing requirements satisfied and
	 *         found no restrictions. Any restrictions discovered starting from
	 *         {@code warning} up to {@code fatal} causes {@code false} to be
	 *         returned here. FIXME explain where to get these codes.
	 * 
	 * @see Requirement
	 * @see RestrictionVerdict
	 * @see org.eclipse.passage.lic.api
	 */
	boolean canUse(String feature);

}
