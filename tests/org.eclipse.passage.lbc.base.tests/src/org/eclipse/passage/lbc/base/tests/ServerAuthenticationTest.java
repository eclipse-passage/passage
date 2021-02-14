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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lbc.internal.base.BaseFlotingRequestHandled;
import org.eclipse.passage.lic.floating.model.net.ServerAuthenticationExpression;
import org.eclipse.passage.lic.floating.model.net.ServerAuthenticationType;
import org.eclipse.passage.lic.internal.api.EvaluationType;
import org.eclipse.passage.lic.internal.api.PassageAction;
import org.eclipse.passage.lic.internal.base.StringNamedData;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;
import org.junit.Test;

public final class ServerAuthenticationTest {

	@Test
	public void demandsServerAuthType() {
		testDemandServerAuthInformation(expression());
	}

	@Test
	public void demandsServerAuthExpression() {
		testDemandServerAuthInformation(type());
	}

	private void testDemandServerAuthInformation(StringNamedData half) {
		NetResponse response = new BaseFlotingRequestHandled(//
				new RequestConstructed()//
						.withAction(new PassageAction.Mine())//
						.withParameter(half).get()//
		).get();
		assertFailedWithCode(response, new Failure.BadRequestInvalidServerAuthInstructions());
	}

	private void assertFailedWithCode(NetResponse response, Failure expected) {
		assertTrue(response.failed());
		assertEquals(expected.error().code(), response.error().code());
	}

	private ServerAuthenticationType type() {
		return new ServerAuthenticationType(new EvaluationType.Hardware().identifier());
	}

	private ServerAuthenticationExpression expression() {
		return new ServerAuthenticationExpression("os.family=*"); //$NON-NLS-1$
	}

}
