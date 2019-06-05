/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.dashboard.ui.wizards;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.api.access.LicensingRequest;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.DashboardUiMessages;
import org.eclipse.passage.loc.licenses.ui.LicensesUi;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.users.ui.UsersUi;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class IssueLicenseRequestPage extends WizardPage {

	private final IEclipseContext context;

	private LicensePlanDescriptor licensePlanDescriptor;
	private UserDescriptor userDescriptor;
	private ProductVersionDescriptor productVersionDescriptor;

	private Date validFrom;
	private Date validUntil;

	private final ComposedAdapterFactoryProvider provider;
	private final LabelProvider labelProvider;

	protected IssueLicenseRequestPage(String pageName, IEclipseContext context) {
		super(pageName);
		this.context = context;
		this.provider = context.get(ComposedAdapterFactoryProvider.class);
		labelProvider = new DomainRegistryLabelProvider(provider.getComposedAdapterFactory());
		setTitle(DashboardUiMessages.IssueLicenseRequestPage_page_title);
		setDescription(DashboardUiMessages.IssueLicenseRequestPage_page_description);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		container.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
		createLicenseBlock(container);
		createUserBlock(container);
		createProductBlock(container);
		createDatesBlock(container);
		setPageComplete(validatePage());
		setControl(container);
		Dialog.applyDialogFont(container);
	}

	private void createLicenseBlock(Composite composite) {
		createBlock(composite, DashboardUiMessages.IssueLicenseRequestPage_license_block_label,
				t -> selectLicensePlan(t), licensePlanDescriptor);
	}

	private void createUserBlock(Composite composite) {
		createBlock(composite, DashboardUiMessages.IssueLicenseRequestPage_user_block_label, t -> selectUser(t),
				userDescriptor);
	}

	private void createProductBlock(Composite composite) {
		createBlock(composite, DashboardUiMessages.IssueLicenseRequestPage_product_block_label,
				t -> selectProductVersion(t), productVersionDescriptor);
	}

	private String selectLicensePlan(Text text) {
		Object data = text.getData();
		LicensePlanDescriptor initial = null;
		if (data instanceof LicensePlanDescriptor) {
			initial = (LicensePlanDescriptor) data;
		}
		LicenseRegistry registry = context.get(LicenseRegistry.class);
		LicensePlanDescriptor selected = LicensesUi.selectLicensePlanDescriptor(getShell(), provider, registry,
				initial);
		text.setData(selected);
		licensePlanDescriptor = selected;
		return labelProvider.getText(selected);
	}

	private String selectUser(Text text) {
		Object data = text.getData();
		UserDescriptor initial = null;
		if (data instanceof UserDescriptor) {
			initial = (UserDescriptor) data;
		}
		UserRegistry registry = context.get(UserRegistry.class);
		UserDescriptor selected = UsersUi.selectUserDescriptor(getShell(), provider, registry, initial);
		text.setData(selected);
		userDescriptor = selected;
		return labelProvider.getText(selected);
	}

	private String selectProductVersion(Text text) {
		Object data = text.getData();
		ProductVersionDescriptor initial = null;
		if (data instanceof ProductVersionDescriptor) {
			initial = (ProductVersionDescriptor) data;
		}
		ProductRegistry registry = context.get(ProductRegistry.class);
		ProductVersionDescriptor selected = ProductsUi.selectProductVersionDescriptor(getShell(), provider, registry,
				initial);
		text.setData(selected);
		productVersionDescriptor = selected;
		return labelProvider.getText(selected);
	}

	private void createBlock(Composite composite, String labelText, Function<Text, String> s, Object initial) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(labelText);
		Text text = new Text(composite, SWT.READ_ONLY);
		text.setData(initial);
		text.addModifyListener(m -> setPageComplete(validatePage()));
		text.setText(labelProvider.getText(initial));
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		Button select = new Button(composite, SWT.PUSH);
		select.setText("Select..."); //$NON-NLS-1$
		select.addSelectionListener(widgetSelectedAdapter(event -> text.setText(s.apply(text))));
	}

	private void createDatesBlock(Composite composite) {
		// TODO Auto-generated method stub

	}

	protected boolean validatePage() {
		setErrorMessage(null);
		if (licensePlanDescriptor == null) {
			setErrorMessage(DashboardUiMessages.IssueLicenseRequestPage_e_no_license_plan);
			return false;
		}
		if (userDescriptor == null) {
			setErrorMessage(DashboardUiMessages.IssueLicenseRequestPage_e_no_user);
			return false;
		}
		if (productVersionDescriptor == null) {
			setErrorMessage(DashboardUiMessages.IssueLicenseRequestPage_e_no_product_version);
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
	}

	public LicensingRequest getLicensingRequest() {
		return createLicensingRequest(userDescriptor, licensePlanDescriptor, productVersionDescriptor, validFrom,
				validUntil);
	}

	private LicensingRequest createLicensingRequest(UserDescriptor user, LicensePlanDescriptor licensePlan,
			ProductVersionDescriptor productVersion, Date from, Date until) {
		String uuid = UUID.randomUUID().toString();
		Date creationDate = new Date();
		return new LicensingRequest() {

			@Override
			public Date getValidUntil() {
				return until;
			}

			@Override
			public Date getValidFrom() {
				return from;
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
