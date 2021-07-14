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
package org.eclipse.passage.lic.internal.equinox.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.base.ProductIdentifier;
import org.eclipse.passage.lic.base.ProductVersion;
import org.eclipse.passage.lic.base.version.DefaultVersion;
import org.eclipse.passage.lic.equinox.LicensedProductFromContext;
import org.junit.Test;
import org.osgi.framework.Bundle;

public final class LicensedProductFromContextTest {

	@Test(expected = NullPointerException.class)
	public void nullCOntextIsProhibited() {
		new LicensedProductFromContext(null);
	}

	@Test
	public void lackOfProductIdentifierBegetsInvalidConfiguration() {
		assertEquals(new InvalidLicensedProduct(), //
				new LicensedProductFromContext(//
						new Empty(new ProductVersion("").key(), "1.0.0") //$NON-NLS-1$ //$NON-NLS-2$
				).get()//
		);
	}

	@Test
	public void validProductIdIsSufficientForValidConfiguration() {
		assertNotEquals(new InvalidLicensedProduct(), //
				new LicensedProductFromContext(//
						new Empty(new ProductIdentifier("").key(), "some.valid.product.id") //$NON-NLS-1$ //$NON-NLS-2$
				).get() //
		);
	}

	@Test
	public void incorrectVersionTurnedToDefault() {
		Map<String, String> properties = new HashMap<>();
		properties.put(new ProductIdentifier("").key(), "some.valid.product.id");//$NON-NLS-1$ //$NON-NLS-2$
		properties.put(new ProductVersion("").key(), "very-much-incorrect-version-value");//$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(//
				new DefaultVersion().toString(), //
				new LicensedProductFromContext(new Empty(properties)).get().version() //
		);
	}

	private static final class Empty implements IApplicationContext {

		private final Map<String, String> properties;

		Empty(Map<String, String> properties) {
			this.properties = properties;
		}

		Empty(String key, String value) {
			this.properties = new HashMap<>();
			properties.put(key, value);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Map getArguments() {
			return new HashMap();
		}

		@Override
		public void applicationRunning() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getBrandingApplication() {
			return null;
		}

		@Override
		public String getBrandingName() {
			return null;
		}

		@Override
		public String getBrandingDescription() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getBrandingId() {
			return null;
		}

		@Override
		public String getBrandingProperty(String key) {
			return properties.get(key);
		}

		@Override
		public Bundle getBrandingBundle() {
			return null;
		}

		@Override
		public void setResult(Object result, IApplication application) {
			throw new UnsupportedOperationException();
		}

	}

}
