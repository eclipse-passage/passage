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
package org.eclipse.passage.lic.api.tests;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.tests.fakes.FakeResolvedRequirements;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

/**
 * <p>
 * Check that {@linkplain Framework} instance in use supplies read only
 * collection of requirement resolution services.
 * </p>
 * <p>
 * Each {@code Framework} implementation must extend this class and satisfy all
 * the demands.
 * </p>
 */
@SuppressWarnings("restriction")
public abstract class FrameworkRequirementResolutionServiceTest extends ReadOnlyCollectionTest<ResolvedRequirements> {

	@Override
	protected final Supplier<Collection<ResolvedRequirements>> collection() {
		return () -> framework().get().requirementsRegistry().get().services();
	}

	@Override
	protected final ResolvedRequirements single() {
		return new FakeResolvedRequirements();
	}

	protected abstract Optional<Framework> framework();

}
