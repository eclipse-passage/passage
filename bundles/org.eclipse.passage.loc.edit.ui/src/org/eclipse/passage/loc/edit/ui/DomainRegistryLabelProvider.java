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
import org.eclipse.passage.lic.registry.FeatureRegistry;
import org.eclipse.passage.lic.registry.FeaturesRegistry;
import org.eclipse.passage.lic.registry.LicenseRegistry;
import org.eclipse.passage.lic.registry.LicensesRegistry;
import org.eclipse.passage.lic.registry.ProductRegistry;
import org.eclipse.passage.lic.registry.ProductsRegistry;
import org.eclipse.passage.lic.registry.UserRegistry;
import org.eclipse.passage.lic.registry.UsersRegistry;
import org.eclipse.passage.loc.edit.EditingDomainBasedRegistry;
import org.eclipse.swt.graphics.Image;

class DomainRegistryLabelProvider extends LabelProvider {

	private LicensingImages images;

	public DomainRegistryLabelProvider(LicensingImages images) {
		this.images = images;
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
		if (element instanceof FeatureRegistry) {
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
		if (lastSegment.endsWith(FeaturesRegistry.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getFeatureSet().getName());
		}
		if (lastSegment.endsWith(ProductsRegistry.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getProductLine().getName());
		}
		if (lastSegment.endsWith(UsersRegistry.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getUserOrigin().getName());
		}
		if (lastSegment.endsWith(LicensesRegistry.FILE_EXTENSION_XMI)) {
			return images.getImage(LicPackage.eINSTANCE.getLicensePack().getName());
		}
		return null;
	}

}
