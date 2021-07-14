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

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;
import org.eclipse.passage.lic.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages;

final class CertificateSummary implements Consumer<TitleAreaDialog> {

	private final Optional<ExaminationCertificate> certificate;

	CertificateSummary(ExaminationCertificate certificate) {
		this(Optional.of(certificate));
	}

	CertificateSummary(Optional<ExaminationCertificate> certificate) {
		this.certificate = certificate;
	}

	@Override
	public void accept(TitleAreaDialog dialog) {
		if (new CertificateIsRestrictive().test(certificate)) {
			dialog.setMessage(LicenseStatusDialogMessages.WithCertificatSummary_error, IMessageProvider.ERROR);
			return;
		}
		if (new CertificateWorthAttention().test(certificate)) {
			dialog.setMessage(LicenseStatusDialogMessages.WithCertificatSummary_warning, IMessageProvider.WARNING);
			return;
		}
		dialog.setMessage(LicenseStatusDialogMessages.WithCertificatSummary_ok, IMessageProvider.NONE);
	}

}
