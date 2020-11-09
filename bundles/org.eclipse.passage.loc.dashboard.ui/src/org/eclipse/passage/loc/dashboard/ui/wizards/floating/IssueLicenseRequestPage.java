/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.ComposedPage;
import org.eclipse.passage.loc.dashboard.ui.wizards.license.PageFields;
import org.eclipse.passage.loc.internal.api.FloatingLicenseRequest;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.core.request.FloatingLicenseData;

public final class IssueLicenseRequestPage implements Supplier<IWizardPage> {

	private final Supplier<Optional<LicensePlanDescriptor>> plan;
	private final Supplier<Optional<Collection<UserDescriptor>>> users;
	private final Supplier<Optional<ProductVersionDescriptor>> product;
	private final Supplier<Optional<List<LocalDate>>> period;
	private final Supplier<Optional<Integer>> capacity;
	private final ComposedPage page;

	IssueLicenseRequestPage(IEclipseContext context, FloatingDataPack initial) {
		page = new ComposedPage(//
				IssueLicenseRequestPage.class.getSimpleName(), //
				IssueLicensePageMessages.IssueLicenseRequestPage_page_description, //
				context);
		PageFields units = page.withBlock();
		plan = units.withLicensePlan(initial.plan());
		users = units.withUsers();
		product = units.withProductVersion(initial.product());
		period = units.withPeriod();
		capacity = units.withDefaultCapacity();
	}

	@Override
	public IWizardPage get() {
		return page.get();
	}

	FloatingLicenseRequest request() {
		return new FloatingLicenseData(//
				() -> users.get().get(), //
				() -> plan.get().get(), //
				() -> product.get().get(), //
				() -> from(), //
				() -> until(), //
				() -> capacity.get().get());
	}

	private LocalDate from() {
		return period.get().get().get(0);
	}

	private LocalDate until() {
		return period.get().get().get(1);
	}

}
