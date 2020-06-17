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
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.tests.fakes.FakeRequirement;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

/**
 * Each requirement resolution service must follow the contract.
 * 
 * @author user
 *
 */
@SuppressWarnings("restriction")
public abstract class ResolvedRequirementsContractTest extends ReadOnlyCollectionTest<Requirement> {

	@Override
	protected Supplier<Collection<Requirement>> collection() {
		return service()::all;
	}

	@Override
	protected Requirement single() {
		return new FakeRequirement();
	}

	protected abstract ResolvedRequirements service();

}
