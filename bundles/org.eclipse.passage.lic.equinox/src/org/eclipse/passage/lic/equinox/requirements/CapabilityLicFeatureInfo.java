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

import org.eclipse.passage.lic.base.NamedData;
import org.eclipse.passage.lic.base.StringNamedData;

/**
 * <p>
 * Hiding actual name of an attribute for a feature under licensing, instead of
 * sharing it
 * </p>
 * <ul>
 * <li>facilitates reading it's value from a {@code Capability}'s attributes,
 * and</li>
 * <li>provides writing <i>name-value</i> pair to a selected target</li>
 * </ul>
 * 
 * @see NamedData
 * @see NamedData.Writable
 */
abstract class CapabilityLicFeatureInfo extends StringNamedData {

	public CapabilityLicFeatureInfo(String value) {
		super(value);
	}

	public CapabilityLicFeatureInfo(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String printed(String value) {
		return "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public String key() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String keyValueSeparator() {
		return "="; //$NON-NLS-1$
	}

	@Override
	public String entrySeparator() {
		return ";"; //$NON-NLS-1$
	}

}
