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

import org.eclipse.passage.lic.api.tests.fakes.restrictions.FakeRestrictionExecutingService;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.restrictions.execution.RestrictionExecutingService;

/**
 * <p>
 * Check that {@linkplain Framework} instance in use supplies read only
 * collection of services responsible for executing restriction.
 * </p>
 * <p>
 * Each {@code Framework} implementation must supply a test extending this class
 * and satisfy all the demands.
 * </p>
 */
@SuppressWarnings("restriction")
public abstract class RestrictionExecutingServicesRegitryTest
		extends ReadOnlyCollectionTest<RestrictionExecutingService> {

	@Override
	protected final Supplier<Collection<RestrictionExecutingService>> collection() {
		return () -> framework().get().accessCycleConfiguration().executors().get().services();
	}

	@Override
	protected final RestrictionExecutingService single() {
		return new FakeRestrictionExecutingService();
	}

	protected abstract Optional<Framework> framework();

}
