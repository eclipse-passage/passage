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
 *     ArSysOp - further evolution
 *******************************************************************************/
package org.eclipse.passage.loc.internal.equinox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.emf.resource.ResourceLoadFailed;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;
import org.eclipse.passage.loc.internal.api.EditingDomainSource;
import org.eclipse.passage.loc.internal.api.OperatorGear;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;

@SuppressWarnings("restriction")
public abstract class BaseDomainRegistry<I> implements EditingDomainRegistry<I>, IEditingDomainProvider {

	protected String domainName;

	private Optional<EditingDomain> editingDomain = Optional.empty();

	private final List<URI> sources;

	private final EContentAdapter contentAdapter;

	private Optional<OperatorGearSupplier> gear = Optional.empty();

	public BaseDomainRegistry() {
		sources = new ArrayList<>();
		contentAdapter = createContentAdapter();
	}

	public void bindGear(OperatorGearSupplier supplier) {
		gear = Optional.of(supplier);
	}

	public void unbindGear(OperatorGearSupplier supplier) {
		if (gear.isEmpty()) {
			return;
		}
		if (gear.get().equals(supplier)) {
			gear = Optional.empty();
		}
	}

	protected final void activate(Map<String, Object> properties) {
		domainName = String.valueOf(properties.get(EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME));
		getEditingDomain().getResourceSet().eAdapters().add(contentAdapter);
		try {
			gear.stream()//
					.findFirst()//
					.map(OperatorGearSupplier::get)//
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

	protected final void deactivate(@SuppressWarnings("unused") Map<String, Object> properties) {
		try {
			gear.stream()//
					.findFirst()//
					.map(OperatorGearSupplier::get)//
					.map(OperatorGear::workspace)//
					.ifPresent(this::store);
		} catch (Exception e) {
			Platform.getLog(getClass()).error(e.getMessage(), e);
		}
		getEditingDomain().getResourceSet().eAdapters().remove(contentAdapter);
	}

	private void store(OperatorWorkspace workspace) {
		knownResources(workspace)//
				.memento(getSources().stream()//
						.map(URI::toString)//
						.collect(Collectors.toList()));
	}

	@Override
	public EditingDomain getEditingDomain() {
		if (editingDomain.isEmpty()) {
			editingDomain = gear.map(OperatorGearSupplier::get)//
					.map(OperatorGear::editingDomainSource)//
					.map(EditingDomainSource::create);
		}
		return editingDomain.orElse(null);
	}

	protected Map<?, ?> getLoadOptions() {
		return new HashMap<>();
	}

	protected Map<?, ?> getSaveOptions() {
		return new HashMap<>();
	}

	public ServiceInvocationResult<Boolean> loadSource(URI uri) {
		ResourceSet set = getEditingDomain().getResourceSet();
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
		ResourceSet resourceSet = getEditingDomain().getResourceSet();
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
