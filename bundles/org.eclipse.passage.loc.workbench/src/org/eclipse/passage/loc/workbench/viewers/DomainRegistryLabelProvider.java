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
package org.eclipse.passage.loc.workbench.viewers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.registry.features.FeatureDescriptor;
import org.eclipse.passage.lic.registry.features.FeatureVersionDescriptor;
import org.eclipse.passage.lic.registry.products.ProductDescriptor;
import org.eclipse.passage.lic.registry.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.registry.products.ProductVersionFeatureDescriptor;
import org.eclipse.swt.graphics.Image;

public class DomainRegistryLabelProvider extends LabelProvider {
	
	private final AdapterFactoryLabelProvider delegate;
	
	public DomainRegistryLabelProvider(AdapterFactory adapterFactory) {
		super();
		this.delegate = new AdapterFactoryLabelProvider(adapterFactory);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Resource) {
			return LicensingImages.getImage(LicensingImages.IMG_DEFAULT);
		}
		return delegate.getImage(element);
	}
	
	@Override
	public String getText(Object element) {
		//FIXME: provide "name" feature for ProductVersion
		if (element instanceof ProductVersionDescriptor) {
			ProductVersionDescriptor productVersion = (ProductVersionDescriptor) element;
			ProductDescriptor product = productVersion.getProduct();
			return product.getName() + ' ' + productVersion.getVersion();
		}
		if (element instanceof ProductVersionFeatureDescriptor) {
			ProductVersionFeatureDescriptor productVersionFeature = (ProductVersionFeatureDescriptor) element;
			String text = getText(productVersionFeature.getProductVersion());
			return text + ' ' + ':' + ' ' + productVersionFeature.getFeatureIdentifier() + ' ' + productVersionFeature.getFeatureVersion();
		}

		//FIXME: AF: move to LIC
		if (element instanceof FeatureVersionDescriptor) {
			FeatureVersionDescriptor productVersion = (FeatureVersionDescriptor) element;
			FeatureDescriptor feature = productVersion.getFeature();
			return feature.getName() + ' ' + productVersion.getVersion();
		}
		if (element instanceof LicensePack) {
			LicensePack pack = (LicensePack) element;
			String packId = pack.getIdentifier();
			String productId = pack.getProductIdentifier();
			String productVersion = pack.getProductVersion();
			return packId + ' ' + '(' + productId + ' ' + productVersion + ')';
		}
		return delegate.getText(element);
	}

}
