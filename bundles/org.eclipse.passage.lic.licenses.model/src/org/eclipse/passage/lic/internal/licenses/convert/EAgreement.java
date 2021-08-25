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
package org.eclipse.passage.lic.internal.licenses.convert;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.licenses.model.api.AgreementData;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

@SuppressWarnings("restriction")
public final class EAgreement implements Supplier<AgreementData> {

	private final GlobalAgreement agreement;

	public EAgreement(GlobalAgreement agreement) {
		this.agreement = agreement;
	}

	@Override
	public AgreementData get() {
		AgreementData data = LicensesFactory.eINSTANCE.createAgreementData();
		data.setIdentifier(agreement.identifier());
		data.setName(agreement.name());
		data.setFile(agreement.file());
		data.setHashAlgo(agreement.hashAlgo());
		data.setHash(agreement.hash());
		data.setContent(agreement.content());
		return data;
	}

}
