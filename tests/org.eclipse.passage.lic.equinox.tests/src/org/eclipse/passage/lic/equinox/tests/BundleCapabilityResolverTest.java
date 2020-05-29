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

import static org.junit.Assert.assertNotNull;

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.internal.equinox.requirements.BundleCapabilityResolver;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

public class BundleCapabilityResolverTest {

	@Test
	public void testNoBundleContext() {
		BundleCapabilityResolver resolver = new BundleCapabilityResolver();
		LoggerFactory factory = Mockito.mock(LoggerFactory.class);
		Mockito.doReturn(Mockito.mock(Logger.class)).when(factory).getLogger(BundleCapabilityResolver.class);
		resolver.bindLoggerFactory(factory);
		resolver.activate(null);
		Iterable<LicensingRequirement> requirements = resolver.resolveLicensingRequirements(null);
		assertNotNull(requirements);
	}

	@Test
	public void testResolveRequirements() {
		BundleCapabilityResolver resolver = new BundleCapabilityResolver();
		ServiceComponentRuntime scr = Mockito.mock(ServiceComponentRuntime.class);
		resolver.bindScr(scr);
		LoggerFactory factory = Mockito.mock(LoggerFactory.class);
		resolver.bindLoggerFactory(factory);
		BundleContext mock = Mockito.mock(BundleContext.class);
		Mockito.doReturn(new Bundle[0]).when(mock).getBundles();
		resolver.activate(mock);
		Iterable<LicensingRequirement> requirements = resolver.resolveLicensingRequirements(null);
		assertNotNull(requirements);
		resolver.deactivate();
		resolver.unbindLoggerFactory(factory);
		resolver.unbindScr(scr);
		resolver.unbindLoggerFactory(null);
		resolver.unbindScr(null);
	}

}
