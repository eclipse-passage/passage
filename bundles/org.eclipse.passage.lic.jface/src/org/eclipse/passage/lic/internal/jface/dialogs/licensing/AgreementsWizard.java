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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Collection;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.lic.api.restrictions.AgreementToAccept;

final class AgreementsWizard extends Wizard {

	private final Collection<AgreementToAccept> agreements;

	AgreementsWizard(Collection<AgreementToAccept> agreements) {
		this.agreements = agreements;
	}

	@Override
	public void addPages() {
		agreements.stream()//
				.map(AgreementPage::new)//
				.forEach(this::addPage);
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
