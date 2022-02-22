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
package org.eclipse.passage.loc.internal.workbench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class LocDomainRegistryAccess implements EditingDomainRegistryAccess {

	private final Logger logger = LogManager.getLogger(getClass());

	private final Map<String, EditingDomainRegistry<?>> domainRegistries = new HashMap<>();
	private final Map<String, String> domain2extension = new HashMap<>();
	private final Map<String, String> extension2domain = new HashMap<>();
	private final Map<String, SelectionCommandAdvisor> selectionAdvisors = new HashMap<>();

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerEditingDomainRegistry(EditingDomainRegistry<?> instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		registerEntry(domainRegistries, domain, instance);
		String extension = String.valueOf(properties.get(PROPERTY_FILE_EXTENSION));
		registerEntry(domain2extension, domain, extension);
		registerEntry(extension2domain, extension, domain);
	}

	public void unregisterEditingDomainRegistry(EditingDomainRegistry<?> instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		unregisterEntry(domainRegistries, domain, instance);
		String extension = String.valueOf(properties.get(PROPERTY_FILE_EXTENSION));
		unregisterEntry(domain2extension, domain, extension);
		unregisterEntry(extension2domain, extension, domain);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerCommandAdvisor(SelectionCommandAdvisor instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		registerEntry(selectionAdvisors, domain, instance);
	}

	public void unregisterCommandAdvisor(SelectionCommandAdvisor instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		unregisterEntry(selectionAdvisors, domain, instance);
	}

	protected <K, V> void registerEntry(Map<K, V> map, K key, V value) {
		V existing = map.put(key, value);
		if (existing != null) {
			logger.warn("Replaced {} for domain {}", existing, key); //$NON-NLS-1$
		}
		logger.trace("Registered {} for domain {}", value, key); //$NON-NLS-1$
	}

	protected <K, V> void unregisterEntry(Map<K, V> map, K key, V value) {
		V existing = map.remove(key);
		if (existing == null) {
			logger.warn("Unexpected null (should be {}) for domain {}", value, key); //$NON-NLS-1$
		}
		logger.trace("Unregistered {} for domain {}", existing, key); //$NON-NLS-1$
	}

	@Override
	public EditingDomainRegistry<?> getDomainRegistry(String domain) {
		return domainRegistries.get(domain);
	}

	@Override
	public String getFileExtension(String domain) {
		return domain2extension.get(domain);
	}

	@Override
	public SelectionCommandAdvisor getSelectionCommandAdvisor(String domain) {
		return selectionAdvisors.get(domain);
	}

	public Optional<String> domainForExtension(String extension) {
		return Optional.ofNullable(extension2domain.get(extension));
	}

	public Optional<EditingDomainRegistry<?>> domainRegistryForExtension(String extension) {
		return domainForExtension(extension)//
				.flatMap(d -> Optional.ofNullable(domainRegistries.get(d)));
	}

	public List<EditingDomainRegistry<?>> domainRegistryList() {
		return new ArrayList<EditingDomainRegistry<?>>(domainRegistries.values());
	}

}
