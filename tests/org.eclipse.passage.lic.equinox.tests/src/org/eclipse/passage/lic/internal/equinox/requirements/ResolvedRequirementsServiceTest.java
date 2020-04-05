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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
abstract class ResolvedRequirementsServiceTest {

	@Test
	public void providedAsResolvedRequirementsImpl() throws InvalidSyntaxException {
		assertTrue(mayBeService().isPresent());
	}

	@Test
	public void allRequirements() throws InvalidSyntaxException {
		Collection<Requirement> requirements = service().all();
		assertTrue(requirements.stream() //
				.collect(Collectors.toSet())//
				.containsAll(expectations()));
	}

	@Test
	public void requirementsForFeature() throws InvalidSyntaxException {
		Requirement single = single();
		Collection<Requirement> forTheFeature = new ResolvedRequirements.Smart(service())
				.forFeature(single.feature().identifier());
		assertEquals(//
				Collections.singleton(single), //
				new HashSet<Requirement>(forTheFeature));
	}

	protected abstract Class<?> serviceClass();

	protected abstract Set<Requirement> expectations();

	protected abstract Requirement single();

	private Optional<ResolvedRequirements> mayBeService() throws InvalidSyntaxException {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		return context.getServiceReferences(ResolvedRequirements.class, null).stream() //
				.map(s -> context.getService(s)) //
				.filter(s -> s.getClass() == serviceClass()) //
				.findAny();
	}

	private ResolvedRequirements service() throws InvalidSyntaxException {
		Optional<ResolvedRequirements> service = mayBeService();
		assumeTrue(service.isPresent());
		return service.get();
	}

}
