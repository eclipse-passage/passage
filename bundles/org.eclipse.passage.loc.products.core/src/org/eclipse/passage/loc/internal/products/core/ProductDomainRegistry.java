/*******************************************************************************
 * Copyright (c) 2018, 2022 ArSysOp
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.lic.products.model.util.ProductsResourceImpl;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.Products;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistryEvents;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + ProductsPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "products_xmi" })
public class ProductDomainRegistry extends BaseDomainRegistry<ProductLineDescriptor>
		implements ProductRegistry, EditingDomainRegistry<ProductLineDescriptor> {

	private final Map<String, ProductLineDescriptor> productLineIndex = new HashMap<>();
	private final Map<String, ProductDescriptor> productIndex = new HashMap<>();
	private final Map<String, Map<String, ProductVersionDescriptor>> productVersionIndex = new HashMap<>();
	private final Map<String, Map<String, Map<String, ProductVersionFeatureDescriptor>>> productVersionFeatureIndex = new HashMap<>();

	private EventAdmin events;

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.events = admin;
	}

	public void unbindEventAdmin(@SuppressWarnings("unused") EventAdmin admin) {
		this.events = null;
	}

	@Override
	@Reference
	public void bindGear(OperatorGearSupplier supplier) {
		super.bindGear(supplier);
	}

	@Override
	public void unbindGear(OperatorGearSupplier supplier) {
		super.unbindGear(supplier);
	}

	@Override
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
	public String getFileExtension() {
		return "products_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<ProductLineDescriptor> getContentClass() {
		return ProductLineDescriptor.class;
	}

	@Override
	public String resolveIdentifier(ProductLineDescriptor content) {
		return content.getIdentifier();
	}

	@Override
	public Iterable<? extends ProductLineDescriptor> getProductLines() {
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
	protected DomainContentAdapter<ProductLineDescriptor, ProductDomainRegistry> createContentAdapter() {
		return new ProductsDomainRegistryTracker(this);
	}

	@Override
	public void registerProductLine(ProductLineDescriptor productLine) {
		String identifier = productLine.getIdentifier();
		ProductLineDescriptor existing = productLineIndex.put(identifier, productLine);
		if (existing != null) {
			String msg = NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing,
					productLine);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_LINE_CREATE, productLine).get());
		productLine.getProducts().forEach(p -> registerProduct(p));
	}

	@Override
	public void registerProduct(ProductDescriptor product) {
		String identifier = product.getIdentifier();
		ProductDescriptor existing = productIndex.put(identifier, product);
		if (existing != null) {
			String msg = NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing, product);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_CREATE, product).get());
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
			String msg = NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing,
					productVersion);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_CREATE, productVersion).get());
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
			String msg = NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing,
					productVersionFeature);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(
				new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_CREATE, productVersionFeature).get());
	}

	@Override
	public void unregisterProductLine(String productLineId) {
		ProductLineDescriptor removed = productLineIndex.remove(productLineId);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_LINE_DELETE, removed).get());
			removed.getProducts().forEach(p -> unregisterProduct(p.getIdentifier()));
		}
	}

	@Override
	public void unregisterProduct(String productId) {
		ProductDescriptor removed = productIndex.remove(productId);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_DELETE, removed).get());
			removed.getProductVersions().forEach(pv -> unregisterProductVersion(productId, pv.getVersion()));
		}
	}

	@Override
	public void unregisterProductVersion(String productId, String version) {
		Map<String, ProductVersionDescriptor> versions = productVersionIndex.get(productId);
		if (versions != null) {
			ProductVersionDescriptor removed = versions.remove(version);
			if (removed != null) {
				events.postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_DELETE, removed).get());
				removed.getProductVersionFeatures().forEach(
						pvf -> unregisterProductVersionFeature(productId, version, pvf.getFeatureIdentifier()));
			}
			if (versions.isEmpty()) {
				productVersionIndex.remove(productId);
			}
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
					events.postEvent(
							new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_DELETE, removed).get());
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
		return ProductsPackage.eINSTANCE.getProductLine();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return ProductsPackage.eINSTANCE.getProductLine_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return ProductsPackage.eINSTANCE.getProductLine_Name();
	}

	@Override
	public void registerContent(ProductLineDescriptor content) {
		registerProductLine(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterProductLine(identifier);
	}

	@Override
	protected final Resource createResource(URI uri) {
		return new ProductsResourceImpl(uri);
	}

	@Override
	protected boolean emfResource(ResourceHandle handle) {
		return Products.xmi.equals(handle.type()) || Products.xmi033.equals(handle.type());
	}

	@Override
	protected KnownResources knownResources(OperatorWorkspace workspace) {
		return workspace.products();
	}

}
