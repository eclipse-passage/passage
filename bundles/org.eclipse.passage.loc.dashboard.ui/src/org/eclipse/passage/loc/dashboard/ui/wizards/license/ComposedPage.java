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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;

public final class ComposedPage implements Supplier<LicenseDataPage> {

	private final List<Field<?>> units = new ArrayList<>();
	private final MandatoryService context;
	private final LabelProvider labels;
	private final String name;
	private LicenseDataPage page;

	public ComposedPage(String name, IEclipseContext context) {
		this.name = name;
		this.context = new MandatoryEclipseContext(context);
		this.labels = new DomainRegistryLabelProvider();
	}

	public Supplier<Optional<LicensePlanDescriptor>> withLicensePlan(Optional<LicensePlanDescriptor> plan) {
		return with(new LicensePlanField(plan, this::validatePage, labels, context));
	}

	public Supplier<Optional<UserDescriptor>> withUser(Optional<UserDescriptor> user) {
		return with(new UserField(user, this::validatePage, labels, context));
	}

	public Supplier<Optional<Collection<UserDescriptor>>> withUsers() {
		return with(new UsersField(Collections.emptyList(), this::validatePage, labels, context));
	}

	public Supplier<Optional<ProductVersionDescriptor>> withProductVersion(Optional<ProductVersionDescriptor> product) {
		return with(new ProductVersionField(product, this::validatePage, labels, context));
	}

	public Supplier<Optional<List<LocalDate>>> withPeriod() {
		return with(new ActivePeriodField(this::validatePage, labels, context));
	}

	private <T> Supplier<Optional<T>> with(Field<T> unit) {
		units.add(unit);
		return unit::data;
	}

	@Override
	public LicenseDataPage get() {
		page = new LicenseDataPage(name, units);
		return page;
	}

	private void validatePage() {
		page.validate();
	}

}
