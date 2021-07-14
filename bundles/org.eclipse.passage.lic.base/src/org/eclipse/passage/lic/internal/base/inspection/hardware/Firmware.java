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

public abstract class Firmware extends BaseEnvironmentProperty {

	protected Firmware(String name) {
		super("firmware", name); //$NON-NLS-1$
	}

	public static final class Manufacturer extends Firmware {

		public Manufacturer() {
			super("manufacturer"); //$NON-NLS-1$
		}

	}

	public static final class Version extends Firmware {

		public Version() {
			super("version"); //$NON-NLS-1$
		}

	}

	public static final class ReleaseDate extends Firmware {

		public ReleaseDate() {
			super("releasedate"); //$NON-NLS-1$
		}

	}

	public static final class Name extends Firmware {

		public Name() {
			super("name"); //$NON-NLS-1$
		}

	}

	public static final class Description extends Firmware {

		public Description() {
			super("description"); //$NON-NLS-1$
		}

	}
}
