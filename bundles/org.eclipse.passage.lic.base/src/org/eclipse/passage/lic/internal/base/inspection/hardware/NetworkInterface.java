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
package org.eclipse.passage.lic.internal.base.inspection.hardware;

import org.eclipse.passage.lic.base.inspection.BaseEnvironmentProperty;

public abstract class NetworkInterface extends BaseEnvironmentProperty {

	protected NetworkInterface(String name) {
		super("net", name); //$NON-NLS-1$
	}

	public static final class Name extends NetworkInterface {

		public Name() {
			super("name"); //$NON-NLS-1$
		}

	}

	public static final class DisplayName extends NetworkInterface {

		public DisplayName() {
			super("displayname"); //$NON-NLS-1$
		}

	}

	public static final class MacAddress extends NetworkInterface {

		public MacAddress() {
			super("macaddress"); //$NON-NLS-1$
		}

	}

	public static final class HwAddress extends NetworkInterface {

		public HwAddress() {
			super("hardwareaddress"); //$NON-NLS-1$
		}

	}

}
