/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

package org.eclipse.passage.lic.cli;

import java.util.Optional;

final class DecoratedPrompt {

	private final Interaction delegate;

	DecoratedPrompt(Interaction interaction) {
		this.delegate = interaction;
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

}
