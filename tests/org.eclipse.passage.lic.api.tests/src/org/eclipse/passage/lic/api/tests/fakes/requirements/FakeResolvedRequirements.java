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
package org.eclipse.passage.lic.api.tests.fakes.requirements;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.api.tests.fakes.FakeServiceInvocationResult;

public final class FakeResolvedRequirements implements ResolvedRequirements {

	private final Collection<Requirement> requirements;

	public FakeResolvedRequirements(Collection<Requirement> requirements) {
		this.requirements = requirements;
	}

	public FakeResolvedRequirements() {
		this(Collections.emptyList());
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("fake-req-res"); //$NON-NLS-1$
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> all() {
		return new FakeServiceInvocationResult<Collection<Requirement>>(requirements);
	}

}
