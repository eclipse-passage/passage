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

import org.eclipse.passage.lic.emf.edit.FeatureDomainRegistry;
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.runtime.features.FeatureDescriptor;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class FeaturesUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.features.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static FeatureDescriptor selectFeatureDescriptor(Shell shell, LicensingImages images,
			FeatureDomainRegistry registry, FeatureDescriptor initial) {
		String classifier = LicPackage.eINSTANCE.getFeature().getName();
		String title = "Select Feature";
		Iterable<FeatureDescriptor> input = registry.getFeatures();
		Class<FeatureDescriptor> clazz = FeatureDescriptor.class;
		return LocWokbench.selectClassifier(shell, images, registry, classifier, title, input, initial, clazz);
	}
	
}
