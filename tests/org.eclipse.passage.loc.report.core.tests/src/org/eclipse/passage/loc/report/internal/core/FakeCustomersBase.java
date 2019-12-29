/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;

@SuppressWarnings("restriction")
final class FakeCustomersBase implements CustomerStorage {

	@Override
	public Set<UserDescriptor> forProducts(Set<String> products) {
		return Arrays.stream(new UserDescriptor[] { //
				new FakeUserDescriptor(//
						"reiner.maria.rilke@gmail.com", //$NON-NLS-1$
						"René Karl Wilhelm Johann Josef Maria Rilke"), //$NON-NLS-1$
				new FakeUserDescriptor(//
						"erwin.schrodinger@gmail.com", //$NON-NLS-1$
						"Erwin Rudolf Josef Alexander Schrödinger"), //$NON-NLS-1$
				new FakeUserDescriptor(//
						"lomonosov_1711@yandex.com", //$NON-NLS-1$
						"Михайло Васильевич Ломоносов"), //$NON-NLS-1$
				new FakeUserDescriptor(//
						"football-asia-cup-2007@gmail.com", //$NON-NLS-1$
						"오범석 呉範錫") //$NON-NLS-1$
		}).collect(Collectors.toSet());
	}

	@Override
	public void installUserRegistry(UserRegistry userRegistry) {
		throw new UnsupportedOperationException();
	}

}
