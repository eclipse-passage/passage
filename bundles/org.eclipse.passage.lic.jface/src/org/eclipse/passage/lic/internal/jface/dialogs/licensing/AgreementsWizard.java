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

final class AgreementsWizard extends Wizard {

	private final Logger log = LogManager.getLogger(getClass());

	private final Collection<AgreementToAccept> agreements;

	AgreementsWizard(Collection<AgreementToAccept> agreements) {
		this.agreements = agreements;
		setWindowTitle("Read and accept license agreements"); //$NON-NLS-1$
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
		MessageDialog.openError(getShell(), "License acceptance is not possible", //$NON-NLS-1$
				"The product lacks configuration, thus license acceptance cannot be performed. \n Contact the vendor."); //$NON-NLS-1$
	}

	private void reportFailure() {
		MessageDialog.openError(getShell(), "License acceptance failed", //$NON-NLS-1$
				"License acceptance process failed."); //$NON-NLS-1$
	}

	private Optional<AgreementAcceptanceService> acceptance() {
		return new SuppliedFrameworkAware()
				.withFramework(framework -> framework.accessCycleConfiguration().acceptance());
	}

}
