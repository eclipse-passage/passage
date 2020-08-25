/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateIsRestrictive;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateWorthAttention;
import org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages;

@SuppressWarnings("restriction")
final class CertificatSummary implements Consumer<TitleAreaDialog> {

	private final Optional<ExaminationCertificate> certificate;

	CertificatSummary(ExaminationCertificate certificate) {
		this(Optional.of(certificate));
	}

	CertificatSummary(Optional<ExaminationCertificate> certificate) {
		this.certificate = certificate;
	}

	@Override
	public void accept(TitleAreaDialog dialog) {
		if (new CertificateIsRestrictive().test(certificate)) {
			dialog.setMessage(LicenseStatusDialogMessages.WithCertificatSummary_error, IMessageProvider.ERROR);
		}
		if (new CertificateWorthAttention().test(certificate)) {
			dialog.setMessage(LicenseStatusDialogMessages.WithCertificatSummary_warning, IMessageProvider.WARNING);
		}
		dialog.setMessage(LicenseStatusDialogMessages.WithCertificatSummary_ok, IMessageProvider.NONE);
	}

}
