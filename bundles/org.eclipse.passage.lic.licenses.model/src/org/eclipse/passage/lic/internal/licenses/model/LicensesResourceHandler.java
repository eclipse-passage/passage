/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.model;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

public class LicensesResourceHandler extends BasicResourceHandler {

	private final EnsureProductRef product;

	public LicensesResourceHandler() {
		product = new EnsureProductRef();
	}

	@Override
	public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		resource.getEObjectToExtensionMap().entrySet().forEach(this::convertEntry);
		resource.getContents().stream()//
				.filter(LicensePack.class::isInstance)//
				.map(LicensePack.class::cast).forEach(new AssignGrantIdentifiers());
	}

	private void convertEntry(Entry<EObject, AnyType> entry) {
		EObject key = entry.getKey();
		if (key instanceof LicensePack) {
			LicensePack pack = (LicensePack) key;
			SimpleFeatureRoute route = new SimpleFeatureRoute();
			route.add("productIdentifier", LicensesPackage.eINSTANCE.getProductRef_Product()); //$NON-NLS-1$
			route.add("productVersion", LicensesPackage.eINSTANCE.getProductRef_Version()); //$NON-NLS-1$
			new ApplyFeatureMap(entry.getValue().getAnyAttribute(), route).apply(product.apply(pack));
		}
	}

}
