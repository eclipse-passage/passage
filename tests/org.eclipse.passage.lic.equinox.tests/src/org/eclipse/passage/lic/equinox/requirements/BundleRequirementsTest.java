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
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Set;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;

/**
 * Integration test: requires OSGi running
 */
public final class BundleRequirementsTest extends ResolvedRequirementsServiceTest {

	@Override
	protected Class<?> serviceClass() {
		return BundleRequirements.class;
	}

	@Override
	protected Set<Requirement> expectations() {
		return new DataBundle().validRequirementsFromCapabilities();
	}

	@Override
	protected Requirement single() {
		return new DataBundle().pi();
	}

	@Override
	protected ResolvedRequirements service() {
		return new BundleRequirements();
	}

	@Override
	protected int invalidMorsels() {
		return 0;
	}

}
