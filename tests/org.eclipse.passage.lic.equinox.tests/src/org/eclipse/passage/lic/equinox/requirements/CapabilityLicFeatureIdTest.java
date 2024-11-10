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

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.base.BaseNamedData;

public final class CapabilityLicFeatureIdTest extends CapabilityLicFeatureInfoTest<FeatureIdentifier> {

	@Override
	protected BaseNamedData<FeatureIdentifier> infoSupplier(Map<String, Object> attributes) {
		return new CapabilityLicFeatureId(attributes);
	}

	@Override
	protected Set<FeatureIdentifier> expectations() {
		return Set.of(//
				new BaseFeatureIdentifier("PI"), //$NON-NLS-1$
				new BaseFeatureIdentifier("E"), //$NON-NLS-1$
				new BaseFeatureIdentifier("Incomplete") //$NON-NLS-1$
		);
	}

}
