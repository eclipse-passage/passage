/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core.user;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeUserDescriptor;
import org.eclipse.passage.loc.report.internal.core.TestData;

abstract class TestCustomers implements TestData<CustomerStorage> {

	private final String[][] records;

	TestCustomers(String[][] records) {
		this.records = records;
	}

	Set<UserDescriptor> descriptors() {
		return Arrays.stream(records)//
				.map(r -> new FakeUserDescriptor(r[0], r[1]))//
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> csv() {
		Set<String> data = Arrays.stream(records)//
				.map(r -> r[0] + ";" + r[1]) //$NON-NLS-1$
				.collect(Collectors.toSet());
		data.add("email;name"); //$NON-NLS-1$
		return data;
	}

	@Override
	public CustomerStorage storage() {
		return new FakeCustomersBase(this);
	}

	static final class Some extends TestCustomers {

		Some() {
			super(new String[][] { //
					new String[] { //
							"erwin.schrodinger@gmail.com", //$NON-NLS-1$
							"Erwin Rudolf Josef Alexander Schrödinger" }, //$NON-NLS-1$
					new String[] { //
							"lomonosov_1711@yandex.com", //$NON-NLS-1$
							"Михайло Васиильевич Ломоноосов" } //$NON-NLS-1$
			});
		}

	}

	static final class Empty extends TestCustomers {

		Empty() {
			super(new String[0][0]);
		}

	}

}
