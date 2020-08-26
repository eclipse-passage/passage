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
package org.eclipse.passage.loc.internal.products.core;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;

public class ProductsDomainRegistryTracker extends DomainContentAdapter<ProductLineDescriptor, ProductDomainRegistry> {

	public ProductsDomainRegistryTracker(ProductDomainRegistry registry) {
		super(registry);
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof ProductLine) {
			ProductLine productLine = (ProductLine) notifier;
			switch (notification.getFeatureID(ProductLine.class)) {
			case ProductsPackage.PRODUCT_LINE__IDENTIFIER:
				processProductLineIdentifier(productLine, notification);
				break;
			case ProductsPackage.PRODUCT_LINE__PRODUCTS:
				processProductLineProducts(productLine, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof Product) {
			Product product = (Product) notifier;
			switch (notification.getFeatureID(Product.class)) {
			case ProductsPackage.PRODUCT__IDENTIFIER:
				processProductIdentifier(product, notification);
				break;
			case ProductsPackage.PRODUCT__PRODUCT_VERSIONS:
				processProductProductVersions(product, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof ProductVersion) {
			ProductVersion productVersion = (ProductVersion) notifier;
			switch (notification.getFeatureID(ProductVersion.class)) {
			case ProductsPackage.PRODUCT_VERSION__VERSION:
				processProductVersionVersion(productVersion, notification);
				break;
			case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
				processProductVersionProductVersionFeatures(productVersion, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof ProductVersionFeature) {
			ProductVersionFeature productVersionFeature = (ProductVersionFeature) notifier;
			switch (notification.getFeatureID(ProductVersionFeature.class)) {
			case ProductsPackage.PRODUCT_VERSION_FEATURE__FEATURE_IDENTIFIER:
				processProductVersionFeatureFeatureIdentifier(productVersionFeature, notification);
				break;
			default:
				break;
			}
		}
		super.notifyChanged(notification);
	}

	protected void processProductLineIdentifier(ProductLine productLine, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterProductLine(oldValue);
			}
			if (newValue != null) {
				registry.registerProductLine(productLine);
			}
			break;
		default:
			break;
		}
	}

	protected void processProductLineProducts(ProductLine productLine, Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof Product) {
				Product product = (Product) newValue;
				String identifier = product.getIdentifier();
				if (identifier != null) {
					registry.registerProduct(product);
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof Product) {
				Product product = (Product) oldValue;
				String identifier = product.getIdentifier();
				if (identifier != null) {
					registry.unregisterProduct(identifier);
				}
			}
			break;

		default:
			break;
		}
	}

	protected void processProductIdentifier(Product product, Notification notification) {
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterProduct(oldValue);
			}
			if (newValue != null) {
				registry.registerProduct(product);
			}
			break;
		default:
			break;
		}
	}

	protected void processProductProductVersions(Product product, Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof ProductVersion) {
				ProductVersion productVersion = (ProductVersion) newValue;
				String version = productVersion.getVersion();
				if (version != null) {
					registry.registerProductVersion(product, productVersion);
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof ProductVersion) {
				ProductVersion productVersion = (ProductVersion) oldValue;
				String version = productVersion.getVersion();
				if (version != null) {
					registry.unregisterProductVersion(product.getIdentifier(), version);
				}
			}
			break;

		default:
			break;
		}
	}

	protected void processProductVersionVersion(ProductVersion productVersion, Notification notification) {
		Product product = productVersion.getProduct();
		if (product == null) {
			// FIXME: warn
			return;
		}
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				registry.unregisterProductVersion(product.getIdentifier(), oldValue);
			}
			if (newValue != null) {
				registry.registerProductVersion(product, productVersion);
			}
			break;
		default:
			break;
		}
	}

	protected void processProductVersionProductVersionFeatures(ProductVersion productVersion,
			Notification notification) {
		Product product = productVersion.getProduct();
		if (product == null) {
			// FIXME: warn
			return;
		}
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof ProductVersionFeature) {
				ProductVersionFeature productVersionFeature = (ProductVersionFeature) newValue;
				String featureId = productVersionFeature.getFeatureIdentifier();
				if (featureId != null) {
					registry.registerProductVersionFeature(product, productVersion, productVersionFeature);
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof ProductVersionFeature) {
				ProductVersionFeature productVersionFeature = (ProductVersionFeature) oldValue;
				String featureId = productVersionFeature.getFeatureIdentifier();
				if (featureId != null) {
					registry.unregisterProductVersionFeature(product.getIdentifier(), productVersion.getVersion(),
							featureId);
				}
			}
			break;

		default:
			break;
		}
	}

	protected void processProductVersionFeatureFeatureIdentifier(ProductVersionFeature productVersionFeature,
			Notification notification) {
		ProductVersion productVersion = productVersionFeature.getProductVersion();
		if (productVersion == null) {
			// FIXME: warn
			return;
		}
		Product product = productVersion.getProduct();
		if (product == null) {
			// FIXME: warn
			return;
		}
		String oldValue = notification.getOldStringValue();
		String newValue = notification.getNewStringValue();
		switch (notification.getEventType()) {
		case Notification.SET:
			if (oldValue != null) {
				String productId = product.getIdentifier();
				String version = productVersion.getVersion();
				registry.unregisterProductVersionFeature(productId, version, oldValue);
			}
			if (newValue != null) {
				registry.registerProductVersionFeature(product, productVersion, productVersionFeature);
			}
			break;
		default:
			break;
		}
	}
}
