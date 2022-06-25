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

public abstract class Cpu extends BaseEnvironmentProperty {

	protected Cpu(String name) {
		super("system", name); //$NON-NLS-1$
	}

	public static final class Vendor extends Cpu {

		public Vendor() {
			super("vendor"); //$NON-NLS-1$
		}

	}

	public static final class Family extends Cpu {

		public Family() {
			super("family"); //$NON-NLS-1$
		}

	}

	public static final class Model extends Cpu {

		public Model() {
			super("model"); //$NON-NLS-1$
		}

	}

	public static final class Name extends Cpu {

		public Name() {
			super("name"); //$NON-NLS-1$
		}

	}

	public static final class Identifier extends Cpu {

		public Identifier() {
			super("identifier"); //$NON-NLS-1$
		}

	}

	public static final class ProcessorId extends Cpu {

		public ProcessorId() {
			super("processorid"); //$NON-NLS-1$
		}

	}
}
