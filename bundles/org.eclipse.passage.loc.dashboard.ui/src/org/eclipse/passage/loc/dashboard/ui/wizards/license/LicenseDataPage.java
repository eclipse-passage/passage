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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public final class LicenseDataPage extends WizardPage {

	private final MandatoryService context;
	private final LabelProvider labels;
	private final List<ChosenLicenseData<?>> units = new ArrayList<>();

	protected LicenseDataPage(String pageName, IEclipseContext context) {
		super(pageName);
		this.context = new MandatoryEclipseContext(context);
		labels = new DomainRegistryLabelProvider();
		setTitle(IssueLicensePageMessages.IssueLicenseRequestPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicenseRequestPage_page_description);
	}

	public <T> LicenseDataPage with(ChosenLicenseData<T> unit) {
		units.add(unit);
		return this;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = container(parent);
		setControl(container);
		units.forEach(unit -> unit.installControll(container));
		Dialog.applyDialogFont(container);
	}

	private Composite container(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		container.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		return container;
	}

	protected boolean validatePage() {
		setErrorMessage(null);
		if (licensePlanDescriptor == null) {
			setErrorMessage(IssueLicensePageMessages.IssueLicenseRequestPage_e_no_license_plan);
			return false;
		}
		if (userDescriptor == null) {
			setErrorMessage(IssueLicensePageMessages.IssueLicenseRequestPage_e_no_user);
			return false;
		}
		if (productVersionDescriptor == null) {
			setErrorMessage(IssueLicensePageMessages.IssueLicenseRequestPage_e_no_product_version);
			return false;
		}
		// FIXME: validate dates
		return true;
	}

	public void init(LicensePlanDescriptor plan, UserDescriptor user, ProductVersionDescriptor version) {
		this.licensePlanDescriptor = plan;
		this.userDescriptor = user;
		this.productVersionDescriptor = version;
		this.validFrom = LocalDate.now();
		this.validUntil = validFrom.plusMonths(12);
	}

}
