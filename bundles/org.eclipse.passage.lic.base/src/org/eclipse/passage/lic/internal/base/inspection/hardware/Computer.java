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

public abstract class Computer extends BaseEnvironmentProperty {

	protected Computer(String name) {
		super("system", name); //$NON-NLS-1$
	}

	public static final class Manufacturer extends Computer {

		public Manufacturer() {
			super("manufacturer"); //$NON-NLS-1$
		}

	}

	public static final class Model extends Computer {

		public Model() {
			super("model"); //$NON-NLS-1$
		}

	}

	public static final class Serial extends Computer {

		public Serial() {
			super("serialnumber"); //$NON-NLS-1$
		}

	}
}
