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
package org.eclipse.passage.lic.api.requirements;

import org.eclipse.passage.lic.api.LicensingConfiguration;

/**
 * <p>
 * General contract for a service capable to obtain (resolve)
 * {@link LicensingRequirement}s from a particular installation of a program
 * under licensing. The contract serves the <i>requirements resolution phase</i>
 * of <i>access cycle</i>.
 * </p>
 *
 * <p>
 * During a program development some features are declared that the ones under
 * licensing. Such a declarations can be reflected in a type of physical sources
 * under a particular program installation. Example sources are
 * <i>manifest.mf</i>, <i>OSGI component manifest</i>.
 * </p>
 *
 * <p>
 * At the program runtime a {@code RequirementResolver} reads particular type of
 * physical sources and produce set of {@link LicensingRequirement}s that are
 * defined there.
 * </p>
 *
 * @see LicensingRequirement
 * @see org.eclipse.passage.lic.api
 * @since 0.4.0
 * 
 * @deprecated use
 *             {@link org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements}
 */
@Deprecated
public interface RequirementResolver {

	/**
	 * Having access to a particular type of physical sources, reads them for
	 * {@link LicensingRequirement} declared there.
	 *
	 * @param configuration general configuration
	 * @return resolved set of {@link LicensingRequirement}s, not nullable
	 * @since 0.4.0
	 */
	Iterable<LicensingRequirement> resolveLicensingRequirements(LicensingConfiguration configuration);

}
