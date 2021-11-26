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
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.internal.jface.i18n.AgreementsDialogMessages;

final class AgreementsWizard extends Wizard {

	private final Logger log = LogManager.getLogger(getClass());

	private final Collection<AgreementToAccept> agreements;

	AgreementsWizard(Collection<AgreementToAccept> agreements) {
		this.agreements = agreements;
		setWindowTitle(AgreementsDialogMessages.AgreementsWizard_description);
	}

	@Override
	public void addPages() {
		agreements.stream()//
				.map(AgreementPage::new)//
				.forEach(this::addPage);
	}

	@Override
	public boolean performFinish() {
		Optional<AgreementAcceptanceService> acceptance = acceptance();
		if (!acceptance.isPresent()) {
			reportInsufficientConfiguration();
		}
		boolean success = performAcceptance(acceptance.get());
		if (!success) {
			reportFailure();
		}
		return true;
	}

	private boolean performAcceptance(AgreementAcceptanceService acceptance) {
		boolean success = true;
		for (AgreementToAccept agreement : agreements) {
			try {
				acceptance.accept(() -> agreement.acceptance().content());
			} catch (Exception e) {
				success = false;
				e.printStackTrace();
				log.error(e);
			}
		}
		return success;
	}

	private void reportInsufficientConfiguration() {
		MessageDialog.openError(getShell(), AgreementsDialogMessages.AgreementsWizard_error,
				AgreementsDialogMessages.AgreementsWizard_error_description);
	}

	private void reportFailure() {
		MessageDialog.openError(getShell(), AgreementsDialogMessages.AgreementsWizard_failure, AgreementsDialogMessages.AgreementsWizard_failure_description);
	}

	private Optional<AgreementAcceptanceService> acceptance() {
		return new SuppliedFrameworkAware()
				.withFramework(framework -> framework.accessCycleConfiguration().acceptance());
	}

}
