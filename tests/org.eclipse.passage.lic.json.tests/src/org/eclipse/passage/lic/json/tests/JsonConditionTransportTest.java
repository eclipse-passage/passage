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
package org.eclipse.passage.lic.json.tests;

import java.util.Collection;

import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.tests.conditions.mining.ConditionTransportContractTest;
import org.eclipse.passage.lic.internal.json.JsonConditionTransport;

public final class JsonConditionTransportTest extends ConditionTransportContractTest {

	private final TestConditions data = new TestConditions();

	@Override
	protected ConditionTransport transport() {
		return new JsonConditionTransport();
	}

	@Override
	protected Collection<Condition> conditions() {
		return data.conditions();
	}

	@Override
	protected String serialized(Collection<Condition> condition) {
		return data.textual();
	}

	@Override
	protected String serializedInvalid() {
		return "{\r\n" + //$NON-NLS-1$
				"  \"conditions\" : [ {\r\n" + //$NON-NLS-1$
				"    \"feature\" : \"who-are-you-guys?\",\r\n" + //$NON-NLS-1$
				"    \"rule\" : \"equivalent\",\r\n" + //$NON-NLS-1$
				"    \"period-closed-from\" : \"2020-07-30T19:23:18.898+03:00[Europe/Moscow]\",\r\n" + //$NON-NLS-1$
				"    \"period-closed-to\" : \"2020-07-30T19:23:19.898+03:00[Europe/Moscow]\",\r\n" + //$NON-NLS-1$
				"    \"expression\" : \"cow-says=moo;cat-says=meow\"\r\n" + //$NON-NLS-1$
				"  } ]\r\n" + //$NON-NLS-1$
				"}\r\n"; //$NON-NLS-1$
	}

}
