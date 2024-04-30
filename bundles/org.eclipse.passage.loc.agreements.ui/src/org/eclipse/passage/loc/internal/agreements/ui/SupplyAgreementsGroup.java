/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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
package org.eclipse.passage.loc.internal.agreements.ui;

import java.util.Optional;

import org.eclipse.passage.lic.agreements.model.api.AgreementGroup;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.eclipse.passage.loc.internal.workbench.SelectRoot;

@SuppressWarnings("restriction")
public final class SupplyAgreementsGroup implements InstanceSupply<AgreementGroup> {

	private final MandatoryService context;

	public SupplyAgreementsGroup(MandatoryService context) {
		this.context = context;
	}

	@Override
	public Optional<AgreementGroup> supply() {
		return new SelectRoot<>(new SelectAgreementsGroup(context).get(), context).get();
	}

}
