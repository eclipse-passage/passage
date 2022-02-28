/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.equinox.SuppliedFrameworkAware;
import org.eclipse.passage.lic.internal.base.access.Libraries;
import org.eclipse.passage.lic.internal.equinox.access.AgreementAcceptanceDelegate;
import org.eclipse.passage.lic.internal.equinox.access.RegisteredLibraries;
import org.eclipse.passage.lic.internal.jface.i18n.AgreementsDialogMessages;

@SuppressWarnings("restriction")
final class AgreementsWizard extends Wizard {

	private final Logger log = LogManager.getLogger(getClass());

	private final Collection<AgreementToAccept> agreements;
	private final Libraries libraries;

	AgreementsWizard(Collection<AgreementToAccept> agreements) {
		this.agreements = agreements;
		this.libraries = new Libraries(new RegisteredLibraries(), product()::get);
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
		Optional<AgreementAcceptanceService> acceptance = productAcceptanceService();
		if (!acceptance.isPresent()) {
			reportInsufficientConfiguration();
			return true;
		}
		boolean success = performAcceptance(acceptance.get());
		if (!success) {
			reportFailure();
		}
		return true;
	}

	private boolean performAcceptance(AgreementAcceptanceService root) {
		AgreementAcceptanceDelegate service = new AgreementAcceptanceDelegate(root, libraries);
		boolean success = true;
		for (AgreementToAccept agreement : agreements) {
			try {
				service.accept(agreement);
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
		MessageDialog.openError(getShell(), AgreementsDialogMessages.AgreementsWizard_failure,
				AgreementsDialogMessages.AgreementsWizard_failure_description);
	}

	private Optional<AgreementAcceptanceService> productAcceptanceService() {
		return new SuppliedFrameworkAware()
				.withFramework(framework -> framework.accessCycleConfiguration().acceptance());
	}

	private Optional<LicensedProduct> product() {
		return new SuppliedFrameworkAware().withFramework(Framework::product);
	}

}
