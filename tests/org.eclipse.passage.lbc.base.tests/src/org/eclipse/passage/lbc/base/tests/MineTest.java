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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.base.BaseFlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.base.StringNamedData;
import org.junit.Test;

public final class MineTest {

	@Test
	public void demandsProductIdentifier() {
		testDemandProductInformation(version());
	}

	@Test
	public void demandsProductVersion() {
		testDemandProductInformation(id());
	}

	@Test
	public void demandsUser() {
		FloatingResponse response = new BaseFlotingRequestHandled(//
				new RequestConstructed()//
						.withAction(new ConditionAction.Mine())//
						.withParameters(Arrays.asList(id(), version())).get()//
		).get();
		assertFailedWithCode(response, new Failure.BadRequestNoUser());
	}

	private void testDemandProductInformation(StringNamedData half) {
		FloatingResponse response = new BaseFlotingRequestHandled(//
				new RequestConstructed()//
						.withAction(new ConditionAction.Mine())//
						.withParameter(half).get()//
		).get();
		assertFailedWithCode(response, new Failure.BadRequestInvalidProduct());
	}

	private void assertFailedWithCode(FloatingResponse response, Failure expected) {
		assertTrue(response.failed());
		assertEquals(expected.error().code(), response.error().code());
	}

	private ProductVersion version() {
		return new ProductVersion("1.1.0"); //$NON-NLS-1$
	}

	private ProductIdentifier id() {
		return new ProductIdentifier("sample-product");//$NON-NLS-1$
	}

}
