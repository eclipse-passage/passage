/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.access;

import java.io.IOException;

import org.eclipse.core.runtime.Platform;

public final class ConsoleInteraction implements Interaction {

	private final int stop;

	public ConsoleInteraction() {
		this.stop = stop();
	}

	@Override
	public void prompt(String information) {
		System.out.printf("%s\n", information); //$NON-NLS-1$
	}

	@Override
	public String input() {
		byte[] bytes = new byte[1024];
		int length = 0;
		try {
			for (int symbol = System.in.read(); symbol != stop; symbol = System.in.read()) {
				bytes[length++] = (byte) symbol;
			}
		} catch (IOException e) {
			swear(e);
			return e.getClass().getName();
		}
		return new String(bytes, 0, length);
	}

	@Override
	public void swear(Throwable thro) {
		thro.printStackTrace(System.err);
	}

	private int stop() {
		if (Platform.getOS().equals(Platform.OS_LINUX)) {
			return 10;
		} else if (Platform.getOS().equals(Platform.OS_MACOSX)) {
			return 10;
		} else {
			return 13;
		}
	}

}
