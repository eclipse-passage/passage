/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.features.model.migration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.internal.emf.migration.DelegateClassifiers;

public final class FeaturesResourceHandler extends MigratingResourceHandler {

	@Override
	protected void ensureMigrations() {
		migrate033();
		migrate040();
	}

	@Override
	protected void convertEntry(Entry<EObject, AnyType> entry) {
		// not yet needed
	}

	private void migrate033() {
		String nsUri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		FeaturesPackage delegate = FeaturesPackage.eINSTANCE;
		List<String> classifiers = new ArrayList<>();
		classifiers.add(delegate.getFeatureSet().getName());
		classifiers.add(delegate.getFeature().getName());
		classifiers.add(delegate.getFeatureVersion().getName());
		new DelegateClassifiers(nsUri).delegate(delegate, classifiers);
	}

	private void migrate040() {
		String nsUri = "http://www.eclipse.org/passage/lic/features/0.4.0"; //$NON-NLS-1$
		FeaturesPackage delegate = FeaturesPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(nsUri, ns -> delegate);
	}
}
