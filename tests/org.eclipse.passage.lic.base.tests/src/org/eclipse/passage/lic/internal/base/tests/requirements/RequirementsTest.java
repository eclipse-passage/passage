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
package org.eclipse.passage.lic.internal.base.tests.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.access.Requirements;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class RequirementsTest {

	@Test
	public void noResolversMeansNoAccess() {
		ServiceInvocationResult<Collection<Requirement>> result = new Requirements(//
				new SabotagedFramework().accessCycleConfiguration().requirementResolvers().get(), //
				"feature0" //$NON-NLS-1$
		).get();
		assertFalse(result.diagnostic().severe().isEmpty());
		assertFalse(result.data().isPresent());
		assertEquals(//
				new NoServicesOfType("resolvers"), //$NON-NLS-1$
				result.diagnostic().severe().get(0).code());
	}

}
