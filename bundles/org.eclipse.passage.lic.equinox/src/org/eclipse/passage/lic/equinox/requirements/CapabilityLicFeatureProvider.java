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

import java.util.Map;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.NamedData;

/**
 * Encapsulate reading of a {@code provider} information for a feature under
 * licensing from a {@code Capability}'s attributes.
 * 
 * @see NamedData
 * @since 2.1
 */
public final class CapabilityLicFeatureProvider extends CapabilityLicFeatureInfo {

	public CapabilityLicFeatureProvider(Requirement requirement) {
		super(requirement.feature().provider());
	}

	public CapabilityLicFeatureProvider(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "provider"; //$NON-NLS-1$
	}

}
