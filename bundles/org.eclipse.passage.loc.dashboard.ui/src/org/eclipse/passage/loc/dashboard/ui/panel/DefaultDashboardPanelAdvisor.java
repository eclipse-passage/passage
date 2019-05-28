/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.panel;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.features.registry.FeatureRegistry;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.loc.dashboard.ui.DashboardUi;
import org.eclipse.passage.loc.features.core.Features;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.DashboardUiMessages;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.eclipse.passage.loc.products.core.Products;
import org.eclipse.passage.loc.users.core.Users;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class DefaultDashboardPanelAdvisor implements DashboardPanelAdvisor {

	private IEclipseContext eclipseContext;

	private DashboardPanelBlock featureSets;
	private DashboardPanelBlock features;
	private DashboardPanelBlock featureVersions;

	private DashboardPanelBlock productLines;
	private DashboardPanelBlock products;
	private DashboardPanelBlock productVersions;
	private DashboardPanelBlock productVersionFeatures;

	private DashboardPanelBlock userOrigins;
	private DashboardPanelBlock users;

	private DashboardPanelBlock licensePlans;

	@Override
	public void init(IEclipseContext context) {
		this.eclipseContext = context;
	}

	@Override
	public void createHeaderInfo(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).indent(0, 10).grab(true, false).create());
		label.setFont(JFaceResources.getBannerFont());
		label.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_overview);
	}

	@Override
	public void createFeatureInfo(Composite parent, FeatureRegistry featureRegistry) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		group.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).create());
		group.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_group);

		createLinks(group, Features.DOMAIN_NAME);

		featureSets = createFeatureSetBlock(group);
		features = createFeatureBlock(group);
		featureVersions = createFeatureVersionBlock(group);

		updateFeatureInfo(featureRegistry);
	}

	protected DashboardPanelBlock createFeatureSetBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_title;
		EClass eClass = FeaturesPackage.eINSTANCE.getFeatureSet();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Features.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createFeatureBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_title;
		EClass eClass = FeaturesPackage.eINSTANCE.getFeature();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Features.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createFeatureVersionBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_title;
		EClass eClass = FeaturesPackage.eINSTANCE.getFeatureVersion();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Features.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_edit,
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						executeEditCommand(domain, classifier);
					}
				});
		return block;
	}

	@Override
	public void updateFeatureInfo(FeatureRegistry featureRegistry) {
		featureSets.update(featureRegistry.getFeatureSets());
		features.update(featureRegistry.getFeatures());
		featureVersions.update(featureRegistry.getFeatureVersions());
	}

	@Override
	public void createProductInfo(Composite parent, ProductRegistry productRegistry) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		group.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).create());
		group.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_product_group);

		createLinks(group, Products.DOMAIN_NAME);

		productLines = createProductLineBlock(group);
		products = createProductBlock(group, productRegistry);
		productVersions = createProductVersionBlock(group);
		productVersionFeatures = createProductVersionFeatureBlock(group);

		updateProductInfo(productRegistry);
	}

	protected DashboardPanelBlock createProductLineBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_title;
		EClass eClass = ProductsPackage.eINSTANCE.getProductLine();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createProductBlock(Composite parent, ProductRegistry registry) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_title;
		EClass eClass = ProductsPackage.eINSTANCE.getProduct();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_product_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createProductVersionBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_title;
		EClass eClass = ProductsPackage.eINSTANCE.getProductVersion();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_edit,
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						executeEditCommand(domain, classifier);
					}
				});
		return block;
	}

	protected DashboardPanelBlock createProductVersionFeatureBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_title;
		EClass eClass = ProductsPackage.eINSTANCE.getProductVersionFeature();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_edit,
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						executeEditCommand(domain, classifier);
					}
				});
		return block;
	}

	@Override
	public void updateProductInfo(ProductRegistry productRegistry) {
		productLines.update(productRegistry.getProductLines());
		products.update(productRegistry.getProducts());
		productVersions.update(productRegistry.getProductVersions());
		productVersionFeatures.update(productRegistry.getProductVersionFeatures());
	}

	@Override
	public void createUserInfo(Composite parent, UserRegistry userRegistry) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		group.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).create());
		group.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_user_group);

		createLinks(group, Users.DOMAIN_NAME);

		userOrigins = createUserOriginBlock(group);
		users = createUserBlock(group);

		updateUserInfo(userRegistry);
	}

	protected DashboardPanelBlock createUserOriginBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_title;
		EClass eClass = UsersPackage.eINSTANCE.getUserOrigin();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Users.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createUserBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_title;
		EClass eClass = UsersPackage.eINSTANCE.getUser();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Users.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_user_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	@Override
	public void updateUserInfo(UserRegistry userRegistry) {
		userOrigins.update(userRegistry.getUserOrigins());
		users.update(userRegistry.getUsers());
	}

	@Override
	public void createLicenseInfo(Composite parent, LicenseRegistry licenseRegistry) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		group.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).create());
		group.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_license_group);

		createLinks(group, Licenses.DOMAIN_NAME);

		licensePlans = createLicensePlanBlock(group);
		updateLicenseInfo(licenseRegistry);
	}

	protected DashboardPanelBlock createLicensePlanBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_title;
		EClass eClass = LicensesPackage.eINSTANCE.getLicensePlan();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_warning;
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Licenses.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit(DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	@Override
	public void updateLicenseInfo(LicenseRegistry licenseRegistry) {
		licensePlans.update(licenseRegistry.getLicensePlans());
	}

	@Override
	public void createFooterInfo(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).indent(0, 10).grab(true, false).create());
		label.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_summary);
	}

	protected void createLinks(Group group, String domain) {
		Link create = new Link(group, SWT.NONE);
		create.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_create_link);
		create.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).span(2, 1).create());
		create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeCreateCommand(domain);
			}
		});
		Link open = new Link(group, SWT.NONE);
		open.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_load_link);
		open.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).span(2, 1).create());
		open.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeLoadCommand(domain);
			}
		});
	}

	protected void executeCreateCommand(String domain) {
		DashboardUi.executeCreateCommand(this.eclipseContext, domain);
	}

	protected void executeLoadCommand(String domain) {
		DashboardUi.executeLoadCommand(this.eclipseContext, domain);
	}

	protected void executeEditCommand(String domain, String classifier) {
		DashboardUi.executeEditCommand(this.eclipseContext, domain, classifier);
	}

	protected Image getImage(EClass eClass) {
		return LicensingImages.getImage(eClass.getName());
	}

	@Override
	public void dispose(IEclipseContext context) {
		this.eclipseContext = null;
	}

}
