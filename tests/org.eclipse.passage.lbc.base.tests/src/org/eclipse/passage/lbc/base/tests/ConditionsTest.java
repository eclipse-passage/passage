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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.mine.Conditions;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.junit.Test;

public final class ConditionsTest {

	@Test
	public void mineNothing() throws LicensingException, IOException {
		FloatingResponse response = new Conditions(request()).get();
		assertFalse(response.failed());
		assertTrue(license(response).getLicenseGrants().isEmpty());
	}

	private ProductUserRequest request() throws LicensingException {
		return new ProductUserRequest(//
				new RequestConstructed()//
						.withParameters(Arrays.asList(//
								new ProductIdentifier("proj"), //$NON-NLS-1$
								new ProductVersion("1.2.3"), //$NON-NLS-1$
								new LicenseUser("mow")) //$NON-NLS-1$
						).get()//
		);
	}

	private LicensePack license(FloatingResponse response) throws IOException, LicensingException {
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			response.write(stream);
			stream.flush();
			return new EObjectFromBytes<>(stream.toByteArray(), LicensePack.class).get();
		}

	}
}
