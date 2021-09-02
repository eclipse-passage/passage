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

final class OptionImport extends BaseOption {

	private final Interaction interaction;

	public OptionImport(Interaction interaction) {
		super('i', //
				"Import", //$NON-NLS-1$
				"Import a license for the application"); //$NON-NLS-1$
		this.interaction = interaction;
	}

	@Override
	public Decision run() {// TODO
		interaction.prompt("do import"); //$NON-NLS-1$
		return Decision.reassess;
	}

}
