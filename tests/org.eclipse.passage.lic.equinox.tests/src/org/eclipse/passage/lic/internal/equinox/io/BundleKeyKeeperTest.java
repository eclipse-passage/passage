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
package org.eclipse.passage.lic.internal.equinox.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.equinox.io.BundleKeyKeeper;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Integration test: demands OSGi running
 */
public final class BundleKeyKeeperTest {

	@Test
	public void exisgingKeyFileFound() throws IOException {
		try {
			InputStream stream = new BundleKeyKeeper(this::productWithKey, bundle()).productPublicKey();
			assertNotNull(stream);
			stream.close();
		} catch (LicensingException e) {
			fail("Public key file stream retrieval is not supposed to fail on valid data"); //$NON-NLS-1$
		}
	}

	@SuppressWarnings("resource") // stream is not supposed to be opened
	@Test
	public void foreignProductKeyIsIgnired() throws IOException {
		try {

			new BundleKeyKeeper(this::productWithoutKey, bundle()).productPublicKey();
		} catch (LicensingException e) {
			assertTrue(e.getMessage().contains("find")); //$NON-NLS-1$
			return;
		}
		fail("Public key for the foreign product is not supposed to fit"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void productIsMandatory() {
		new BundleKeyKeeper(null, bundle());
	}

	@Test(expected = NullPointerException.class)
	public void bundleIsMandatory() {
		new BundleKeyKeeper(this::productWithKey, (Bundle) null);
	}

	private Bundle bundle() {
		return FrameworkUtil.getBundle(getClass());
	}

	private LicensedProduct productWithKey() {
		return new BaseLicensedProduct("fake-product", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private LicensedProduct productWithoutKey() {
		return new BaseLicensedProduct("another-fake-product", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
