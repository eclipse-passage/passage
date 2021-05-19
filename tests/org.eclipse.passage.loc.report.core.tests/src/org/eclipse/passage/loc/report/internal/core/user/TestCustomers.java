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
import java.util.stream.Stream;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeCompanyDescriptor;
import org.eclipse.passage.loc.report.internal.core.FakeUserDescriptor;
import org.eclipse.passage.loc.report.internal.core.TestData;

abstract class TestCustomers implements TestData<CustomerStorage> {

	private final String[][] persons;
	private final String[][] companies;

	protected TestCustomers(String[][] persons, String[][] companies) {
		this.persons = persons;
		this.companies = companies;
	}

	Set<UserDescriptor> users() {
		return Arrays.stream(persons)//
				.map(r -> new FakeUserDescriptor(r[0], r[1]))//
				.collect(Collectors.toSet());
	}

	Set<UserOriginDescriptor> companies() {
		return Arrays.stream(companies)//
				.map(r -> new FakeCompanyDescriptor(r[0]))//
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> csv() {
		Set<String> data = Stream.concat(//
				Arrays.stream(persons), //
				Arrays.stream(companies))//
				.map(r -> String.format("%s;%s;%s", r[0], r[1], r[2])) //$NON-NLS-1$
				.collect(Collectors.toSet());
		data.add("name;usage;contact"); //$NON-NLS-1$
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
							"Erwin Rudolf Josef Alexander Schrödinger", //$NON-NLS-1$
							ProductCustomer.Usage.personal.name(), //
							"erwin.schroedinger@gmail.com" }, //$NON-NLS-1$
					new String[] { //
							"Михайло Васильевич Ломоносов", //$NON-NLS-1$
							ProductCustomer.Usage.personal.name(), //
							"lomonosov_1711@yandex.com" } //$NON-NLS-1$
			}, new String[][] { //
					new String[] { //
							"Santa Claus and Co", //$NON-NLS-1$
							ProductCustomer.Usage.company.name(), //
							"santa@lapland.org" }, //$NON-NLS-1$
			});
		}
	}

	static final class Empty extends TestCustomers {

		Empty() {
			super(new String[0][0], new String[0][0]);
		}

	}
}
