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
package org.eclipse.passage.loc.internal.products.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.DomainContentAdapter;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ProductDomainRegistry;
import org.eclipse.passage.lic.model.api.ProductLine;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.runtime.products.ProductDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductLineDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductRegistry;
import org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductsEvents;
import org.eclipse.passage.lic.runtime.products.ProductsRegistry;
import org.eclipse.passage.lic.runtime.registry.Identified;
import org.eclipse.passage.loc.edit.EditingDomainBasedRegistry;
import org.eclipse.passage.loc.runtime.OperatorEvents;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + ProductsRegistry.DOMAIN_NAME,
		DomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + ProductsRegistry.FILE_EXTENSION_XMI })
public class ProductDomainRegistryImpl extends EditingDomainBasedRegistry
		implements ProductRegistry, ProductDomainRegistry, EditingDomainRegistry {

	private final Map<String, ProductLineDescriptor> productLineIndex = new HashMap<>();
	private final Map<String, ProductDescriptor> productIndex = new HashMap<>();
	private final Map<String, Map<String, ProductVersionDescriptor>> productVersionIndex = new HashMap<>();
	private final Map<String, Map<String, Map<String, ProductVersionFeatureDescriptor>>> productVersionFeatureIndex = new HashMap<>();

	@Reference
	@Override
	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		super.bindEnvironmentInfo(environmentInfo);
	}

	@Override
	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		super.unbindEnvironmentInfo(environmentInfo);
	}

	@Reference
	@Override
	public void bindEventAdmin(EventAdmin eventAdmin) {
		super.bindEventAdmin(eventAdmin);
	}

	@Override
	public void unbindEventAdmin(EventAdmin eventAdmin) {
		super.unbindEventAdmin(eventAdmin);
	}

	@Reference
	@Override
	public void bindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		super.bindFactoryProvider(factoryProvider);
	}

	@Override
	public void unbindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		super.unbindFactoryProvider(factoryProvider);
	}

	@Activate
	public void activate(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	@Override
	public void deactivate(Map<String, Object> properties) {
		Collection<Map<String, ProductVersionDescriptor>> values = productVersionIndex.values();
		for (Map<String, ProductVersionDescriptor> map : values) {
			map.clear();
		}
		productVersionIndex.clear();
		productIndex.clear();
		productLineIndex.clear();
		super.deactivate(properties);
	}

	@Override
	public String createPassword(ProductVersionDescriptor descriptor) {
		String id = null;
		String version = null;
		if (descriptor != null) {
			ProductDescriptor product = descriptor.getProduct();
			if (product != null) {
				id = product.getIdentifier();
			}
			version = descriptor.getVersion();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("###").append(version); //$NON-NLS-1$
		return sb.toString();
	}

	@Override
	public String getFileExtension() {
		return ProductsRegistry.FILE_EXTENSION_XMI;
	}

	@Override
	public Iterable<ProductLineDescriptor> getProductLines() {
		return new ArrayList<>(productLineIndex.values());
	}

	@Override
	public ProductLineDescriptor getProductLine(String identifier) {
		return productLineIndex.get(identifier);
	}

	@Override
	public Iterable<ProductDescriptor> getProducts() {
		return new ArrayList<>(productIndex.values());
	}

	@Override
	public Iterable<? extends ProductDescriptor> getProducts(String productLineId) {
		ProductLineDescriptor productLine = productLineIndex.get(productLineId);
		if (productLine == null) {
			return Collections.emptyList();
		}
		return productLine.getProducts();
	}

	@Override
	public ProductDescriptor getProduct(String productId) {
		return productIndex.get(productId);
	}

	@Override
	public Iterable<ProductVersionDescriptor> getProductVersions() {
		List<ProductVersionDescriptor> list = new ArrayList<>();
		Collection<Map<String, ProductVersionDescriptor>> values = productVersionIndex.values();
		for (Map<String, ProductVersionDescriptor> map : values) {
			list.addAll(map.values());
		}
		return list;
	}

	@Override
	public Iterable<ProductVersionDescriptor> getProductVersions(String productId) {
		Map<String, ProductVersionDescriptor> map = productVersionIndex.get(productId);
		if (map == null) {
			return Collections.emptyList();
		}
		return new ArrayList<>(map.values());
	}

	@Override
	public ProductVersionDescriptor getProductVersion(String product, String version) {
		Map<String, ProductVersionDescriptor> map = productVersionIndex.get(product);
		if (map == null) {
			return null;
		}
		return map.get(version);
	}

	@Override
	public Iterable<ProductVersionFeatureDescriptor> getProductVersionFeatures() {
		List<ProductVersionFeatureDescriptor> productVersionFeatures = new ArrayList<>();
		Collection<Map<String, Map<String, ProductVersionFeatureDescriptor>>> versionValues = productVersionFeatureIndex
				.values();
		for (Map<String, Map<String, ProductVersionFeatureDescriptor>> versions : versionValues) {
			Collection<Map<String, ProductVersionFeatureDescriptor>> values = versions.values();
			for (Map<String, ProductVersionFeatureDescriptor> map : values) {
				productVersionFeatures.addAll(map.values());
			}
		}
		return productVersionFeatures;
	}

	@Override
	public Iterable<ProductVersionFeatureDescriptor> getProductVersionFeatures(String productId, String version) {
		Map<String, Map<String, ProductVersionFeatureDescriptor>> versions = productVersionFeatureIndex.get(productId);
		if (versions == null) {
			return Collections.emptyList();
		}
		Map<String, ProductVersionFeatureDescriptor> map = versions.get(version);
		if (map == null) {
			return Collections.emptyList();
		}
		List<ProductVersionFeatureDescriptor> result = new ArrayList<>();
		map.values().forEach(result::add);
		return result;
	}

	@Override
	protected DomainContentAdapter<ProductDomainRegistry> createContentAdapter() {
		return new ProductDomainRegistryTracker(this);
	}

	@Override
	public void registerProductLine(ProductLineDescriptor productLine) {
		String identifier = productLine.getIdentifier();
		ProductLineDescriptor existing = productLineIndex.put(identifier, productLine);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_LINE_CREATE, productLine));
		productLine.getProducts().forEach(p -> registerProduct(p));
	}

	@Override
	public void registerProduct(ProductDescriptor product) {
		String identifier = product.getIdentifier();
		ProductDescriptor existing = productIndex.put(identifier, product);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_CREATE, product));
		product.getProductVersions().forEach(pv -> registerProductVersion(product, pv));
	}

	@Override
	public void registerProductVersion(ProductDescriptor product, ProductVersionDescriptor productVersion) {
		String identifier = product.getIdentifier();
		Map<String, ProductVersionDescriptor> versions = productVersionIndex.computeIfAbsent(identifier,
				key -> new HashMap<>());
		String version = productVersion.getVersion();
		ProductVersionDescriptor existing = versions.put(version, productVersion);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_VERSION_CREATE, productVersion));
	}

	@Override
	public void registerProductVersionFeature(ProductDescriptor product, ProductVersionDescriptor productVersion,
			ProductVersionFeatureDescriptor productVersionFeature) {
		String identifier = product.getIdentifier();
		Map<String, Map<String, ProductVersionFeatureDescriptor>> versions = productVersionFeatureIndex
				.computeIfAbsent(identifier, key -> new HashMap<>());
		String version = productVersion.getVersion();
		Map<String, ProductVersionFeatureDescriptor> features = versions.computeIfAbsent(version,
				key -> new HashMap<>());
		String featureIdentifier = productVersionFeature.getFeatureIdentifier();
		ProductVersionFeatureDescriptor existing = features.put(featureIdentifier, productVersionFeature);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin
				.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_VERSION_FEATURE_CREATE, productVersionFeature));
	}

	@Override
	public void unregisterProductLine(String productLineId) {
		ProductLineDescriptor removed = productLineIndex.remove(productLineId);
		if (removed != null) {
			eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_LINE_DELETE, removed));
			removed.getProducts().forEach(p -> unregisterProduct(p.getIdentifier()));
		}
	}

	@Override
	public void unregisterProduct(String productId) {
		ProductDescriptor removed = productIndex.remove(productId);
		if (removed != null) {
			eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_DELETE, removed));
			removed.getProductVersions().forEach(pv -> unregisterProductVersion(productId, pv.getVersion()));
		}
	}

	@Override
	public void unregisterProductVersion(String productId, String version) {
		Map<String, ProductVersionDescriptor> versions = productVersionIndex.get(productId);
		if (versions != null) {
			ProductVersionDescriptor removed = versions.remove(version);
			if (removed != null) {
				eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_VERSION_DELETE, removed));
			}
			if (versions.isEmpty()) {
				productVersionIndex.remove(productId);
			}
			removed.getProductVersionFeatures()
					.forEach(pvf -> unregisterProductVersionFeature(productId, version, pvf.getFeatureIdentifier()));
		}
	}

	@Override
	public void unregisterProductVersionFeature(String productId, String version, String featureId) {
		Map<String, Map<String, ProductVersionFeatureDescriptor>> versions = productVersionFeatureIndex.get(productId);
		if (versions != null) {
			Map<String, ProductVersionFeatureDescriptor> features = versions.get(version);
			if (features != null) {
				ProductVersionFeatureDescriptor removed = features.remove(featureId);
				if (removed != null) {
					eventAdmin.postEvent(OperatorEvents.create(ProductsEvents.PRODUCT_VERSION_FEATURE_DELETE, removed));
				}
				if (features.isEmpty()) {
					versions.remove(version);
				}
			}
			if (versions.isEmpty()) {
				productVersionFeatureIndex.remove(productId);
			}
		}
	}

	@Override
	public EClass getContentClassifier() {
		return LicPackage.eINSTANCE.getProductLine();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return LicPackage.eINSTANCE.getProductLine_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return LicPackage.eINSTANCE.getProductLine_Name();
	}

	@Override
	public void registerContent(Identified content) {
		if (content instanceof ProductLine) {
			ProductLine productLine = (ProductLine) content;
			registerProductLine(productLine);
		} else {
			// TODO: warning
		}
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterProductLine(identifier);
	}

}
