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
package org.eclipse.passage.loc.edit.ui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.registry.FeatureRegistry;
import org.eclipse.passage.lic.features.registry.FeatureRegistryEvents;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistryEvents;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.products.registry.ProductRegistryEvents;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.lic.users.registry.UserRegistryEvents;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class DomainRegistryExplorerPart {

	private List<Object> registries = new ArrayList<>();

	private TreeViewer treeViewer;

	@Inject
	public DomainRegistryExplorerPart(IEclipseContext context) {
		this.registries.add(context.get(FeatureRegistry.class));
		this.registries.add(context.get(ProductRegistry.class));
		this.registries.add(context.get(UserRegistry.class));
		this.registries.add(context.get(LicenseRegistry.class));
	}

	@PostConstruct
	public void postConstruct(Composite parent, IEclipseContext context) {

		Composite area = new Composite(parent, SWT.NONE);
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setLayout(new GridLayout(1, false));
		treeViewer = createRegistryTree(area);
		ESelectionService selectionService = context.get(ESelectionService.class);
		treeViewer.addSelectionChangedListener(e -> {
			ISelection selection = e.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structured = (IStructuredSelection) selection;
				if (structured.size() == 1) {
					selectionService.setSelection(structured.getFirstElement());
				} else {
					selectionService.setSelection(structured.toArray());
				}
			}
		});
		treeViewer.setInput(registries);
	}

	private TreeViewer createRegistryTree(Composite area) {
		TreeViewer treeView = new TreeViewer(area);
		treeView.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		treeView.setAutoExpandLevel(2);
		treeView.setContentProvider(new DomainRegistryContentProvider());
		treeView.setLabelProvider(new DomainRegistryLabelProvider());
		return treeView;
	}

	@Inject
	@Optional
	public void createFeatureSet(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_CREATE) FeatureSetDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void deleteFeatureSet(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_DELETE) FeatureSetDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void updateFeatureSet(
			@UIEventTopic(FeatureRegistryEvents.FEATURE_SET_UPDATE) FeatureSetDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void createProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_CREATE) ProductLineDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void deleteProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_DELETE) ProductLineDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void updatedProductLine(
			@UIEventTopic(ProductRegistryEvents.PRODUCT_LINE_UPDATE) ProductLineDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void createUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_CREATE) UserOriginDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void deleteUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_DELETE) UserOriginDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void updateUserOrigin(@UIEventTopic(UserRegistryEvents.USER_ORIGIN_UPDATE) UserOriginDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void createLicensePack(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PACK_CREATE) LicensePackDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void deleteLicensePack(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PACK_DELETE) LicensePackDescriptor descriptor) {
		treeViewer.refresh();
	}

	@Inject
	@Optional
	public void updateLicensePack(
			@UIEventTopic(LicenseRegistryEvents.LICENSE_PACK_UPDATE) LicensePackDescriptor descriptor) {
		treeViewer.refresh();
	}

}
