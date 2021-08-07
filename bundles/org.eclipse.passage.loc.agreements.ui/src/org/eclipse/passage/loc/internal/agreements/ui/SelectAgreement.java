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

import org.eclipse.passage.lic.agreements.AgreementDescriptor;
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
public final class SelectAgreement extends SupplySelectRequest<AgreementDescriptor> {

	public SelectAgreement(MandatoryService context) {
		super(context);
	}

	@Override
	public SelectRequest<AgreementDescriptor> get() {
		return new SelectRequest<>(AgreementDescriptor.class, domain(), input(), appearance());
	}

	private Supplier<Iterable<AgreementDescriptor>> input() {
		return () -> new ArrayList<>(context.get(AgreementRegistry.class).agreements());
	}

	private Appearance appearance() {
		return new Appearance(AgreementsUiMessages.SelectAgreement_title, //
				() -> LicensingImages.getImage(AgreementsPackage.eINSTANCE.getAgreement().getName()), labels());
	}

	private String domain() {
		return AgreementsPackage.eNAME;
	}

}
