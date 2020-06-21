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
package org.eclipse.passage.lic.api.tests.conditions.mining;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class ConditionTransportContractTest {

	@Test(expected = NullPointerException.class)
	public void conditionsAreMandatory() throws IOException {
		transport().write(null, new ByteArrayOutputStream());
	}

	@Test
	public void readYourOwnWritings() throws IOException {
		// given
		Collection<Condition> sedentaries = conditions();
		assumeTrue("Too few: supply at least two test conditions for transportation", sedentaries.size() > 1); //$NON-NLS-1$
		ConditionTransport transport = transport();
		// when
		String serialized = write(transport, sedentaries);
		Collection<Condition> nomads = read(transport, serialized);
		// then
		assertEquals(new HashSet<>(textual(sedentaries)), new HashSet<>(textual(nomads)));
	}

	private String write(ConditionTransport transport, Collection<Condition> conditions) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		transport.write(conditions, output);
		return new String(output.toByteArray());
	}

	private Collection<Condition> read(ConditionTransport transport, String source) throws IOException {
		return transport.read(new ByteArrayInputStream(source.getBytes()));
	}

	private Set<String> textual(Collection<Condition> conditions) {
		return conditions.stream()//
				.map(this::textual) //
				.collect(Collectors.toSet());
	}

	/**
	 * Provide any textual representation of a condition that distinguishes two
	 * instances with differing content.
	 */
	protected abstract String textual(Condition condition);

	/**
	 * Produce new instance of a transport under test
	 */
	protected abstract ConditionTransport transport();

	/**
	 * Supply as least two test condition for transportation
	 */
	protected abstract Collection<Condition> conditions();
}
