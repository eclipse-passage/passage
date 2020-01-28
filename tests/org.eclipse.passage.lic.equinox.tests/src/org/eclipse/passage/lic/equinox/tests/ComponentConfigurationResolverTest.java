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
import org.osgi.service.log.LoggerFactory;

@SuppressWarnings("restriction")
public class ComponentConfigurationResolverTest {
	
	@Test
	public void testExecuteRestrictions() {
		ComponentConfigurationResolver resolver = new ComponentConfigurationResolver();
		resolver.bindScr(Mockito.mock(ServiceComponentRuntime.class));
		resolver.bindLoggerFactory(Mockito.mock(LoggerFactory.class));
		resolver.activate(Mockito.mock(BundleContext.class));
		Iterable<LicensingRequirement> requirements = resolver.resolveLicensingRequirements(null);
		assertNotNull(requirements);
	}

}
