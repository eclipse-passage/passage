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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.internal.api.LicensingRequest;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.ui.SelectLicensePlan;
import org.eclipse.passage.loc.internal.products.ui.SelectProduct;
import org.eclipse.passage.loc.internal.products.ui.SelectProductVersion;
import org.eclipse.passage.loc.internal.users.ui.SelectUser;
import org.eclipse.passage.loc.internal.users.ui.SelectUserOrigin;
import org.eclipse.passage.loc.internal.workbench.MandatoryEclipseContext;
import org.eclipse.passage.loc.internal.workbench.SelectInner;
import org.eclipse.passage.loc.internal.workbench.SelectRoot;
import org.eclipse.passage.loc.jface.dialogs.DateDialog;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class IssueLicenseRequestPage extends WizardPage {

	private final MandatoryService context;

	private LicensePlanDescriptor licensePlanDescriptor;
	private UserDescriptor userDescriptor;
	private ProductVersionDescriptor productVersionDescriptor;

	private LocalDate validFrom;
	private LocalDate validUntil;

	private final LabelProvider labelProvider;

	protected IssueLicenseRequestPage(String pageName, IEclipseContext context) {
		super(pageName);
		this.context = new MandatoryEclipseContext(context);
		labelProvider = new DomainRegistryLabelProvider();
		setTitle(IssueLicensePageMessages.IssueLicenseRequestPage_page_title);
		setDescription(IssueLicensePageMessages.IssueLicenseRequestPage_page_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		container.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		setControl(container);
		createLicenseBlock(container);
		createUserBlock(container);
		createProductBlock(container);
		createDatesBlock(container);
		setPageComplete(validatePage());
		Dialog.applyDialogFont(container);
	}

	private void createLicenseBlock(Composite composite) {
		createTextButtonBlock(composite, IssueLicensePageMessages.IssueLicenseRequestPage_lbl_license_plan,
				t -> selectLicensePlan(t), licensePlanDescriptor);
	}

	private void createUserBlock(Composite composite) {
		createTextButtonBlock(composite, IssueLicensePageMessages.IssueLicenseRequestPage_lbl_user, t -> selectUser(t),
				userDescriptor);
	}

	private void createProductBlock(Composite composite) {
		createTextButtonBlock(composite, IssueLicensePageMessages.IssueLicenseRequestPage_lbl_product_version,
				t -> selectProductVersion(t), productVersionDescriptor);
	}

	private void createDatesBlock(Composite composite) {
		createTextButtonBlock(composite, IssueLicensePageMessages.IssueLicenseRequestPage_lbl_valid_from,
				t -> selectFromDate(t), validFrom);
		createTextButtonBlock(composite, IssueLicensePageMessages.IssueLicenseRequestPage_lbl_valid_until,
				t -> selectUntilDate(t), validUntil);
	}

	private void createTextButtonBlock(Composite composite, String labelText, Function<Text, String> s,
			Object initial) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(labelText);
		label.setLayoutData(GridDataFactory.fillDefaults().create());
		Text text = new Text(composite, SWT.READ_ONLY);
		text.setData(initial);
		text.addModifyListener(m -> setPageComplete(validatePage()));
		text.setText(labelProvider.getText(initial));
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		Button select = new Button(composite, SWT.PUSH);
		select.setText(IssueLicensePageMessages.IssueLicenseRequestPage_btn_select_text);
		select.addSelectionListener(widgetSelectedAdapter(event -> text.setText(s.apply(text))));
		select.setLayoutData(GridDataFactory.fillDefaults().create());
	}

	private String selectLicensePlan(Text text) {
		Object data = text.getData();
		Collection<LicensePlanDescriptor> initial = new ArrayList<>();
		if (data instanceof LicensePlanDescriptor) {
			initial.add((LicensePlanDescriptor) data);
		}
		LicensePlanDescriptor selected = new SelectRoot<>(new SelectLicensePlan(context).get(), context).get()
				.orElse(null);
		text.setData(selected);
		licensePlanDescriptor = selected;
		return labelProvider.getText(selected);
	}

	private String selectUser(Text text) {
		Object data = text.getData();
		Collection<UserDescriptor> initial = new ArrayList<>();
		if (data instanceof UserDescriptor) {
			initial.add((UserDescriptor) data);
		}
		UserDescriptor selected = new SelectInner<UserDescriptor, UserOriginDescriptor>(new SelectUser(context).get(),
				new SelectUserOrigin(context).get(), context).get().orElse(null);
		text.setData(selected);
		userDescriptor = selected;
		return labelProvider.getText(selected);
	}

	private String selectProductVersion(Text text) {
		Object data = text.getData();
		Collection<ProductVersionDescriptor> initial = new ArrayList<>();
		if (data instanceof ProductVersionDescriptor) {
			initial.add((ProductVersionDescriptor) data);
		}
		ProductVersionDescriptor selected = new SelectInner<ProductVersionDescriptor, ProductDescriptor>(
				new SelectProductVersion(context).get(), new SelectProduct(context).get(), context).get().orElse(null);
		text.setData(selected);
		productVersionDescriptor = selected;
		return labelProvider.getText(selected);
	}

	private String selectFromDate(Text text) {
		LocalDate selected = selectDate(text, validFrom,
				IssueLicensePageMessages.IssueLicenseRequestPage_valid_from_title);
		validFrom = selected;
		return selected.toString();
	}

	private String selectUntilDate(Text text) {
		LocalDate selected = selectDate(text, validUntil,
				IssueLicensePageMessages.IssueLicenseRequestPage_valid_until_title);
		validUntil = selected;
		return selected.toString();
	}

	private LocalDate selectDate(Text text, LocalDate initial, String title) {
		LocalDate current = initial;
		Object data = text.getData();
		if (data instanceof LocalDate) {
			current = (LocalDate) data;
		}
		DateDialog dialog = new DateDialog(getShell(), current);
		dialog.create();
		dialog.getShell().setText(title);
		if (dialog.open() == Window.OK) {
			current = dialog.getSelected();
			text.setData(current);
		}
		return current;
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
//validate dates
		// TODO Auto-generated method stub
		return true;
	}

	public void init(LicensePlanDescriptor plan, UserDescriptor user, ProductVersionDescriptor version) {
		this.licensePlanDescriptor = plan;
		this.userDescriptor = user;
		this.productVersionDescriptor = version;
		this.validFrom = LocalDate.now();
		this.validUntil = validFrom.plusMonths(12);
	}

	public LicensingRequest getLicensingRequest() {
		return createLicensingRequest(userDescriptor, licensePlanDescriptor, productVersionDescriptor, validFrom,
				validUntil);
	}

	private LicensingRequest createLicensingRequest(UserDescriptor user, LicensePlanDescriptor licensePlan,
			ProductVersionDescriptor productVersion, LocalDate from, LocalDate until) {
		ZoneId systemDefault = ZoneId.systemDefault();
		String uuid = UUID.randomUUID().toString();
		Date creationDate = new Date();
		return new LicensingRequest() {

			@Override
			public Date getValidUntil() {
				return Date.from(until.atStartOfDay(systemDefault).toInstant());
			}

			@Override
			public Date getValidFrom() {
				return Date.from(from.atStartOfDay(systemDefault).toInstant());
			}

			@Override
			public String getUserIdentifier() {
				return user.getEmail();
			}

			@Override
			public String getUserFullName() {
				return user.getFullName();
			}

			@Override
			public String getProductVersion() {
				return productVersion.getVersion();
			}

			@Override
			public String getProductIdentifier() {
				return productVersion.getProduct().getIdentifier();
			}

			@Override
			public String getPlanIdentifier() {
				return licensePlan.getIdentifier();
			}

			@Override
			public String getIdentifier() {
				return uuid;
			}

			@Override
			public Date getCreationDate() {
				return creationDate;
			}

			@Override
			public String getConditionType() {
				return user.getPreferredConditionType();
			}

			@Override
			public String getConditionExpression() {
				return user.getPreferredConditionExpression();
			}
		};
	}
}
