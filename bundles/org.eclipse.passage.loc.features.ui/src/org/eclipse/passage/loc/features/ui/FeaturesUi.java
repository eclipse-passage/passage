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
package org.eclipse.passage.loc.features.ui;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.registry.features.FeatureDescriptor;
import org.eclipse.passage.lic.registry.features.FeaturesRegistry;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class FeaturesUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.features.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static FeatureDescriptor selectFeatureDescriptor(Shell shell, ComposedAdapterFactoryProvider provider,
			FeaturesRegistry registry, FeatureDescriptor initial) {
		String classifier = LicPackage.eINSTANCE.getFeature().getName();
		String title = "Select Feature";
		Iterable<? extends FeatureDescriptor> input = registry.getFeatures();
		Class<FeatureDescriptor> clazz = FeatureDescriptor.class;
		ComposedAdapterFactory factory = provider.getComposedAdapterFactory();
		return LocWokbench.selectClassifier(shell, factory, classifier, title, input, initial, clazz);
	}
	
}
