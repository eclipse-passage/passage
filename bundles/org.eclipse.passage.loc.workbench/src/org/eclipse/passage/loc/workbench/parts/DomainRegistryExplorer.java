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
package org.eclipse.passage.loc.workbench.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.passage.lic.emf.ecore.ResourceSetProvider;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;
import org.eclipse.passage.loc.workbench.viewers.ResourceSetAdapter;
import org.eclipse.passage.loc.workbench.viewers.StructuredSelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class DomainRegistryExplorer {

	private final ResourceSetProvider resourceSetProvider;
	private final ComposedAdapterFactoryProvider composedAdapterFactoryProvider;
	private final ESelectionService selectionService;

	private ISelectionChangedListener selectionChangeListener;
	private TreeViewer viewer;
	private ResourceSetAdapter resourceSetAdapter;

	public DomainRegistryExplorer(ResourceSetProvider setProvider, ComposedAdapterFactoryProvider factoryProvider,
			ESelectionService selectionService) {
		super();
		this.resourceSetProvider = setProvider;
		this.composedAdapterFactoryProvider = factoryProvider;
		this.selectionService = selectionService;
	}

	public ResourceSetProvider getResourceSetProvider() {
		return resourceSetProvider;
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		Composite base = new Composite(parent, SWT.BORDER);
		base.setLayout(new GridLayout());
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		viewer = new TreeViewer(base);
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		AdapterFactory factory;
		if (composedAdapterFactoryProvider != null) {
			factory = composedAdapterFactoryProvider.getComposedAdapterFactory();
		} else {
			factory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		viewer.setContentProvider(new AdapterFactoryContentProvider(factory));
		viewer.setLabelProvider(new DomainRegistryLabelProvider(factory));
		selectionChangeListener = new StructuredSelectionListener(selectionService);
		viewer.addSelectionChangedListener(selectionChangeListener);
		ResourceSet resourceSet = resourceSetProvider.getResourceSet();
		resourceSetAdapter = new ResourceSetAdapter(viewer);
		resourceSet.eAdapters().add(resourceSetAdapter);
		resetInput();
	}

	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@PreDestroy
	public void dispose() {
		resourceSetProvider.getResourceSet().eAdapters().remove(resourceSetAdapter);
		viewer.removeSelectionChangedListener(selectionChangeListener);
	}

	protected void resetInput() {
		if (viewer != null && !viewer.getControl().isDisposed()) {
			ResourceSet resourceSet = resourceSetProvider.getResourceSet();
			ISelection selection = viewer.getSelection();
			viewer.setInput(resourceSet);
			if (selection.isEmpty()) {
				EList<Resource> resources = resourceSet.getResources();
				if (!resources.isEmpty()) {
					Resource resource = resources.get(0);
					selection = new StructuredSelection(resource);
				}
			}
			viewer.setSelection(selection);
		}
	}
}
