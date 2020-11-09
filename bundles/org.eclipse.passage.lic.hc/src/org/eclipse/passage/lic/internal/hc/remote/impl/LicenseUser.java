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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import org.eclipse.passage.lic.internal.base.StringNamedData;

/**
 * <p>
 * Until proper user management is developed across both server and client, this
 * class supplies the dummy value for a user name for condition mining requests
 * </p>
 * 
 * FIXME: for development: #564815
 */
public final class LicenseUser extends StringNamedData {

	public LicenseUser(String value) {
		super(value);
	}

	@Override
	public String key() {
		return "user"; //$NON-NLS-1$
	}

}
