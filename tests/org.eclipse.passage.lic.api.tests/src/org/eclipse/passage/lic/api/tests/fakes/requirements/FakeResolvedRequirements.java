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
package org.eclipse.passage.lic.api.tests.fakes.requirements;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

@SuppressWarnings("restriction")
public final class FakeResolvedRequirements implements ResolvedRequirements {

	@Override
	public StringServiceId id() {
		return new StringServiceId("fake-req-res"); //$NON-NLS-1$
	}

	@Override
	public Collection<Requirement> all() {
		throw new UnsupportedOperationException();
	}

}
