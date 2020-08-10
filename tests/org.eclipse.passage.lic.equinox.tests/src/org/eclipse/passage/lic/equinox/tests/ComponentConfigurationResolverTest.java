/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
import org.eclipse.passage.lic.internal.equinox.requirements.ComponentConfigurationResolver;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

public class ComponentConfigurationResolverTest {

	@Test
	public void testNoBundleContext() {
		ComponentConfigurationResolver resolver = new ComponentConfigurationResolver();
		LoggerFactory factory = Mockito.mock(LoggerFactory.class);
		Mockito.doReturn(Mockito.mock(Logger.class)).when(factory).getLogger(ComponentConfigurationResolver.class);
		resolver.bindLoggerFactory(factory);
		resolver.activate(null);
		Iterable<LicensingRequirement> requirements = resolver.resolveLicensingRequirements(null);
		assertNotNull(requirements);
	}

	@Test
	public void testResolveRequirements() {
		ComponentConfigurationResolver resolver = new ComponentConfigurationResolver();
		ServiceComponentRuntime src = Mockito.mock(ServiceComponentRuntime.class);
		resolver.bindScr(src);
		LoggerFactory factory = Mockito.mock(LoggerFactory.class);
		resolver.bindLoggerFactory(factory);
		resolver.activate(Mockito.mock(BundleContext.class));
		Iterable<LicensingRequirement> requirements = resolver.resolveLicensingRequirements(null);
		assertNotNull(requirements);
		resolver.deactivate();
		resolver.unbindLoggerFactory(factory);
		resolver.unbindScr(src);
		resolver.unbindLoggerFactory(null);
		resolver.unbindScr(null);
	}

}
