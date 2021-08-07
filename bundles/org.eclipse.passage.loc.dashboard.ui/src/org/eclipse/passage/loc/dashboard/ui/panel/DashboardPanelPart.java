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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.agreements.AgreementGroupDescriptor;
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.FeatureVersionDescriptor;
import org.eclipse.passage.lic.internal.agreements.model.AgreementsClassMetadata;
import org.eclipse.passage.lic.internal.features.model.FeaturesClassMetadata;
import org.eclipse.passage.lic.internal.licenses.model.LicensesClassMetadata;
import org.eclipse.passage.lic.internal.products.model.ProductsClassMetadata;
import org.eclipse.passage.lic.internal.users.model.UsersClassMetadata;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistryEvents;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistryEvents;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistryEvents;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistryEvents;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistryEvents;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("unused")
public class DashboardPanelPart {

	private final FeatureRegistry features;
	private final ProductRegistry products;
	private final UserRegistry users;
	private final AgreementRegistry agreements;
	private final LicenseRegistry licenses;
	private final DashboardPanelAdvisor dashboard;

	@Inject
	public DashboardPanelPart(IEclipseContext context) {
		this.features = context.get(FeatureRegistry.class);
		this.products = context.get(ProductRegistry.class);
		this.users = context.get(UserRegistry.class);
		this.agreements = context.get(AgreementRegistry.class);
		this.licenses = context.get(LicenseRegistry.class);
		this.dashboard = dashboard(context);
	}

	private DashboardPanelAdvisor dashboard(IEclipseContext context) {
		DashboardPanelAdvisor advisor = context.get(DashboardPanelAdvisor.class);
		if (advisor == null) {
			advisor = new DefaultDashboardPanelAdvisor();
		}
		return advisor;
	}

	@PostConstruct
	public void postConstruct(Composite parent, IEclipseContext context) {
		dashboard.init(context);
		Composite area = new Composite(parent, SWT.NONE);
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setLayout(GridLayoutFactory.swtDefaults().create());
		createHeaderInfo(area);
		createFeatureInfo(area);
		createProductInfo(area);
		createUserInfo(area);
		createAgreementInfo(area);
		createLicenseInfo(area);
		createFooterInfo(area);
		// FIXME: replace this with OSGi component registration
		ComposableClassMetadata metadata = EclipseContextFactory
				.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext())
				.get(ComposableClassMetadata.class);
		metadata.consider(new FeaturesClassMetadata());
		metadata.consider(new ProductsClassMetadata());
		metadata.consider(new UsersClassMetadata());
		metadata.consider(new AgreementsClassMetadata());
		metadata.consider(new LicensesClassMetadata());
	}

	protected void createHeaderInfo(Composite parent) {
		dashboard.createHeaderInfo(parent);
	}

	protected void createFeatureInfo(Composite parent) {
		dashboard.createFeatureInfo(parent, features);
	}

	protected void createProductInfo(Composite parent) {
		dashboard.createProductInfo(parent, products);
	}

	protected void createUserInfo(Composite parent) {
		dashboard.createUserInfo(parent, users);
	}

	protected void createAgreementInfo(Composite parent) {
		dashboard.createAgreementInfo(parent, agreements);
	}

	protected void createLicenseInfo(Composite parent) {
		dashboard.createLicenseInfo(parent, licenses);
	}

	protected void createFooterInfo(Composite parent) {
		dashboard.createFooterInfo(parent);
	}

	@Inject
	@Optional
	public void createdFeatureSet(@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_CREATE) FeatureSetDescriptor input) {
		dashboard.updateFeatureInfo(features);
	}

	@Inject
	@Optional
	public void deletedFeatureSet(@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_DELETE) FeatureSetDescriptor input) {
		dashboard.updateFeatureInfo(features);
	}

	@Inject
	@Optional
	public void createdFeature(@UIEventTopic(FeatureRegistryEvents.FEATURE_CREATE) FeatureDescriptor input) {
		dashboard.updateFeatureInfo(features);
	}

	@Inject
	@Optional
	public void deletedFeature(@UIEventTopic(FeatureRegistryEvents.FEATURE_DELETE) FeatureDescriptor input) {
		dashboard.updateFeatureInfo(features);
	}

	@Inject
	@Optional
	public void createdFeatureVersion(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_VERSION_CREATE) FeatureVersionDescriptor input) {
		dashboard.updateFeatureInfo(features);
	}

	@Inject
	@Optional
	public void deletedFeatureVersion(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_VERSION_DELETE) FeatureVersionDescriptor input) {
		dashboard.updateFeatureInfo(features);
	}

	@Inject
	@Optional
	public void createdProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_CREATE) ProductLineDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void deletedProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_DELETE) ProductLineDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void createdProduct(@UIEventTopic(ProductRegistryEvents.PRODUCT_CREATE) ProductDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void deletedProduct(@UIEventTopic(ProductRegistryEvents.PRODUCT_DELETE) ProductDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void createdProductVersion(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_CREATE) ProductVersionDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void deletedProductVersion(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_DELETE) ProductVersionDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void createdProductVersionFeature(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_CREATE) ProductVersionFeatureDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void deletedProductVersionFeature(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_DELETE) ProductVersionFeatureDescriptor input) {
		dashboard.updateProductInfo(products);
	}

	@Inject
	@Optional
	public void createdUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_CREATE) UserOriginDescriptor input) {
		dashboard.updateUserInfo(users);
	}

	@Inject
	@Optional
	public void deletedUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_DELETE) UserOriginDescriptor input) {
		dashboard.updateUserInfo(users);
	}

	@Inject
	@Optional
	public void createdUser(@UIEventTopic(UserRegistryEvents.USER_CREATE) UserDescriptor input) {
		dashboard.updateUserInfo(users);
	}

	@Inject
	@Optional
	public void deletedUser(@UIEventTopic(UserRegistryEvents.USER_DELETE) UserDescriptor input) {
		dashboard.updateUserInfo(users);
	}

	@Inject
	@Optional
	public void createdAgreementsGroup(
			@UIEventTopic(AgreementRegistryEvents.AGREEMENT_GROUP_CREATE) AgreementGroupDescriptor input) {
		dashboard.updateAgreementInfo(agreements);
	}

	@Inject
	@Optional
	public void deletedAgreementsGroup(
			@UIEventTopic(AgreementRegistryEvents.AGREEMENT_GROUP_DELETE) AgreementGroupDescriptor input) {
		dashboard.updateAgreementInfo(agreements);
	}

	@Inject
	@Optional
	public void createdAgreement(@UIEventTopic(AgreementRegistryEvents.AGREEMENT_CREATE) AgreementDescriptor input) {
		dashboard.updateAgreementInfo(agreements);
	}

	@Inject
	@Optional
	public void deletedAgreement(@UIEventTopic(AgreementRegistryEvents.AGREEMENT_DELETE) AgreementDescriptor input) {
		dashboard.updateAgreementInfo(agreements);
	}

	@Inject
	@Optional
	public void createdLicensePlan(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PLAN_CREATE) LicensePlanDescriptor input) {
		dashboard.updateLicenseInfo(licenses);
	}

	@Inject
	@Optional
	public void deletedLicensePlan(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PLAN_DELETE) LicensePlanDescriptor input) {
		dashboard.updateLicenseInfo(licenses);
	}

	@PreDestroy
	public void preDestroy(IEclipseContext context) {
		dashboard.dispose(context);
	}

	@Focus
	public void onFocus() {

	}

	@Persist
	public void save() {

	}

}