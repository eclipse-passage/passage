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
package org.eclipse.passage.lic.internal.licenses.model.migration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.ecore.util.DelegatingEPackage;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

public class LicensesResourceHandler extends MigratingResourceHandler {

	@Override
	protected void ensureMigrations() {
		migrate033();
		migrate040();
		migrate050();
		migrate100();
		migrate110();
	}

	@Override
	public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		super.postLoad(resource, inputStream, options);
		resource.getContents().stream()//
				.filter(LicensePack.class::isInstance)//
				.map(LicensePack.class::cast).forEach(new AssignGrantIdentifiers());
	}

	@Override
	protected void convertEntry(Entry<EObject, AnyType> entry) {
		EObject key = entry.getKey();
		AnyType value = entry.getValue();
	}

	private void migrate033() {
		String nsUri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		List<String> classifiers = new ArrayList<>();
		classifiers.add(delegate.getLicensePack().getName());
		classifiers.add(delegate.getLicenseGrant().getName());
		DelegatingEPackage.delegate(nsUri, delegate, classifiers);
	}

	private void migrate040() {
		String nsUri = "http://www.eclipse.org/passage/lic/licenses/0.4.0"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(nsUri, ns -> delegate);
	}

	private void migrate050() {
		String nsUri = "http://www.eclipse.org/passage/lic/licenses/0.5.0"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(nsUri, ns -> delegate);
	}

	private void migrate100() {
		String nsUri = "http://www.eclipse.org/passage/lic/licenses/1.0.0"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(nsUri, ns -> delegate);
	}

	private void migrate110() {
		String nsUri = "http://www.eclipse.org/passage/lic/licenses/1.1.0"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(nsUri, ns -> delegate);
	}

}
