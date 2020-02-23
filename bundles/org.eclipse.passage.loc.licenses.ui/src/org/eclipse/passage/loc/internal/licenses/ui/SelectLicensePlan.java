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
package org.eclipse.passage.loc.internal.licenses.ui;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;
import org.eclipse.passage.loc.users.core.Users;

/**
 * Creates {@link SelectRequest} for {@link LicensePlanDescriptor} from the
 * given {@link IEclipseContext}.
 * 
 * @since 0.6
 *
 */
public final class SelectLicensePlan implements Supplier<SelectRequest<LicensePlanDescriptor>> {

	private final IEclipseContext context;

	public SelectLicensePlan(IEclipseContext context) {
		this.context = context;
	}

	@Override
	public SelectRequest<LicensePlanDescriptor> get() {
		return new SelectRequest<LicensePlanDescriptor>(LicensePlanDescriptor.class, domain(), input(), appearance());
	}

	private Supplier<Iterable<LicensePlanDescriptor>> input() {
		return () -> StreamSupport.stream(context.get(LicenseRegistry.class).getLicensePlans().spliterator(), false)//
				.collect(Collectors.toList());
	}

	private Appearance appearance() {
		return new Appearance(LicensesUiMessages.LicensesUi_select_license_plan, //
				() -> LicensingImages.getImage(LicensesPackage.eINSTANCE.getLicensePlan().getName()));
	}

	private String domain() {
		return Users.DOMAIN_NAME;
	}

}
