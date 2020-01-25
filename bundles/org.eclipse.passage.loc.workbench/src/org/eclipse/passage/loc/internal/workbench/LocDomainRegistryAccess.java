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
package org.eclipse.passage.loc.internal.workbench;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.SelectionCommandAdvisor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LocDomainRegistryAccess implements EditingDomainRegistryAccess {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());;

	private final Map<String, EditingDomainRegistry<?>> domainRegistries = new HashMap<>();
	private final Map<String, String> fileExtensions = new HashMap<>();
	private final Map<String, ClassifierInitializer> classifierInitializers = new HashMap<>();
	private final Map<String, SelectionCommandAdvisor> selectionAdvisors = new HashMap<>();

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerEditingDomainRegistry(EditingDomainRegistry<?> instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		registerEntry(domainRegistries, domain, instance);
		String extension = String.valueOf(properties.get(PROPERTY_FILE_EXTENSION));
		registerEntry(fileExtensions, domain, extension);
	}

	public void unregisterEditingDomainRegistry(EditingDomainRegistry<?> instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		unregisterEntry(domainRegistries, domain, instance);
		String extension = String.valueOf(properties.get(PROPERTY_FILE_EXTENSION));
		unregisterEntry(fileExtensions, domain, extension);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerClassifierInitializer(ClassifierInitializer instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		registerEntry(classifierInitializers, domain, instance);
	}

	public void unregisterClassifierInitializer(ClassifierInitializer instance, Map<String, Object> properties) {
		String domain = String.valueOf(properties.get(PROPERTY_DOMAIN_NAME));
		unregisterEntry(classifierInitializers, domain, instance);
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
		return fileExtensions.get(domain);
	}

	@Override
	public ClassifierInitializer getClassifierInitializer(String domain) {
		return classifierInitializers.get(domain);
	}

	@Override
	public SelectionCommandAdvisor getSelectionCommandAdvisor(String domain) {
		return selectionAdvisors.get(domain);
	}

}
