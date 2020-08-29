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
package org.eclipse.passage.loc.features.ui;

import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.features.ui.i18n.FeatureUiMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class FeaturesUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.features.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static FeatureDescriptor selectFeatureDescriptor(Shell shell, FeatureRegistry registry,
			FeatureDescriptor initial) {
		String classifier = FeaturesPackage.eINSTANCE.getFeature().getName();
		String title = FeatureUiMessages.FeaturesUi_select_feature_title;
		Iterable<? extends FeatureDescriptor> input = registry.getFeatures();
		Class<FeatureDescriptor> clazz = FeatureDescriptor.class;
		return LocWokbench.selectClassifier(shell, classifier, title, input, initial, clazz);
	}

}
