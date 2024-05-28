/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.lic.internal.products.model.util.ProductsResourceImpl;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.Products;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.equinox.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistryEvents;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + ProductsPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "products_xmi" })
public final class ProductDomainRegistry extends BaseDomainRegistry<ProductLine>
		implements ProductRegistry, EditingDomainRegistry<ProductLine> {

	private final Map<String, ProductLine> lines = new HashMap<>();
	private final Map<String, Product> products = new HashMap<>();
	private final Map<String, Map<String, ProductVersion>> versions = new HashMap<>();
	private final Map<String, Map<String, Map<String, ProductVersionFeature>>> features = new HashMap<>();

	private final List<EventAdmin> events = new ArrayList<>();

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	public void bindEventAdmin(EventAdmin admin) {
		this.events.add(admin);
	}

	public void unbindEventAdmin(EventAdmin admin) {
		this.events.remove(admin);
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

	@Activate
	public void load(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	public void unload(Map<String, Object> properties) {
		for (Map<String, Map<String, ProductVersionFeature>> map : features.values()) {
			map.clear();
		}
		versions.values().forEach(Map::clear);
		products.clear();
		lines.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return "products_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<ProductLine> getContentClass() {
		return ProductLine.class;
	}

	@Override
	public String resolveIdentifier(ProductLine content) {
		return content.getIdentifier();
	}

	@Override
	public Collection<ProductLine> productLines() {
		return new ArrayList<>(lines.values());
	}

	@Override
	public Optional<ProductLine> productLine(String identifier) {
		return Optional.ofNullable(lines.get(identifier));
	}

	@Override
	public Collection<Product> products() {
		return new ArrayList<>(products.values());
	}

	@Override
	public Optional<Product> product(String id) {
		return Optional.ofNullable(products.get(id));
	}

	@Override
	public Collection<ProductVersion> productVersions() {
		List<ProductVersion> list = new ArrayList<>();
		Collection<Map<String, ProductVersion>> values = versions.values();
		for (Map<String, ProductVersion> map : values) {
			list.addAll(map.values());
		}
		return list;
	}

	@Override
	public Optional<ProductVersion> productVersion(String product, String version) {
		return Optional.ofNullable(versions.get(product)).map(m -> m.get(version));
	}

	@Override
	public Collection<ProductVersionFeature> productVersionFeatures() {
		List<ProductVersionFeature> result = new ArrayList<>();
		for (Map<String, Map<String, ProductVersionFeature>> maps : features.values()) {
			for (Map<String, ProductVersionFeature> map : maps.values()) {
				result.addAll(map.values());
			}
		}
		return result;
	}

	@Override
	protected DomainContentAdapter<ProductLine, ProductDomainRegistry> createContentAdapter() {
		return new ProductsDomainRegistryTracker(this);
	}

	void registerProductLine(ProductLine line) {
		ProductLine existing = lines.put(line.getIdentifier(), line);
		if ((existing != null) && (existing != line)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing, line));
		}
		events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_LINE_CREATE, line).get());
		line.getProducts().forEach(p -> registerProduct(p));
	}

	void registerProduct(Product product) {
		String identifier = product.getIdentifier();
		Product existing = products.put(identifier, product);
		if ((existing != null) && (existing != product)) {
			String msg = NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing, product);
			Platform.getLog(getClass()).warn(msg);
		}
		events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_CREATE, product).get());
		product.getProductVersions().forEach(pv -> registerProductVersion(product, pv));
	}

	void registerProductVersion(Product product, ProductVersion version) {
		ProductVersion existing = versions.computeIfAbsent(product.getIdentifier(), key -> new HashMap<>())
				.put(version.getVersion(), version);
		if ((existing != null) && (existing != version)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing, version));
		}
		events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_CREATE, version).get());
	}

	void registerProductVersionFeature(Product product, ProductVersion version, ProductVersionFeature feature) {
		ProductVersionFeature existing = features//
				.computeIfAbsent(product.getIdentifier(), key -> new HashMap<>())//
				.computeIfAbsent(version.getVersion(), key -> new HashMap<>())//
				.put(feature.getFeatureIdentifier(), feature);
		if ((existing != null) && (existing != feature)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(ProductsCoreMessages.ProductDomain_instance_duplication_message, existing, feature));
		}
		events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_CREATE, feature).get());
	}

	void unregisterProductLine(String id) {
		ProductLine removed = lines.remove(id);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_LINE_DELETE, removed).get());
			removed.getProducts().forEach(p -> unregisterProduct(p.getIdentifier()));
		}
	}

	void unregisterProduct(String id) {
		Product removed = products.remove(id);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_DELETE, removed).get());
			removed.getProductVersions().forEach(pv -> unregisterProductVersion(id, pv.getVersion()));
		}
	}

	void unregisterProductVersion(String product, String version) {
		Map<String, ProductVersion> found = versions.get(product);
		if (found != null) {
			ProductVersion removed = found.remove(version);
			if (removed != null) {
				events().postEvent(new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_DELETE, removed).get());
				removed.getProductVersionFeatures()
						.forEach(pvf -> unregisterProductVersionFeature(product, version, pvf.getFeatureIdentifier()));
			}
			if (found.isEmpty()) {
				found.remove(product);
			}
		}
	}

	void unregisterProductVersionFeature(String product, String version, String feature) {
		Map<String, Map<String, ProductVersionFeature>> maps = features.get(product);
		if (maps != null) {
			Map<String, ProductVersionFeature> map = maps.get(version);
			if (map != null) {
				ProductVersionFeature removed = map.remove(feature);
				if (removed != null) {
					events().postEvent(
							new EquinoxEvent(ProductRegistryEvents.PRODUCT_VERSION_FEATURE_DELETE, removed).get());
				}
				if (map.isEmpty()) {
					maps.remove(version);
				}
			}
			if (maps.isEmpty()) {
				features.remove(product);
			}
		}
	}

	private EventAdmin events() {
		return events.stream().findAny().get();
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
	public void registerContent(ProductLine content) {
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
