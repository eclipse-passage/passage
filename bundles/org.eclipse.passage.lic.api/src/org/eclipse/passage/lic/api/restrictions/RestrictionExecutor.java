/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.api.restrictions;

import org.eclipse.passage.lic.api.LicensingResult;

/**
 * 
 * Implement the {@link RestrictionVerdict}(s) for the licensed feature:
 * <li>early exit from command line tools with notice</li>
 * <li>blocking dialogs for UI application</li>
 * <li>filtering out the UI</li>
 * <li>blocking of bundles using OSGi level</li>
 *
 * @since 0.4.0
 */
public interface RestrictionExecutor {

	LicensingResult execute(Iterable<RestrictionVerdict> verdicts);

}
