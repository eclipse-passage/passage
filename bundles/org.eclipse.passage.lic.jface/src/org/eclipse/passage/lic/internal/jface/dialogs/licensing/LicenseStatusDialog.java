/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.swt.widgets.Shell;

public final class LicenseStatusDialog extends BaseLicenseStatusDialog {

	public LicenseStatusDialog(Shell shell, ExaminationCertificate certificate, Diagnostic diagnostic) {
		super(shell, certificate, diagnostic);
	}

	@Override
	protected GoodIntention requestLicenseIntention() {
		return new GoodIntention.RequestLicense(this::getShell);
	}

	@Override
	protected GoodIntention importLicenseIntention() {
		return new GoodIntention.ImportLicense(this::getShell);
	}

	@Override
	protected GoodIntention diagnoseIntention() {
		return new GoodIntention.Diagnose(this::getShell, diagnostic);
	}

	@Override
	protected GoodIntention exposeLicenseAgreementsIntention(Collection<AgreementToAccept> agreements) {
		return new GoodIntention.ExposeLicenseAgreements(this::getShell, agreements);
	}

}
