/*******************************************************************************
 * Copyright (c) 2026 ArSysOp
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
package org.eclipse.passage.lic.internal.base.inspection;

import org.eclipse.passage.lic.base.inspection.BaseEnvironmentProperty;

public abstract class Trusted extends BaseEnvironmentProperty {

	protected Trusted(String name) {
		super("trusted", name); //$NON-NLS-1$
	}

	public static final class Scope extends Trusted {

		public Scope() {
			super("scope"); //$NON-NLS-1$
		}

	}

}
