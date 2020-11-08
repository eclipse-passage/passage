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

public final class HostPort {

	private final String host;
	private final String port;

	public HostPort(String host, String port) {
		this.host = host;
		this.port = port;
	}

	public String host() {
		return host;
	}

	public String port() {
		return port;
	}

}
