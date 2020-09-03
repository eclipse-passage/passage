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
package org.eclipse.passage.loc.internal.emf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;
import org.eclipse.passage.loc.internal.api.diagnostic.code.ResourceLoadFailed;

@SuppressWarnings("restriction")
public abstract class BaseDomainRegistry<I> implements EditingDomainRegistry<I>, IEditingDomainProvider {

	protected String domainName;

	private AdapterFactoryEditingDomain editingDomain;

	private final List<String> sources = new ArrayList<>();

	private EContentAdapter contentAdapter;

	public BaseDomainRegistry() {
		BasicCommandStack commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), commandStack,
				new HashMap<Resource, Boolean>());
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
			Files.readAllLines(domainPath).forEach(this::registerSource);
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

	public ServiceInvocationResult<Boolean> loadSource(String source) {
		URI uri = createURI(source);
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource resource = resourceSet.createResource(uri);
		try {
			resource.load(getLoadOptions());
			return new BaseServiceInvocationResult<>(true);
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(new Trouble(//
					new ResourceLoadFailed(), NLS.bind(EmfMessages.BaseDomainRegistry_e_load_failed, source), e));
		}
	}

	public void unloadSource(String source) {
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
	public ServiceInvocationResult<Boolean> registerSource(String source) {
		sources.add(source);
		return loadSource(source);
	}

	@Override
	public void unregisterSource(String source) {
		sources.remove(source);
		unloadSource(source);
	}

	@Override
	public Iterable<String> getSources() {
		return Collections.unmodifiableList(sources);
	}

}
