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
package org.eclipse.passage.lic.internal.net;

import org.eclipse.passage.lic.floating.model.api.FloatingServerConnection;
import org.eclipse.passage.lic.internal.base.StringNamedData;

public final class ServerAuthenticationType extends StringNamedData {

	public ServerAuthenticationType(String value) {
		super(value);
	}

	public ServerAuthenticationType(FloatingServerConnection server) {
		this(server.getAuthentication().getType());
	}

	@Override
	public String key() {
		return "server.auth.type"; //$NON-NLS-1$
	}

}
