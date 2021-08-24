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
import org.eclipse.passage.lic.base.agreements.BaseGlobalAgreement;
import org.eclipse.passage.lic.licenses.model.api.AgreementData;

@SuppressWarnings("restriction")
public final class PAgreement implements Supplier<GlobalAgreement> {

	private final AgreementData agreement;

	public PAgreement(AgreementData agreement) {
		this.agreement = agreement;
	}

	@Override
	public GlobalAgreement get() {
		return new BaseGlobalAgreement(//
				agreement.getIdentifier(), //
				agreement.getName(), //
				agreement.getFile(), //
				agreement.getHashAlgo(), //
				agreement.getHash(), //
				agreement.getContent());
	}

}
