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

final class OptionProceed extends BaseOption {

	OptionProceed(Interaction.Smart interaction) {
		super('p', //
				"Proceed", //$NON-NLS-1$
				"Proceed with the application", //$NON-NLS-1$
				interaction);
	}

	@Override
	public Decision run() {
		interaction.prompt("License coverage is sufficient, proceeding with the server launching..."); //$NON-NLS-1$
		return Decision.proceed;
	}
}