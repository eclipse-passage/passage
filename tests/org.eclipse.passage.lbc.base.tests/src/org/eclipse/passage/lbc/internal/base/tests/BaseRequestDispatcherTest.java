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
import java.util.Map;

import org.eclipse.passage.lbc.api.tests.FakeLicensingResponse;
import org.eclipse.passage.lbc.base.tests.LbcTestsBase;
import org.eclipse.passage.lbc.internal.base.BaseLicensingRequest;
import org.eclipse.passage.lbc.internal.base.BaseRequestDispatcher;
import org.junit.Test;

public class BaseRequestDispatcherTest extends LbcTestsBase {

	@Test
	public void nullAction() throws IOException {
		BaseRequestDispatcher dispatcher = new BaseRequestDispatcher(new HashMap<>());
		BaseLicensingRequest<Map<String, String>> request = new BaseLicensingRequest<>(new HashMap<>(),
				this::requestParameter, this::requestBody);
		String expected = "{\"error\":\"unsupported action null\"}"; //$NON-NLS-1$
		assertEquals(expected, dispatch(dispatcher, request));
	}

	@Test
	public void unknownAction() throws IOException {
		BaseRequestDispatcher dispatcher = new BaseRequestDispatcher(new HashMap<>());
		BaseLicensingRequest<Map<String, String>> request = new BaseLicensingRequest<>(
				Collections.singletonMap("action", "do-it-now"), this::requestParameter, this::requestBody); //$NON-NLS-1$ //$NON-NLS-2$
		String expected = "{\"error\":\"unsupported action do-it-now\"}"; //$NON-NLS-1$
		assertEquals(expected, dispatch(dispatcher, request));
	}

	private String dispatch(BaseRequestDispatcher dispatcher, BaseLicensingRequest<Map<String, String>> request)
			throws IOException {
		FakeLicensingResponse result = new FakeLicensingResponse();
		dispatcher.dispatch(request, result);
		return result.toString();
	}

}
