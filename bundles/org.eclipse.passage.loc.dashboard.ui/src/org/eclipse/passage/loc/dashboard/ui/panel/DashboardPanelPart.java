/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
import org.eclipse.passage.lic.emf.meta.ComposableClassMetadata;
import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.FeatureVersionDescriptor;
import org.eclipse.passage.lic.internal.features.model.FeaturesClassMetadata;
import org.eclipse.passage.lic.internal.licenses.model.LicensesClassMetadata;
import org.eclipse.passage.lic.internal.products.model.ProductsClassMetadata;
import org.eclipse.passage.lic.internal.users.model.UsersClassMetadata;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistryEvents;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.products.registry.ProductRegistryEvents;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.lic.users.registry.UserRegistryEvents;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistryEvents;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.FrameworkUtil;

public class DashboardPanelPart {

	private final FeatureRegistry featureRegistry;
	private final ProductRegistry productRegistry;
	private final UserRegistry userRegistry;
	private final LicenseRegistry licenseRegistry;
	private final DashboardPanelAdvisor dashboardAdvisor;

	@Inject
	public DashboardPanelPart(IEclipseContext context) {
		this.featureRegistry = context.get(FeatureRegistry.class);
		this.productRegistry = context.get(ProductRegistry.class);
		this.userRegistry = context.get(UserRegistry.class);
		this.licenseRegistry = context.get(LicenseRegistry.class);
		DashboardPanelAdvisor advisor = context.get(DashboardPanelAdvisor.class);
		if (advisor == null) {
			advisor = new DefaultDashboardPanelAdvisor();
		}
		this.dashboardAdvisor = advisor;
	}

	@PostConstruct
	public void postConstruct(Composite parent, IEclipseContext context) {
		dashboardAdvisor.init(context);
		Composite area = new Composite(parent, SWT.NONE);
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setLayout(GridLayoutFactory.swtDefaults().create());
		createHeaderInfo(area);
		createFeatureInfo(area);
		createProductInfo(area);
		createUserInfo(area);
		createLicenseInfo(area);
		createFooterInfo(area);
		// FIXME: replace this with OSGi component registration
		ComposableClassMetadata metadata = EclipseContextFactory
				.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext())
				.get(ComposableClassMetadata.class);
		metadata.consider(new FeaturesClassMetadata());
		metadata.consider(new ProductsClassMetadata());
		metadata.consider(new UsersClassMetadata());
		metadata.consider(new LicensesClassMetadata());
	}

	protected void createHeaderInfo(Composite parent) {
		dashboardAdvisor.createHeaderInfo(parent);
	}

	protected void createFeatureInfo(Composite parent) {
		dashboardAdvisor.createFeatureInfo(parent, featureRegistry);
	}

	protected void createProductInfo(Composite parent) {
		dashboardAdvisor.createProductInfo(parent, productRegistry);
	}

	protected void createUserInfo(Composite parent) {
		dashboardAdvisor.createUserInfo(parent, userRegistry);
	}

	protected void createLicenseInfo(Composite parent) {
		dashboardAdvisor.createLicenseInfo(parent, licenseRegistry);
	}

	protected void createFooterInfo(Composite parent) {
		dashboardAdvisor.createFooterInfo(parent);
	}

	@Inject
	@Optional
	public void createdFeatureSet(@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_CREATE) FeatureSetDescriptor input) {
		dashboardAdvisor.updateFeatureInfo(featureRegistry);
	}

	@Inject
	@Optional
	public void deletedFeatureSet(@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_DELETE) FeatureSetDescriptor input) {
		dashboardAdvisor.updateFeatureInfo(featureRegistry);
	}

	@Inject
	@Optional
	public void createdFeature(@UIEventTopic(FeatureRegistryEvents.FEATURE_CREATE) FeatureDescriptor input) {
		dashboardAdvisor.updateFeatureInfo(featureRegistry);
	}

	@Inject
	@Optional
	public void deletedFeature(@UIEventTopic(FeatureRegistryEvents.FEATURE_DELETE) FeatureDescriptor input) {
		dashboardAdvisor.updateFeatureInfo(featureRegistry);
	}

	@Inject
	@Optional
	public void createdFeatureVersion(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_VERSION_CREATE) FeatureVersionDescriptor input) {
		dashboardAdvisor.updateFeatureInfo(featureRegistry);
	}

	@Inject
	@Optional
	public void deletedFeatureVersion(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_VERSION_DELETE) FeatureVersionDescriptor input) {
		dashboardAdvisor.updateFeatureInfo(featureRegistry);
	}

	@Inject
	@Optional
	public void createdProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_CREATE) ProductLineDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void deletedProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_DELETE) ProductLineDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void createdProduct(@UIEventTopic(ProductRegistryEvents.PRODUCT_CREATE) ProductDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void deletedProduct(@UIEventTopic(ProductRegistryEvents.PRODUCT_DELETE) ProductDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void createdProductVersion(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_CREATE) ProductVersionDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void deletedProductVersion(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_DELETE) ProductVersionDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void createdProductVersionFeature(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_CREATE) ProductVersionFeatureDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void deletedProductVersionFeature(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_DELETE) ProductVersionFeatureDescriptor input) {
		dashboardAdvisor.updateProductInfo(productRegistry);
	}

	@Inject
	@Optional
	public void createdUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_CREATE) UserOriginDescriptor input) {
		dashboardAdvisor.updateUserInfo(userRegistry);
	}

	@Inject
	@Optional
	public void deletedUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_DELETE) UserOriginDescriptor input) {
		dashboardAdvisor.updateUserInfo(userRegistry);
	}

	@Inject
	@Optional
	public void createdUser(@UIEventTopic(UserRegistryEvents.USER_CREATE) UserDescriptor input) {
		dashboardAdvisor.updateUserInfo(userRegistry);
	}

	@Inject
	@Optional
	public void deletedUser(@UIEventTopic(UserRegistryEvents.USER_DELETE) UserDescriptor input) {
		dashboardAdvisor.updateUserInfo(userRegistry);
	}

	@Inject
	@Optional
	public void createdUserLicense(@UIEventTopic(UserRegistryEvents.USER_LICENSE_CREATE) UserLicenseDescriptor input) {
		dashboardAdvisor.updateUserInfo(userRegistry);
	}

	@Inject
	@Optional
	public void deletedUserLicense(@UIEventTopic(UserRegistryEvents.USER_LICENSE_DELETE) UserLicenseDescriptor input) {
		dashboardAdvisor.updateUserInfo(userRegistry);
	}

	@Inject
	@Optional
	public void createdLicensePlan(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PLAN_CREATE) LicensePlanDescriptor input) {
		dashboardAdvisor.updateLicenseInfo(licenseRegistry);
	}

	@Inject
	@Optional
	public void deletedLicensePlan(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PLAN_DELETE) LicensePlanDescriptor input) {
		dashboardAdvisor.updateLicenseInfo(licenseRegistry);
	}

	@PreDestroy
	public void preDestroy(IEclipseContext context) {
		dashboardAdvisor.dispose(context);
	}

	@Focus
	public void onFocus() {

	}

	@Persist
	public void save() {

	}

}