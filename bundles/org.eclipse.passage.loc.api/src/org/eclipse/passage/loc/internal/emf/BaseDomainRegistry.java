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
package org.eclipse.passage.loc.internal.emf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
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
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.emf.resource.ResourceLoadFailed;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;
import org.eclipse.passage.loc.internal.api.OperatorGear;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;

@SuppressWarnings("restriction")
public abstract class BaseDomainRegistry<I> implements EditingDomainRegistry<I>, IEditingDomainProvider {

	protected String domainName;

	private final AdapterFactoryEditingDomain editingDomain;

	private final List<URI> sources;

	private final EContentAdapter contentAdapter;

	private final List<OperatorGearSupplier> gear;

	public BaseDomainRegistry() {
		sources = new ArrayList<>();
		gear = new ArrayList<>(1);
		BasicCommandStack commandStack = new BasicCommandStack();
		editingDomain = new AdapterFactoryEditingDomain(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), commandStack,
				new HashMap<Resource, Boolean>());
		contentAdapter = createContentAdapter();
	}

	public void bindGear(OperatorGearSupplier supplier) {
		gear.add(supplier);
	}

	public void unbindGear(OperatorGearSupplier supplier) {
		gear.remove(supplier);
	}

	protected void activate(Map<String, Object> properties) {
		domainName = String.valueOf(properties.get(EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME));
		editingDomain.getResourceSet().eAdapters().add(contentAdapter);
		try {
			gear.stream()//
					.findFirst()//
					.map(OperatorGearSupplier::gear)//
					.map(OperatorGear::workspace)//
					.ifPresent(this::load);
		} catch (Exception e) {
			Platform.getLog(getClass()).error(e.getMessage(), e);
		}
	}

	private void load(OperatorWorkspace workspace) {
		knownResources(workspace).all().stream()//
				.filter(this::emfResource)//
				.map(ResourceHandle::uri)//
				.map(URI::createURI)//
				.forEach(this::registerSource);
	}

	protected abstract boolean emfResource(ResourceHandle handle);

	protected abstract KnownResources knownResources(OperatorWorkspace workspace);

	protected void logDiagnostic(Diagnostic diagnostic) {
		Platform.getLog(getClass()).error(new DiagnosticExplained(diagnostic).get());
	}

	protected abstract DomainContentAdapter<I, ? extends EditingDomainRegistry<I>> createContentAdapter();

	protected void deactivate(Map<String, Object> properties) {
		try {
			gear.stream()//
					.findFirst()//
					.map(OperatorGearSupplier::gear)//
					.map(OperatorGear::workspace)//
					.ifPresent(this::store);
		} catch (Exception e) {
			Platform.getLog(getClass()).error(e.getMessage(), e);
		}
		editingDomain.getResourceSet().eAdapters().remove(contentAdapter);
	}

	private void store(OperatorWorkspace workspace) {
		knownResources(workspace)//
				.memento(getSources().stream()//
						.map(URI::toString)//
						.collect(Collectors.toList()));
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

	public ServiceInvocationResult<Boolean> loadSource(URI uri) {
		ResourceSet set = editingDomain.getResourceSet();
		Resource resource = createResource(uri);
		set.getResources().add(resource);
		try {
			resource.load(getLoadOptions());
			resource.eAdapters().add(contentAdapter);
			return new BaseServiceInvocationResult<>(true);
		} catch (IOException e) {
			return new BaseServiceInvocationResult<>(new Trouble(//
					new ResourceLoadFailed(), NLS.bind(EmfMessages.BaseDomainRegistry_e_load_failed, uri), e));
		}
	}

	protected abstract Resource createResource(URI uri);

	public void unloadSource(URI uri) {
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Resource resource = resourceSet.getResource(uri, false);
		resource.unload();
		resourceSet.getResources().remove(resource);
	}

	@Override
	public ServiceInvocationResult<Boolean> registerSource(URI uri) {
		sources.add(uri);
		return loadSource(uri);
	}

	@Override
	public void unregisterSource(URI uri) {
		sources.remove(uri);
		unloadSource(uri);
	}

	@Override
	public List<URI> getSources() {
		return Collections.unmodifiableList(sources);
	}

}
