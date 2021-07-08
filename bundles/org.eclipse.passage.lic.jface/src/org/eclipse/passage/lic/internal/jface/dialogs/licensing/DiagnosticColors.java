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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Arrays;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

final class DiagnosticColors {

	private final Color failure;
	private final Color error;
	private final Color warning;
	private final Color info;

	DiagnosticColors(Device device) {
		this.failure = new Color(device, 222, 145, 145);
		this.error = new Color(device, 233, 175, 215);
		this.warning = new Color(device, 244, 228, 186);
		this.info = new Color(device, 178, 201, 166);
	}

	Color get(boolean severe, boolean exception) {
		if (severe && exception) {
			return failure;
		} else if (severe && !exception) {
			return error;
		} else if (!severe && exception) {
			return warning;
		} else {
			return info;
		}
	}

	void dispose() {
		Arrays.asList(failure, error, warning, info)//
				.forEach(Color::dispose);
	}
}
