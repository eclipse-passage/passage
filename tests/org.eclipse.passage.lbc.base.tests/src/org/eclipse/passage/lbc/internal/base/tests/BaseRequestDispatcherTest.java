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
package org.eclipse.passage.lbc.internal.base.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.passage.lbc.api.tests.FakeLicensingRequest;
import org.eclipse.passage.lbc.api.tests.FakeLicensingResponse;
import org.eclipse.passage.lbc.internal.base.BaseRequestDispatcher;
import org.junit.Test;

public class BaseRequestDispatcherTest {

	@Test
	public void nullAction() throws IOException {
		BaseRequestDispatcher dispatcher = new BaseRequestDispatcher(new HashMap<>());
		FakeLicensingRequest request = new FakeLicensingRequest(new HashMap<>());
		String expected = "{\"error\":\"unsupported action null\"}"; //$NON-NLS-1$
		dispatch(dispatcher, request, expected);
	}

	@Test
	public void unknownAction() throws IOException {
		BaseRequestDispatcher dispatcher = new BaseRequestDispatcher(new HashMap<>());
		FakeLicensingRequest request = new FakeLicensingRequest(Collections.singletonMap("action", "do-it-now")); //$NON-NLS-1$ //$NON-NLS-2$
		String expected = "{\"error\":\"unsupported action do-it-now\"}"; //$NON-NLS-1$
		dispatch(dispatcher, request, expected);
	}

	private void dispatch(BaseRequestDispatcher dispatcher, FakeLicensingRequest request, String expected)
			throws IOException {
		FakeLicensingResponse result = new FakeLicensingResponse();
		dispatcher.dispatch(request, result);
		assertEquals(expected, result.toString());
	}

}
