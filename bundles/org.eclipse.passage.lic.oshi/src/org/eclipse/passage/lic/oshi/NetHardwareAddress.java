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
package org.eclipse.passage.lic.oshi;

import java.net.SocketException;
import java.util.function.Supplier;

import oshi.hardware.NetworkIF;

final class NetHardwareAddress implements Supplier<String> {

	private final NetworkIF net;

	NetHardwareAddress(NetworkIF net) {
		this.net = net;
	}

	@Override
	public String get() {
		byte[] address;
		try {
			address = net.getNetworkInterface().getHardwareAddress();
		} catch (SocketException e) {
			return null; // mimic null: supports OSHI reading policy
		}
		return enlisted(address);
	}

	private String enlisted(byte[] bytes) {
		StringBuilder out = new StringBuilder();
		for (int one : bytes) {
			out.append(one);
		}
		return out.toString();
	}

}
