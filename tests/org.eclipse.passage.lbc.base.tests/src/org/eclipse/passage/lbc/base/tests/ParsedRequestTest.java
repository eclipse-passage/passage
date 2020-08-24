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

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.ProductLicensesRequest;
import org.eclipse.passage.lbc.internal.base.ParsedRequest;
import org.junit.Test;

public final class ParsedRequestTest extends LbcTestsBase {

	@Test
	public void positive() {
		BackendLicensingRequest request = new FakeLicensingRequest(params(), ""); //$NON-NLS-1$
		ProductLicensesRequest miningRequest = Stream.of(request).map(new ParsedRequest()).collect(Collectors.toList())
				.get(0);
		assertEquals(userValue(), miningRequest.requester().hardware());
		assertEquals(identifierValue(), miningRequest.product().identifier());
		assertEquals(versionValue(), miningRequest.product().version());
	}

	private Map<String, String> params() {
		Map<String, String> params = new HashMap<>();
		params.put("user", userValue()); //$NON-NLS-1$
		params.put("licensing.product.identifier", identifierValue()); //$NON-NLS-1$
		params.put("licensing.product.version", versionValue()); //$NON-NLS-1$
		return params;
	}

}
