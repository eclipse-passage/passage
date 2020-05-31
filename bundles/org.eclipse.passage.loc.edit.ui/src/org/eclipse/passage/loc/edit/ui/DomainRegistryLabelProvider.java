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
package org.eclipse.passage.loc.edit.ui;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.features.registry.FeatureRegistry;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.loc.features.core.Features;
import org.eclipse.passage.loc.internal.edit.ui.i18n.EditUiMessages;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.eclipse.swt.graphics.Image;

class DomainRegistryLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof FeatureRegistry) {
			return EditUiMessages.DomainRegistryLabelProvider_features;
		}
		if (element instanceof ProductRegistry) {
			return EditUiMessages.DomainRegistryLabelProvider_products;
		}
		if (element instanceof UserRegistry) {
			return EditUiMessages.DomainRegistryLabelProvider_users;
		}
		if (element instanceof LicenseRegistry) {
			return EditUiMessages.DomainRegistryLabelProvider_licenses;
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
		if (element instanceof EditingDomainRegistry<?>) {
			EditingDomainRegistry<?> registry = (EditingDomainRegistry<?>) element;
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
		if (lastSegment.contains(Features.DOMAIN_NAME)) {
			return LicensingImages.getImage(FeaturesPackage.eINSTANCE.getFeatureSet().getName());
		}
		if (lastSegment.contains(ProductsPackage.eNAME)) {
			return LicensingImages.getImage(ProductsPackage.eINSTANCE.getProductLine().getName());
		}
		if (lastSegment.contains(UsersPackage.eNAME)) {
			return LicensingImages.getImage(UsersPackage.eINSTANCE.getUserOrigin().getName());
		}
		if (lastSegment.contains(Licenses.DOMAIN_NAME)) {
			return LicensingImages.getImage(LicensesPackage.eINSTANCE.getLicensePlan().getName());
		}
		return null;
	}

}
