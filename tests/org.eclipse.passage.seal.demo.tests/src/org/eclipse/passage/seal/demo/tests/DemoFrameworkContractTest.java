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
package org.eclipse.passage.seal.demo.tests;

import org.eclipse.passage.lic.internal.api.Framework;

import java.util.Optional;

import org.eclipse.passage.lic.api.tests.FrameworkContractTest;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.seal.internal.demo.DemoFrameworkSupplier;

@SuppressWarnings("restriction")
public final class DemoFrameworkContractTest extends FrameworkContractTest {

	@Override
	protected Optional<Framework> framework() {
		return new DemoFrameworkSupplier().get();
	}

	@Override
	protected boolean readOnly(Registry<?, ?> registry) {
		return ReadOnlyRegistry.class.isInstance(registry);
	}

}
