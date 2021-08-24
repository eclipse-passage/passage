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

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.licenses.model.api.AgreementData;

@SuppressWarnings("restriction")
public final class PAgreements implements Supplier<Collection<GlobalAgreement>> {

	private final Collection<AgreementData> agreements;

	public PAgreements(Collection<AgreementData> agreements) {
		this.agreements = agreements;
	}

	@Override
	public Collection<GlobalAgreement> get() {
		return agreements.stream()//
				.map(PAgreement::new)//
				.map(PAgreement::get)//
				.collect(Collectors.toList());
	}

}