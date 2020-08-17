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

		@Override
		public boolean paveTheWay() {
			// expose request-license dialog facilitated with environment inspection
			return false;
		}
	}

}
