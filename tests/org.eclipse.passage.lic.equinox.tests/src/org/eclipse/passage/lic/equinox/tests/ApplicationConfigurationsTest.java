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
package org.eclipse.passage.lic.equinox.tests;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.equinox.ApplicationConfigurations;
import org.junit.Test;
import org.osgi.framework.Bundle;

public class ApplicationConfigurationsTest {

	private final class InvalidApplicationContext implements IApplicationContext {
		@Override
		public void setResult(Object result, IApplication application) {
		}

		@Override
		public String getBrandingProperty(String key) {
			return null;
		}

		@Override
		public String getBrandingName() {
			return null;
		}

		@Override
		public String getBrandingId() {
			return null;
		}

		@Override
		public String getBrandingDescription() {
			return null;
		}

		@Override
		public Bundle getBrandingBundle() {
			return null;
		}

		@Override
		public String getBrandingApplication() {
			return null;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Map getArguments() {
			return null;
		}

		@Override
		public void applicationRunning() {
		}
	}

	@Test
	public void testInvalidProduct() {
		assertEquals(LicensingConfigurations.INVALID.getProductIdentifier(),
				ApplicationConfigurations.getLicensingProductIdentifier(null));
		assertEquals(LicensingConfigurations.INVALID.getProductIdentifier(),
				ApplicationConfigurations.getLicensingProductIdentifier(new InvalidApplicationContext()));
	}

}
