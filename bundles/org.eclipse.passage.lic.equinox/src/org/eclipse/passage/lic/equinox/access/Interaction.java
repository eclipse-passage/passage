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
package org.eclipse.passage.lic.equinox.access;

import java.util.Optional;

public interface Interaction {

	void prompt(String information);

	void swear(Throwable thro);

	String input();

	public static final class Smart implements Interaction {
		private final Interaction delegate;

		public Smart(Interaction delegate) {
			this.delegate = delegate;
		}

		void head(String title) {
			head(title, Optional.empty());
		}

		void head(String title, String message) {
			head(title, Optional.of(message));
		}

		private void head(String title, Optional<String> message) {
			delegate.prompt("------------------------------------"); //$NON-NLS-1$
			delegate.prompt(String.format("--- %s", title)); //$NON-NLS-1$
			message.ifPresent(delegate::prompt);
			delegate.prompt("------------------------------------"); //$NON-NLS-1$
		}

		@Override
		public void prompt(String information) {
			delegate.prompt(information);
		}

		@Override
		public void swear(Throwable thro) {
			delegate.swear(thro);
		}

		@Override
		public String input() {
			return delegate.input();
		}
	}

}
