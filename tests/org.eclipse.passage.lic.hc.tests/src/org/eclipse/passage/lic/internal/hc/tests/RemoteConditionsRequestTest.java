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
package org.eclipse.passage.lic.internal.hc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.ProductIdentifier;
import org.eclipse.passage.lic.base.ProductVersion;
import org.eclipse.passage.lic.base.io.MD5Hashes;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.hc.remote.impl.mine.RemoteConditionsRequest;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationExpression;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationType;
import org.eclipse.passage.lic.licenses.model.api.EvaluationInstructions;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class RemoteConditionsRequestTest {

	private final String host = "fake.licensing.server"; //$NON-NLS-1$
	private final int port = 1234;
	private final String user = "some_user@some_mail.se"; //$NON-NLS-1$
	private final String environment = "some_env"; //$NON-NLS-1$
	private final String expression = "some_expression"; //$NON-NLS-1$
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void urlContainsAllParameters() throws IOException {
		URL url = url();
		assertEquals(host, url.getHost());
		assertEquals(port, url.getPort());
		assertNotNull(url.getQuery());
		queryHas(url, new ProductIdentifier("any").key()); //$NON-NLS-1$
		queryHas(url, new ProductVersion("any").key()); //$NON-NLS-1$
		queryHas(url, //
				new LicensingAction(new PassageAction.Of("any")).key(), //$NON-NLS-1$
				new PassageAction.Mine().name());
		queryHas(url, //
				new ServerAuthenticationExpression("any").key(), //$NON-NLS-1$
				expression);
		queryHas(url, //
				new ServerAuthenticationType("any").key(), //$NON-NLS-1$
				environment);
		queryHas(url, //
				new LicenseUser(user).key(), //
				user);
	}

	private void queryHas(URL url, String... values) {
		for (String value : values) {
			assertTrue(url.getQuery().contains(value));
		}
	}

	private URL url() {
		try {
			return new RemoteConditionsRequest<>(product(), access(), hashes()).url();
		} catch (LicensingException e) {
			fail("Url composition on valid parameters must succssed"); //$NON-NLS-1$
			throw new RuntimeException(e); // unreachable
		}
	}

	private FloatingLicenseAccess access() {
		FloatingLicenseAccess access = LicensesFactory.eINSTANCE.createFloatingLicenseAccess();
		access.setUser(user);
		FloatingServerConnection connection = LicensesFactory.eINSTANCE.createFloatingServerConnection();
		connection.setIp(host);
		connection.setPort(port);
		EvaluationInstructions auth = LicensesFactory.eINSTANCE.createEvaluationInstructions();
		auth.setType(environment);
		auth.setExpression(expression);
		connection.setAuthentication(auth);
		access.setServer(connection);
		access.setOriginLicensePack("none"); //$NON-NLS-1$
		return access;
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("fake-product", "0.1.27"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private HashesRegistry hashes() {
		return () -> new ReadOnlyRegistry<StringServiceId, Hashes>(new MD5Hashes());
	}

}
