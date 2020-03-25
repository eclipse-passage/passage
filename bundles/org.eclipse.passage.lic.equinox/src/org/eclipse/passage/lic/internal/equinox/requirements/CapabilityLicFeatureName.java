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
public final class CapabilityLicFeatureName extends StringNamedData {

	public CapabilityLicFeatureName(String version) {
		super(version);
	}

	public CapabilityLicFeatureName(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "name"; //$NON-NLS-1$
	}

}
