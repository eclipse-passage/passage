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
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;

public final class PageFields implements Fields {

	private final List<Field<?>> units = new ArrayList<>();
	private final MandatoryService context;
	private final LabelProvider labels;
	private final Supplier<LicenseDataPage> page;

	PageFields(Supplier<LicenseDataPage> page, IEclipseContext context) {
		this.context = new MandatoryEclipseContext(context);
		this.labels = new DomainRegistryLabelProvider();
		this.page = page;
	}

	public Supplier<Optional<LicensePlan>> withLicensePlan(Optional<LicensePlan> plan) {
		return with(new LicensePlanField(plan, this::modified, labels, context));
	}

	public Supplier<Optional<User>> withUser(Optional<User> user) {
		return with(new UserField(user, this::modified, labels, context));
	}

	public Supplier<Optional<Collection<User>>> withUsers(List<User> users) {
		return with(new UsersField(users, this::modified, labels, context));
	}

	public Supplier<Optional<ProductVersion>> withProductVersion(Optional<ProductVersion> product) {
		return with(new ProductVersionField(product, this::modified, labels, context));
	}

	public Supplier<Optional<List<LocalDate>>> withPeriod() {
		return with(new ActivePeriodField(this::modified, labels, context));
	}

	public Supplier<Optional<Integer>> withDefaultCapacity() {
		return with(new CapacityField(IssueLicensePageMessages.IssueLicenseRequestPage_lbl_default_capacity,
				this::modified, labels, context));
	}

	public Supplier<Optional<Boolean>> withSwitcher(String field, boolean value) {
		return with(new SwitchField(field, value, this::modified, labels, context));
	}

	public Supplier<Optional<Integer>> withPort() {
		return with(new PortField(this::modified, labels, context));
	}

	public Supplier<Optional<String>> withIp() {
		return with(new IpAddressField(this::modified, labels, context));
	}

	private <T> Supplier<Optional<T>> with(Field<T> unit) {
		units.add(unit);
		return unit::data;
	}

	public void enable(boolean enable) {
		units.forEach(field -> field.enable(enable));
	}

	@Override
	public List<Field<?>> fields() {
		return units;
	}

	@Override
	public void modified() {
		page.get().validate();
	}

}
