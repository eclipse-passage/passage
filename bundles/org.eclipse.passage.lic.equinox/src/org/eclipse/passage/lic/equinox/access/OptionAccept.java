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

import java.util.Collection;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;

final class OptionAccept extends BaseOption {

	private final Collection<AgreementToAccept> agreements;

	public OptionAccept(Interaction.Smart interaction, Collection<AgreementToAccept> agreements) {
		super('a', //
				"Accept", //$NON-NLS-1$
				"Read and accept license agreements", //$NON-NLS-1$
				interaction);
		this.agreements = agreements;
	}

	@Override
	public Decision run() {// TODO
		interaction.head("accept license agreements"); //$NON-NLS-1$
		return Decision.reassess;
	}

}
