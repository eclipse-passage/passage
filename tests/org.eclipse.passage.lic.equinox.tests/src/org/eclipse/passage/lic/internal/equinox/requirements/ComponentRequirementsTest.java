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
package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
public final class ComponentRequirementsTest {

	@Test
	public void providedAsResolvedRequirementsImpl() throws InvalidSyntaxException {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		assertTrue(context.getServiceReferences(ResolvedRequirements.class, null).stream() //
				.map(s -> context.getService(s)) //
				.filter(s -> s.getClass() == ComponentRequirements.class) //
				.findAny() //
				.isPresent());
	}

}
