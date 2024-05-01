/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further evolution
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.ui;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SupplySelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Creates {@link SelectRequest} for {@link LicensePlanDescriptor} from the
 * given {@link MandatoryService}.
 * 
 * @since 0.6
 *
 */
public final class SelectLicensePlan extends SupplySelectRequest<LicensePlan> {

	public SelectLicensePlan(MandatoryService context) {
		this(context, Optional.empty());
	}

	public SelectLicensePlan(MandatoryService context, Optional<LicensePlan> selection) {
		super(context, selection);
	}

	@Override
	public SelectRequest<LicensePlan> get() {
		return new SelectRequest<>(LicensePlan.class, domain(), input(), () -> initial, appearance());
	}

	private Supplier<Iterable<LicensePlan>> input() {
		return () -> StreamSupport.stream(context.get(LicenseRegistry.class).plans().spliterator(), false)//
				.collect(Collectors.toList());
	}

	private Appearance appearance() {
		return new Appearance(LicensesUiMessages.LicensesUi_select_license_plan, //
				() -> LicensingImages.getImage(LicensesPackage.eINSTANCE.getLicensePlan().getName()), labels());
	}

	private String domain() {
		return LicensesPackage.eNAME;
	}

}
