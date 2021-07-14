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
package org.eclipse.passage.lic.internal.net;

import java.util.Map;

import org.eclipse.passage.lic.base.StringNamedData;

public final class LicensingServerPort extends StringNamedData {

	public LicensingServerPort(Map<String, Object> container) {
		super(container);
	}

	public LicensingServerPort(String value) {
		super(value);
	}

	public LicensingServerPort(int value) {
		super(Integer.toString(value));
	}

	@Override
	public String entrySeparator() {
		return "\n"; //$NON-NLS-1$
	}

	@Override
	public String key() {
		return "licensing.server.port"; //$NON-NLS-1$
	}

}
