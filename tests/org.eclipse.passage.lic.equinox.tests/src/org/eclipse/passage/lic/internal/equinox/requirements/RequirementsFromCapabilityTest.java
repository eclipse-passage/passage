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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class RequirementsFromCapabilityTest {

	@Test
	public void keepReadingOnMorselFailure() {
		DataBundle data = new DataBundle();
		ServiceInvocationResult<Collection<Requirement>> result = data.capabilities().stream() //
				.map(c -> new RequirementFromCapability(data.bundle(), c)) //
				.map(RequirementFromCapability::get) //
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<Requirement>()))// ;
				.get();
		assertContainsProperErrorDiagnostic(result.diagnostic());
		assertContainsAllExpectedRequirements(data, result.data());
	}

	private void assertContainsProperErrorDiagnostic(Diagnostic diagnostic) {
		assertEquals(1, diagnostic.severe().size());
		assertEquals(new ServiceFailedOnMorsel(), diagnostic.severe().get(0).code());
	}

	private void assertContainsAllExpectedRequirements(DataBundle data,
			Optional<Collection<Requirement>> requirements) {
		assertTrue(requirements.isPresent());
		assertEquals(//
				data.validRequirementsFromCapabilities(), //
				new HashSet<>(requirements.get())//
		);
	}

}
