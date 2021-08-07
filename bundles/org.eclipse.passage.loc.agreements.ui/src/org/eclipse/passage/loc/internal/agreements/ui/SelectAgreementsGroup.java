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
package org.eclipse.passage.loc.internal.agreements.ui;

import java.util.ArrayList;
import java.util.function.Supplier;

import org.eclipse.passage.lic.agreements.AgreementGroupDescriptor;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.ui.i18n.AgreementsUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SupplySelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Creates {@link SelectRequest} for {@link AgreementGroupDescriptor} from the
 * given {@link MandatoryService}.
 * 
 */
@SuppressWarnings("restriction")
public final class SelectAgreementsGroup extends SupplySelectRequest<AgreementGroupDescriptor> {

	public SelectAgreementsGroup(MandatoryService context) {
		super(context);
	}

	@Override
	public SelectRequest<AgreementGroupDescriptor> get() {
		return new SelectRequest<>(AgreementGroupDescriptor.class, domain(), input(), appearance());
	}

	private Supplier<Iterable<AgreementGroupDescriptor>> input() {
		return () -> new ArrayList<>(context.get(AgreementRegistry.class).groups());
	}

	private Appearance appearance() {
		return new Appearance(AgreementsUiMessages.SelectAgreementGroup_title, //
				() -> LicensingImages.getImage(AgreementsPackage.eINSTANCE.getAgreementGroup().getName()), labels());
	}

	private String domain() {
		return AgreementsPackage.eNAME;
	}

}
