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
package org.eclipse.passage.loc.edit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.DomainContentAdapter;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.runtime.registry.DescriptorRegistry;
import org.osgi.service.event.EventAdmin;

public abstract class EditingDomainBasedRegistry implements DescriptorRegistry, EditingDomainRegistry {
	
	//FIXME: AF find better place
	static {
		EPackage.Registry.INSTANCE.put("http://www.arsysop.ru/passage/lic/0.3.1", LicPackage.eINSTANCE); //$NON-NLS-1$
		EPackage.Registry.INSTANCE.put("http://www.arsysop.ru/passage/lic/0.3.2", LicPackage.eINSTANCE); //$NON-NLS-1$
	}

	protected EnvironmentInfo environmentInfo;

	protected EventAdmin eventAdmin;

	private String domainName;

	private ComposedAdapterFactory composedAdapterFactory;

	private AdapterFactoryEditingDomain editingDomain;

	private final List<String> sources = new ArrayList<>();

	private EContentAdapter contentAdapter;

	public EditingDomainBasedRegistry() {
		BasicCommandStack commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(composedAdapterFactory, commandStack,
				new HashMap<Resource, Boolean>());
	}

	protected void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	protected void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = null;
	}

	protected void bindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}

	protected void unbindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = null;
	}

	protected void bindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		this.composedAdapterFactory = factoryProvider.getComposedAdapterFactory();
		editingDomain.setAdapterFactory(composedAdapterFactory);
	}

	protected void unbindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		this.composedAdapterFactory = null;
		editingDomain.setAdapterFactory(composedAdapterFactory);
	}

	@Override
	public Path getBasePath() {
		String areaValue = environmentInfo.getProperty("user.home"); //$NON-NLS-1$
		Path passagePath = Paths.get(areaValue, LicensingPaths.FOLDER_LICENSING_BASE);
		try {
			Files.createDirectories(passagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passagePath;
	}

	protected void activate(Map<String, Object> properties) {
		domainName = String.valueOf(properties.get(DomainRegistryAccess.PROPERTY_DOMAIN_NAME));
		contentAdapter = createContentAdapter();
		editingDomain.getResourceSet().eAdapters().add(contentAdapter);
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

	protected Path getResourceSetPath() throws Exception {
		String areaValue = environmentInfo.getProperty(LicensingPaths.PROPERTY_OSGI_INSTALL_AREA);
		Path areaPath = Paths.get(new java.net.URI(areaValue));
		Path passagePath = areaPath.resolve(LicensingPaths.FOLDER_LICENSING_BASE);
		Files.createDirectories(passagePath);
		Path domainPath = passagePath.resolve(domainName);
		return domainPath;
	}

	protected abstract DomainContentAdapter<? extends EditingDomainRegistry> createContentAdapter();

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

	@Override
	public ComposedAdapterFactory getComposedAdapterFactory() {
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
