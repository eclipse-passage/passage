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
package org.eclipse.passage.lic.emf.edit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.emf.ecore.DomainContentAdapter;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.runtime.LicensingReporter;

public abstract class BaseDomainRegistry<I> implements EditingDomainRegistry<I>, IEditingDomainProvider {

	protected LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	protected String domainName;

	private ComposedAdapterFactory composedAdapterFactory;

	private AdapterFactoryEditingDomain editingDomain;

	private final List<String> sources = new ArrayList<>();

	private EContentAdapter contentAdapter;

	public BaseDomainRegistry() {
		BasicCommandStack commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, commandStack,
				new HashMap<Resource, Boolean>());
	}

	protected void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	protected void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.licensingReporter == reporter) {
			this.licensingReporter = SystemReporter.INSTANCE;
		}
	}

	protected void bindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		this.composedAdapterFactory = factoryProvider.getComposedAdapterFactory();
		editingDomain.setAdapterFactory(composedAdapterFactory);
	}

	protected void unbindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		this.composedAdapterFactory = null;
		editingDomain.setAdapterFactory(composedAdapterFactory);
	}

	protected void activate(Map<String, Object> properties) {
		domainName = String.valueOf(properties.get(EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME));
		contentAdapter = createContentAdapter();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		resourceSet.eAdapters().add(contentAdapter);
		loadResourceSet();
	}

	protected void loadResourceSet() {
		try {
			Path domainPath = getResourceSetPath();
			if (!Files.exists(domainPath)) {
				return;
			}
			List<String> lines = Files.readAllLines(domainPath);
			for (String line : lines) {
				registerSource(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected abstract Path getResourceSetPath() throws Exception;

	protected abstract DomainContentAdapter<I, ? extends EditingDomainRegistry<I>> createContentAdapter();

	protected void deactivate(Map<String, Object> properties) {
		saveResourceSet();
		editingDomain.getResourceSet().eAdapters().remove(contentAdapter);
	}

	protected void saveResourceSet() {
		try {
			Path domainPath = getResourceSetPath();
			if (!Files.exists(domainPath)) {
				Files.createFile(domainPath);
			}
			Files.write(domainPath, sources);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected ComposedAdapterFactory getComposedAdapterFactory() {
		return composedAdapterFactory;
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	protected Map<?, ?> getLoadOptions() {
		return new HashMap<>();
	}

	protected Map<?, ?> getSaveOptions() {
		return new HashMap<>();
	}

	public void loadSource(String source) throws Exception {
		URI uri = createURI(source);
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource resource = resourceSet.createResource(uri);
		resource.load(getLoadOptions());
	}

	public void unloadSource(String source) throws Exception {
		URI uri = createURI(source);
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource resource = resourceSet.getResource(uri, false);
		resource.unload();
		resourceSet.getResources().remove(resource);
	}

	protected URI createURI(String source) {
		return URI.createFileURI(source);
	}

	@Override
	public void registerSource(String source) {
		sources.add(source);
		try {
			loadSource(source);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.FINER, e.getMessage(), e);
		}
	}

	@Override
	public void unregisterSource(String source) {
		sources.remove(source);
		try {
			unloadSource(source);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.FINER, e.getMessage(), e);
		}
	}

	@Override
	public Iterable<String> getSources() {
		return Collections.unmodifiableList(sources);
	}

}
