/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.passage.lic.emf.ecore.util.DelegatingEPackage;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@SuppressWarnings("restriction")
@Component
public class LicensesMigrator {

	@Activate
	public void activate() {
		migrate033();
		migrate040();
		migrate050();
		migrate100();
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
		EPackage.Registry.INSTANCE.put(nsUri, delegate);
	}

	private void migrate050() {
		String nsUri = "http://www.eclipse.org/passage/lic/licenses/0.5.0"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(nsUri, delegate);
	}

	private void migrate100() {
		String nsUri = "http://www.eclipse.org/passage/lic/licenses/1.0.0"; //$NON-NLS-1$
		LicensesPackage delegate = LicensesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(nsUri, delegate);
	}

}
