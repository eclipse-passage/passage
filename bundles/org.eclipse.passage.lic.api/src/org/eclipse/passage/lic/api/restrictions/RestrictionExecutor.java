/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.restrictions;

import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 *
 * The service implementing the <i>restrictions execution</i> phase of <i>
 * access cycle</i>.
 * 
 * @since 0.4.0
 * @see org.eclipse.passage.lic.api
 * @deprecated Restriction execution is not part of access cycle anymore. It is
 *             now designed as a set of developer's facilities for
 *             {@linkplain Diagnostic} and {@linkplain ExaminationCertificate}
 *             analysis as well as for performing some predefined restrictive
 *             actions.
 */
@Deprecated
public interface RestrictionExecutor {

	/**
	 * <p>
	 * Applies the given {@link RestrictionVerdict}s for feature under licensing to
	 * the current runtime of the program.
	 * </p>
	 *
	 * <p>
	 * Is capable to affect the program runtime environment the following ways:
	 * </p>
	 * <ol>
	 * <li>early exit from command line tools with notice</li>
	 * <li>block dialogs for UI application</li>
	 * <li>filter out the UI</li>
	 * <li>block of bundles using OSGi level</li>
	 * </ol>
	 *
	 * @param verdicts restriction instructions gained at the <i>permission
	 *                 examining</i> phase of <i>access cycle</i>
	 * @return the final result summary of the current <i>access cycle</i>
	 *
	 * @since 0.4.0
	 */
	LicensingResult execute(Iterable<RestrictionVerdict> verdicts);

}
