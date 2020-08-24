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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.function.Supplier;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public abstract class GoodIntention {

	/**
	 * Do intended action and return {@code true} if the world has been changed
	 */
	public abstract boolean paveTheWay();

	final static class Nope extends GoodIntention {

		@Override
		public boolean paveTheWay() {
			return false;
		}

	}

	final static class ImportLicense extends GoodIntention {
		private final Supplier<Shell> shell;

		ImportLicense(Supplier<Shell> shell) {
			this.shell = shell;
		}

		@Override
		public boolean paveTheWay() {
			return Window.OK == new ImportLicenseDialog(shell.get()).open();
		}
	}

	final static class RequestLicense extends GoodIntention {

		private final Supplier<Shell> shell;

		RequestLicense(Supplier<Shell> shell) {
			this.shell = shell;
		}

		@Override
		public boolean paveTheWay() {
			new EnvironmentStateDialog(shell.get()).open();
			return false;
		}
	}

}
