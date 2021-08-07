/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.panel;

import java.util.Collections;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.internal.e4.core.commands.ExecuteCommand;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.dashboard.ui.DashboardUi;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.DashboardUiMessages;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
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

	private DashboardPanelBlock agreementsGroups;
	private DashboardPanelBlock agreements;

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
		createLinks(group, FeaturesPackage.eNAME);
		featureSets = createFeatureSetBlock(group);
		features = createFeatureBlock(group);
		featureVersions = createFeatureVersionBlock(group);
		updateFeatureInfo(featureRegistry);
	}

	protected DashboardPanelBlock createFeatureSetBlock(Composite parent) {
		String domain = FeaturesPackage.eNAME;
		EClass eClass = FeaturesPackage.eINSTANCE.getFeatureSet();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_set_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createFeatureBlock(Composite parent) {
		String domain = FeaturesPackage.eNAME;
		EClass eClass = FeaturesPackage.eINSTANCE.getFeature();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createFeatureVersionBlock(Composite parent) {
		String domain = FeaturesPackage.eNAME;
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_title;
		EClass eClass = FeaturesPackage.eINSTANCE.getFeatureVersion();
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_feature_version_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
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
		createLinks(group, ProductsPackage.eNAME);
		productLines = createProductLineBlock(group);
		products = createProductBlock(group);
		productVersions = createProductVersionBlock(group);
		productVersionFeatures = createProductVersionFeatureBlock(group);
		updateProductInfo(productRegistry);
	}

	protected DashboardPanelBlock createProductLineBlock(Composite parent) {
		String domain = ProductsPackage.eNAME;
		EClass eClass = ProductsPackage.eINSTANCE.getProductLine();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_line_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createProductBlock(Composite parent) {
		String domain = ProductsPackage.eNAME;
		EClass eClass = ProductsPackage.eINSTANCE.getProduct();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createProductVersionBlock(Composite parent) {
		String domain = ProductsPackage.eNAME;
		EClass eClass = ProductsPackage.eINSTANCE.getProductVersion();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_version_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createProductVersionFeatureBlock(Composite parent) {
		String domain = ProductsPackage.eNAME;
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_title;
		EClass eClass = ProductsPackage.eINSTANCE.getProductVersionFeature();
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_product_feature_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
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
		createLinks(group, UsersPackage.eNAME);
		userOrigins = createUserOriginBlock(group);
		users = createUserBlock(group);
		updateUserInfo(userRegistry);
	}

	protected DashboardPanelBlock createUserOriginBlock(Composite parent) {
		String domain = UsersPackage.eNAME;
		EClass eClass = UsersPackage.eINSTANCE.getUserOrigin();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_origin_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createUserBlock(Composite parent) {
		String domain = UsersPackage.eNAME;
		EClass eClass = UsersPackage.eINSTANCE.getUser();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_user_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
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
		createLinks(group, LicensesPackage.eNAME);
		licensePlans = createLicensePlanBlock(group);
		updateLicenseInfo(licenseRegistry);
	}

	protected DashboardPanelBlock createLicensePlanBlock(Composite parent) {
		String domain = LicensesPackage.eNAME;
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_title;
		EClass eClass = LicensesPackage.eINSTANCE.getLicensePlan();
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_license_plan_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createBlock(Composite parent, String domain, EClass eClass, String label, String info,
			String warning, String show) {
		DashboardPanelBlock block = new DashboardPanelBlock();
		Image image = getImage(eClass);
		block.createControl(parent, label, image);
		block.setInfo(info);
		block.setWarning(warning);
		String classifier = eClass.getName();
		block.configureShow(show, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				executeShowCommand(domain, classifier);
			}
		});
		return block;
	}

	@Override
	public void updateLicenseInfo(LicenseRegistry licenseRegistry) {
		licensePlans.update(licenseRegistry.getLicensePlans());
	}

	@Override
	public void createAgreementInfo(Composite parent, AgreementRegistry registry) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		group.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).create());
		group.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_group);
		createLinks(group, AgreementsPackage.eNAME);
		agreementsGroups = createAgreementsGroupBlock(group);
		agreements = createAgreementsBlock(group);
		updateAgreementInfo(registry);
	}

	@Override
	public void updateAgreementInfo(AgreementRegistry registry) {
		agreementsGroups.update(registry.groups());
		agreements.update(registry.agreements());
	}

	protected DashboardPanelBlock createAgreementsGroupBlock(Composite parent) {
		String domain = AgreementsPackage.eNAME;
		EClass eClass = AgreementsPackage.eINSTANCE.getAgreementGroup();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_group_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_group_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_group_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_group_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	protected DashboardPanelBlock createAgreementsBlock(Composite parent) {
		String domain = AgreementsPackage.eNAME;
		EClass eClass = AgreementsPackage.eINSTANCE.getAgreement();
		String label = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_title;
		String info = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_info;
		String warning = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_warning;
		String show = DashboardUiMessages.DefaultDashboardPanelAdvisor_agreement_show;
		return createBlock(parent, domain, eClass, label, info, warning, show);
	}

	@Override
	public void createFooterInfo(Composite parent) {
		GridDataFactory gdf = GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.TOP).indent(0, 10).grab(true, false);
		appendSummary(parent, gdf);
		appendIssueLicenseButton(parent, gdf, //
				DashboardUiMessages.DefaultDashboardPanelAdvisor_btn_issue_personal_text, //
				DashboardUiMessages.DefaultDashboardPanelAdvisor_btn_issue_personal_description, //
				this::executeIssueLicenseCommand);
		appendIssueLicenseButton(parent, gdf, //
				DashboardUiMessages.DefaultDashboardPanelAdvisor_btn_issue_floating_text, //
				DashboardUiMessages.DefaultDashboardPanelAdvisor_btn_issue_floating_description, //
				this::executeIssueFloatingLicenseCommand);
	}

	private void appendSummary(Composite parent, GridDataFactory gdf) {
		Label summary = new Label(parent, SWT.NONE);
		summary.setLayoutData(gdf.create());
		summary.setText(DashboardUiMessages.DefaultDashboardPanelAdvisor_summary);
	}

	private void appendIssueLicenseButton(Composite parent, GridDataFactory gdf, //
			String text, String tooltip, Runnable issuing) {
		Button issue = new Button(parent, SWT.PUSH);
		issue.setImage(LicensingImages.getImage(LicensesPackage.eINSTANCE.getPersonalLicensePack().getName()));
		issue.setLayoutData(gdf.create());
		issue.setText(text);
		issue.setToolTipText(tooltip);
		issue.addSelectionListener(SelectionListener.widgetSelectedAdapter(c -> issuing.run()));
	}

	protected void executeIssueLicenseCommand() {
		new ExecuteCommand(DashboardUi.COMMAND_ISSUE_LICENSE, eclipseContext).apply(Collections.emptyMap());
	}

	protected void executeIssueFloatingLicenseCommand() {
		new ExecuteCommand(DashboardUi.COMMAND_ISSUE_FLOATING_LICENSE, eclipseContext).apply(Collections.emptyMap());
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

	protected void executeShowCommand(String domain, String classifier) {
		DashboardUi.executeShowCommand(this.eclipseContext, domain, classifier);
	}

	protected Image getImage(EClass eClass) {
		return LicensingImages.getImage(eClass.getName());
	}

	@Override
	public void dispose(IEclipseContext context) {
		this.eclipseContext = null;
	}

}
