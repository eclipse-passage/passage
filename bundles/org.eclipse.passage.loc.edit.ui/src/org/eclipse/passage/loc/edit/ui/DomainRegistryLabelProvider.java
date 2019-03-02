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
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.runtime.features.FeaturesRegistry;
import org.eclipse.passage.lic.runtime.features.Features;
import org.eclipse.passage.lic.runtime.licenses.LicensesRegistry;
import org.eclipse.passage.lic.runtime.licenses.Licenses;
import org.eclipse.passage.lic.runtime.products.ProductsRegistry;
import org.eclipse.passage.lic.runtime.products.Products;
import org.eclipse.passage.lic.runtime.users.UsersRegistry;
import org.eclipse.passage.lic.runtime.users.Users;
import org.eclipse.passage.loc.edit.EditingDomainBasedRegistry;
import org.eclipse.swt.graphics.Image;

class DomainRegistryLabelProvider extends LabelProvider {

	private LicensingImages images;

	public DomainRegistryLabelProvider(LicensingImages images) {
		this.images = images;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof FeaturesRegistry) {
			return "Features";
		}
		if (element instanceof ProductsRegistry) {
			return "Products";
		}
		if (element instanceof UsersRegistry) {
			return "Users";
		}
		if (element instanceof LicensesRegistry) {
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
		if (element instanceof FeaturesRegistry) {
			return images.getImage(LicPackage.eINSTANCE.getFeatureSet().getName());
		}
		if (element instanceof EditingDomainBasedRegistry) {
			EditingDomainBasedRegistry registry = (EditingDomainBasedRegistry) element;
			return images.getImage(registry.getContentClassifier().getName());
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
			return images.getImage(LicPackage.eINSTANCE.getFeatureSet().getName());
		}
		if (lastSegment.endsWith(Products.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getProductLine().getName());
		}
		if (lastSegment.endsWith(Users.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getUserOrigin().getName());
		}
		if (lastSegment.endsWith(Licenses.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getLicensePack().getName());
		}
		return null;
	}

}
