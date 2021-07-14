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
package org.eclipse.passage.lic.api.tests.conditions.mining;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.junit.Test;

public abstract class ConditionTransportContractTest {

	@Test
	public final void identifiedByContentType() {
		assertNotNull(transport().id());
	}

	@Test
	public final void readYourOwnWritings() throws IOException {
		// given
		Collection<Condition> sedentaries = conditions();
		assumeTrue("Too few: supply at least two test conditions for transportation", sedentaries.size() > 1); //$NON-NLS-1$
		ConditionTransport transport = transport();
		// when
		Collection<Condition> nomads = read(transport, serialized(sedentaries));
		// then
		assertEquals(new HashSet<>(textual(sedentaries)), new HashSet<>(textual(nomads)));
	}

	@Test(expected = IOException.class)
	public final void failOnNotSufficientData() throws IOException {
		read(transport(), serializedInvalid()); // $NON-NLS-1$
	}

	@Test(expected = IOException.class)
	public final void failOnEmptySource() throws IOException {
		read(transport(), ""); //$NON-NLS-1$
	}

	private Collection<Condition> read(ConditionTransport transport, String source) throws IOException {
		return transport.read(new ByteArrayInputStream(source.getBytes())).conditions();
	}

	private Set<String> textual(Collection<Condition> condition) {
		return condition.stream()//
				.map(this::textual) //
				.collect(Collectors.toSet());
	}

	private String textual(Condition condition) {
		return new StringBuilder()//
				.append(condition.feature())//
				.append(condition.versionMatch().version())//
				.append(condition.versionMatch().rule().identifier())//
				.append(((ValidityPeriodClosed) condition.validityPeriod()).from())//
				.append(((ValidityPeriodClosed) condition.validityPeriod()).to())//
				.append(condition.evaluationInstructions().type().identifier())//
				.append(condition.evaluationInstructions().expression()) //
				.toString();
	}

	/**
	 * Supply source for reading which is expected to be parsed to
	 * {@code conditions()}
	 */
	protected abstract String serialized(Collection<Condition> condition);

	/**
	 * Supply source for reading a single condition with lack if mandatory data
	 * {@code conditions()}
	 */
	protected abstract String serializedInvalid();

	/**
	 * Produce new instance of a transport under test
	 */
	protected abstract ConditionTransport transport();

	/**
	 * Supply as least two test condition for transportation
	 */
	protected abstract Collection<Condition> conditions();

}
