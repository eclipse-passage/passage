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
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.tests.fakes.io.FakeKeyKeeper;

/**
 * <p>
 * Check that {@linkplain Framework} instance in use supplies read only
 * collection of public key keepers.
 * </p>
 * <p>
 * Each {@code Framework} implementation must supply a test extending this class
 * and satisfy all the demands.
 * </p>
 */
public abstract class KeyKeepersRegitryTest extends ReadOnlyCollectionTest<KeyKeeper> {

	@Override
	protected final Supplier<Collection<KeyKeeper>> collection() {
		return () -> framework().get().accessCycleConfiguration().keyKeepers().get().services();
	}

	@Override
	protected final KeyKeeper single() {
		return new FakeKeyKeeper();
	}

	protected abstract Optional<Framework> framework();

}
