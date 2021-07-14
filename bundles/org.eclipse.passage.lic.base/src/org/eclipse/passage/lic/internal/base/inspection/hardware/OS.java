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

public abstract class OS extends BaseEnvironmentProperty {

	protected OS(String family) {
		super("os", family); //$NON-NLS-1$
	}

	public static final class Family extends OS {
		public Family() {
			super("family"); //$NON-NLS-1$
		}
	}

	public static final class Manufacturer extends OS {
		public Manufacturer() {
			super("manufacturer"); //$NON-NLS-1$
		}
	}

	public static final class Version extends OS {
		public Version() {
			super("version"); //$NON-NLS-1$
		}
	}

	public static final class BuildNumber extends OS {
		public BuildNumber() {
			super("buildnumber"); //$NON-NLS-1$
		}
	}
}
