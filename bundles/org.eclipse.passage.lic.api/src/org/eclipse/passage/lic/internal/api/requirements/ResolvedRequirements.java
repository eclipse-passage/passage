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
package org.eclipse.passage.lic.internal.api.requirements;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;

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
 */
public interface ResolvedRequirements extends Service<StringServiceId> {

	/**
	 * Having access to a particular type of physical sources, reads them for all
	 * the {@link Requirement} declared there.
	 *
	 * @return resolved collection of {@link Requirement}s, not nullable
	 * @since 0.4.0
	 */
	Collection<Requirement> all();

	final class Smart {

		private final ResolvedRequirements delegate;

		public Smart(ResolvedRequirements delegate) {
			this.delegate = delegate;
		}

		/**
		 * Among {@linkplain ResolvedRequirements#all()} requirements here only those
		 * are selected that relates to the given {@code feature}.
		 * 
		 * @param configuration of the product under licensing
		 * @param feature       identifier of a feature, who's {@linkplain Requirement}s
		 *                      are demanded
		 * @return collection of {@linkplain Requirement} only for the given
		 *         {@code feature}
		 */
		public Collection<Requirement> forFeature(String feature) {
			return delegate.all().stream() //
					.filter(r -> feature.equals(r.feature().identifier())) //
					.collect(Collectors.toList());
		}

	}

}
