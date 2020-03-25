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

import java.util.Map;

import org.eclipse.passage.lic.internal.base.StringNamedData;

@SuppressWarnings("restriction")
public final class CapabilityLicFeatureId extends StringNamedData {

	public CapabilityLicFeatureId(String identifier) {
		super(identifier);
	}

	public CapabilityLicFeatureId(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "licensing.feature"; //$NON-NLS-1$
	}

	@Override
	public String printed(String value) {
		return "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
