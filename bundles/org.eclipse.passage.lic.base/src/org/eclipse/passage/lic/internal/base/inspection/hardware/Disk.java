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

public abstract class Disk extends BaseEnvironmentProperty {

	protected Disk(String name) {
		super("hwdisk", name); //$NON-NLS-1$
	}

	public static final class Name extends Disk {

		public Name() {
			super("name"); //$NON-NLS-1$
		}

	}

	public static final class Model extends Disk {

		public Model() {
			super("model"); //$NON-NLS-1$
		}

	}

	public static final class Serial extends Disk {

		public Serial() {
			super("serial"); //$NON-NLS-1$
		}

	}
}
