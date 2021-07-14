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

import java.util.Collection;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.StringServiceId;

/**
 * <p>
 * General contract for a service capable to obtain (resolve)
 * {@link Requirement}s from a particular installation of a program under
 * licensing. The contract serves the <i>requirements resolution phase</i> of
 * <i>access cycle</i>.
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
 * physical sources and produce set of {@link Requirement}s that are defined
 * there.
 * </p>
 *
 * @see Requirement
 * @see org.eclipse.passage.lic.api
 * @since 2.1
 */
public interface ResolvedRequirements extends Service<StringServiceId> {

	/**
	 * Having access to a particular type of physical sources, reads them for all
	 * the {@link Requirement} declared there.
	 *
	 * @return resolved collection of {@link Requirement}s, never null, couples with
	 *         {@linkplain Diagnostic} instance
	 */
	ServiceInvocationResult<Collection<Requirement>> all();

}
