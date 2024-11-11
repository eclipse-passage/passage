/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.base.StringNamedData;

public final class CapabilityLicFeatureNameTest extends CapabilityLicFeatureInfoTest<String> {

	@Override
	protected StringNamedData infoSupplier(Map<String, Object> attributes) {
		return new CapabilityLicFeatureName(attributes);
	}

	@Override
	protected Set<String> expectations() {
		return Set.of(//
				"PI of version PI", //$NON-NLS-1$
				"Euler number" //$NON-NLS-1$
		);
	}

}
