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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.emf.edit.BaseDomainRegistry;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.features.registry.FeatureRegistry;
import org.eclipse.passage.lic.features.registry.Features;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.licenses.registry.Licenses;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.products.registry.Products;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.lic.users.registry.Users;
import org.eclipse.swt.graphics.Image;

class DomainRegistryLabelProvider extends LabelProvider {

	public DomainRegistryLabelProvider(LicensingImages images) {
	}

	@Override
	public String getText(Object element) {
		if (element instanceof FeatureRegistry) {
			return "Features";
		}
		if (element instanceof ProductRegistry) {
			return "Products";
		}
		if (element instanceof UserRegistry) {
			return "Users";
		}
		if (element instanceof LicenseRegistry) {
			return "Licenses";
		}
		if (element instanceof Resource) {
			Resource resource = (Resource) element;
			URI uri = resource.getURI();
			return uri.toString();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof BaseDomainRegistry) {
			BaseDomainRegistry registry = (BaseDomainRegistry) element;
			return LicensingImages.getImage(registry.getContentClassifier().getName());
		}
		if (element instanceof Resource) {
			Resource resource = (Resource) element;
			URI uri = resource.getURI();
			return getImageByUri(uri);
		}
		return super.getImage(element);
	}

	private Image getImageByUri(URI uri) {
		if (uri == null) {
			return null;
		}
		String lastSegment = uri.lastSegment();
		if (lastSegment.endsWith(Features.FILE_EXTENSION_XMI)) {
			return LicensingImages.getImage(FeaturesPackage.eINSTANCE.getFeatureSet().getName());
		}
		if (lastSegment.endsWith(Products.FILE_EXTENSION_XMI)) {
			return LicensingImages.getImage(LicPackage.eINSTANCE.getProductLine().getName());
		}
		if (lastSegment.endsWith(Users.FILE_EXTENSION_XMI)) {
			return LicensingImages.getImage(LicPackage.eINSTANCE.getUserOrigin().getName());
		}
		if (lastSegment.endsWith(Licenses.FILE_EXTENSION_XMI)) {
			return LicensingImages.getImage(LicPackage.eINSTANCE.getLicensePack().getName());
		}
		return null;
	}

}
