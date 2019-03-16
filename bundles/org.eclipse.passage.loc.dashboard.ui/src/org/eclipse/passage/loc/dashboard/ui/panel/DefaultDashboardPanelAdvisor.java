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
import org.eclipse.passage.lic.features.registry.Features;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.licenses.registry.Licenses;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.products.registry.Products;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.lic.users.registry.Users;
import org.eclipse.passage.loc.dashboard.ui.DashboardUi;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class DefaultDashboardPanelAdvisor implements DashboardPanelAdvisor {

	private IEclipseContext context;

	private DashboardPanelBlock featureSets;
	private DashboardPanelBlock features;
	private DashboardPanelBlock featureVersions;

	private DashboardPanelBlock productLines;
	private DashboardPanelBlock products;
	private DashboardPanelBlock productVersions;
	private DashboardPanelBlock productVersionFeatures;

	private DashboardPanelBlock userOrigins;
	private DashboardPanelBlock users;

	private DashboardPanelBlock licensePacks;

	@Override
	public void init(IEclipseContext context) {
		this.context = context;
	}

	@Override
	public void createHeaderInfo(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).indent(0, 10).grab(true, false).create());
		label.setFont(JFaceResources.getBannerFont());
		label.setText("Licensing data overview");
	}

	@Override
	public void createFeatureInfo(Composite parent, FeatureRegistry featureRegistry) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		group.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).create());
		group.setText("Features");

		createLinks(group, Features.DOMAIN_NAME);

		featureSets = createFeatureSetBlock(group);
		features = createFeatureBlock(group);
		featureVersions = createFeatureVersionBlock(group);

		updateFeatureInfo(featureRegistry);
	}

	protected DashboardPanelBlock createFeatureSetBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Feature Sets:";
		EClass eClass = FeaturesPackage.eINSTANCE.getFeatureSet();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = "You have %s Feature Set(s) defined.\nUse it define the Features";
		String warning = "You have no Feature Sets defined.\nPlease create or load Feature Set definitions";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Features.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit("Select Feature Set to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createFeatureBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Features:";
		EClass eClass = FeaturesPackage.eINSTANCE.getFeature();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = "You have %s Feature(s) defined.\nUse it define the Feature Version(s)";
		String warning = "You have no Features defined.\nPlease create it for the Feature Set(s)";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Features.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit("Select Feature to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createFeatureVersionBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Feature Versions:";
		EClass eClass = FeaturesPackage.eINSTANCE.getFeatureVersion();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		String info = "You have %s Feature Version(s) defined.\nUse it define the Product Version(s)";
		String warning = "You have no Feature Versions defined.\nPlease create it for the Feature(s)";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Features.DOMAIN_NAME;
		String classifier = eClass.getName();
		block.configureEdit("Select Feature Version to edit", new SelectionAdapter() {
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
		group.setText("Products");

		createLinks(group, Products.DOMAIN_NAME);

		productLines = createProductLineBlock(group);
		products = createProductBlock(group, productRegistry);
		productVersions = createProductVersionBlock(group);
		productVersionFeatures = createProductVersionFeatureBlock(group);

		updateProductInfo(productRegistry);
	}

	protected DashboardPanelBlock createProductLineBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Product Lines:";
		Image image = getImage(LicPackage.eINSTANCE.getProductLine());
		block.createControl(parent, label, image);
		String info = "You have %s Product Line(s) defined.\nUse it define the Products";
		String warning = "You have no Product Lines defined.\nPlease create or load Product Line definitions";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getProductLine().getName();
		block.configureEdit("Select Product Line to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createProductBlock(Composite parent, ProductRegistry registry) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Products:";
		Image image = getImage(LicPackage.eINSTANCE.getProduct());
		block.createControl(parent, label, image);
		String info = "You have %s Product(s) defined.\nUse it define the Product Versions";
		String warning = "You have no Products defined.\nPlease create it for the Product Line(s)";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getProduct().getName();
		block.configureEdit("Select Product to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createProductVersionBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Product Versions:";
		Image image = getImage(LicPackage.eINSTANCE.getProductVersion());
		block.createControl(parent, label, image);
		String info = "You have %s Product Version (s) defined.\nUse it define the Product Version Features";
		String warning = "You have no Product Versions defined.\nPlease create it for the Product(s)";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getProductVersion().getName();
		block.configureEdit("Select Product Version to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createProductVersionFeatureBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Product Features:";
		Image image = getImage(LicPackage.eINSTANCE.getProductVersionFeature());
		block.createControl(parent, label, image);
		String info = "You have %s Product Version Feature(s) defined.\nUse it define License Grants";
		String warning = "You have no Product Version Features defined.\nPlease create it for the Product Verion(s)";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Products.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getProductVersionFeature().getName();
		block.configureEdit("Select Product Version Feature to edit", new SelectionAdapter() {
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
		group.setText("Users");

		createLinks(group, Users.DOMAIN_NAME);

		userOrigins = createUserOriginBlock(group);
		users = createUserBlock(group);

		updateUserInfo(userRegistry);
	}

	protected DashboardPanelBlock createUserOriginBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "User Origins:";
		Image image = getImage(LicPackage.eINSTANCE.getUserOrigin());
		block.createControl(parent, label, image);
		String info = "You have %s User Origin(s) defined.\nUse it define the Users";
		String warning = "You have no User Origins defined.\nPlease create or load User Origin definitions";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Users.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getUserOrigin().getName();
		block.configureEdit("Select User Origin to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	protected DashboardPanelBlock createUserBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "Users:";
		Image image = getImage(LicPackage.eINSTANCE.getUser());
		block.createControl(parent, label, image);
		String info = "You have %s User(s) defined.\nUse it define the License Packs";
		String warning = "You have no Users defined.\nPlease create it for the User Origin(s)";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Users.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getUser().getName();
		block.configureEdit("Select User to edit", new SelectionAdapter() {
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
		group.setText("Licenses");

		createLinks(group, Licenses.DOMAIN_NAME);

		licensePacks = createLicensePackBlock(group);
		updateLicenseInfo(licenseRegistry);
	}

	protected DashboardPanelBlock createLicensePackBlock(Composite parent) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		String label = "License Packs:";
		Image image = getImage(LicPackage.eINSTANCE.getLicensePack());
		block.createControl(parent, label, image);
		String info = "You have %s License Pack(s) defined.\nUse it define the License Grants";
		String warning = "You have no License Packs defined.\nPlease create or load License Pack definitions";
		block.setInfo(info);
		block.setWarning(warning);
		String domain = Licenses.DOMAIN_NAME;
		String classifier = LicPackage.eINSTANCE.getLicensePack().getName();
		block.configureEdit("Select License Pack to edit", new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeEditCommand(domain, classifier);
			}
		});
		return block;
	}

	@Override
	public void updateLicenseInfo(LicenseRegistry licenseRegistry) {
		licensePacks.update(licenseRegistry.getLicensePacks());
	}

	@Override
	public void createFooterInfo(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(
				GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).indent(0, 10).grab(true, false).create());
		label.setText("Licensing data summary");
	}

	protected void createLinks(Group group, String domain) {
		Link create = new Link(group, SWT.NONE);
		create.setText("<a>Create</a>");
		create.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).span(2, 1).create());
		create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeCreateCommand(domain);
			}
		});
		Link open = new Link(group, SWT.NONE);
		open.setText("<a>Load</a>");
		open.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).span(2, 1).create());
		open.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeLoadCommand(domain);
			}
		});
	}

	protected void executeCreateCommand(String domain) {
		DashboardUi.executeCreateCommand(this.context, domain);
	}

	protected void executeLoadCommand(String domain) {
		DashboardUi.executeLoadCommand(this.context, domain);
	}

	protected void executeEditCommand(String domain, String classifier) {
		DashboardUi.executeEditCommand(this.context, domain, classifier);
	}

	protected Image getImage(EClass eClass) {
		return LicensingImages.getImage(eClass.getName());
	}

	@Override
	public void dispose(IEclipseContext context) {
		this.context = null;
	}

}
