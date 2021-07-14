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
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.api.tests.fakes.requirements.FakeRequirement;

/**
 * Each requirement resolution service must follow the contract.
 */
public abstract class ResolvedRequirementsContractTest extends ReadOnlyCollectionTest<Requirement> {

	@Override
	protected final Supplier<Collection<Requirement>> collection() {
		return service().all().data()::get;
	}

	@Override
	protected Requirement single() {
		return new FakeRequirement();
	}

	protected abstract ResolvedRequirements service();

}
