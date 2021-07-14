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
package org.eclipse.passage.lic.api.tests;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermissionEmittingService;

/**
 * <p>
 * Check that {@linkplain Framework} instance in use supplies read only
 * collection of permission emission services.
 * </p>
 * <p>
 * Each {@code Framework} implementation must supply a test extending this class
 * and satisfy all the demands.
 * </p>
 */
public abstract class RequirementResolutionServicesRegitryTest
		extends ReadOnlyCollectionTest<PermissionEmittingService> {

	@Override
	protected final Supplier<Collection<PermissionEmittingService>> collection() {
		return () -> framework().get().accessCycleConfiguration().permissionEmitters().get().services();
	}

	@Override
	protected final PermissionEmittingService single() {
		return new FakePermissionEmittingService();
	}

	protected abstract Optional<Framework> framework();

}
