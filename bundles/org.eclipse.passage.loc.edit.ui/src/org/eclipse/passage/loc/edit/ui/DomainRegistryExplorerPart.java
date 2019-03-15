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
import org.eclipse.passage.lic.features.FeaturesEvents;
import org.eclipse.passage.lic.features.FeaturesRegistry;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductsEvents;
import org.eclipse.passage.lic.products.ProductsRegistry;
import org.eclipse.passage.lic.registry.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.registry.licenses.LicensesEvents;
import org.eclipse.passage.lic.registry.licenses.LicensesRegistry;
import org.eclipse.passage.lic.registry.users.UserOriginDescriptor;
import org.eclipse.passage.lic.registry.users.UsersEvents;
import org.eclipse.passage.lic.registry.users.UsersRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class DomainRegistryExplorerPart {

	private List<Object> registries = new ArrayList<>();

	private TreeViewer treeView;
	private LicensingImages licensingImages;

	@Inject
	public DomainRegistryExplorerPart(IEclipseContext context) {
		this.registries.add(context.get(FeaturesRegistry.class));
		this.registries.add(context.get(ProductsRegistry.class));
		this.registries.add(context.get(UsersRegistry.class));
		this.registries.add(context.get(LicensesRegistry.class));
		this.licensingImages = context.get(LicensingImages.class);
	}

	@PostConstruct
	public void postConstruct(Composite parent, IEclipseContext context) {

		Composite area = new Composite(parent, SWT.NONE);
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setLayout(new GridLayout(1, false));
		treeView = createRegistryTree(area);
		ESelectionService selectionService = context.get(ESelectionService.class);
		treeView.addSelectionChangedListener(e -> {
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
		treeView.setInput(registries);
	}

	private TreeViewer createRegistryTree(Composite area) {
		TreeViewer treeView = new TreeViewer(area);
		treeView.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		treeView.setAutoExpandLevel(2);
		treeView.setContentProvider(new DomainRegistryContentProvider());
		treeView.setLabelProvider(new DomainRegistryLabelProvider(licensingImages));
		return treeView;
	}

	@Inject
	@Optional
	public void createFeatureSet(@UIEventTopic(FeaturesEvents.FEATURE_SET_CREATE) FeatureSetDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void deleteFeatureSet(@UIEventTopic(FeaturesEvents.FEATURE_SET_DELETE) FeatureSetDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void updateFeatureSet(@UIEventTopic(FeaturesEvents.FEATURE_SET_UPDATE) FeatureSetDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void createProductLine(@UIEventTopic(ProductsEvents.PRODUCT_LINE_CREATE) ProductLineDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void deleteProductLine(@UIEventTopic(ProductsEvents.PRODUCT_LINE_DELETE) ProductLineDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void updatedProductLine(@UIEventTopic(ProductsEvents.PRODUCT_LINE_UPDATE) ProductLineDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void createUserOrigin(@UIEventTopic(UsersEvents.USER_ORIGIN_CREATE) UserOriginDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void deleteUserOrigin(@UIEventTopic(UsersEvents.USER_ORIGIN_DELETE) UserOriginDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void updateUserOrigin(@UIEventTopic(UsersEvents.USER_ORIGIN_UPDATE) UserOriginDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void createLicensePack(@UIEventTopic(LicensesEvents.LICENSE_PACK_CREATE) LicensePackDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void deleteLicensePack(@UIEventTopic(LicensesEvents.LICENSE_PACK_DELETE) LicensePackDescriptor descriptor) {
		treeView.refresh();
	}

	@Inject
	@Optional
	public void updateLicensePack(@UIEventTopic(LicensesEvents.LICENSE_PACK_UPDATE) LicensePackDescriptor descriptor) {
		treeView.refresh();
	}

}
