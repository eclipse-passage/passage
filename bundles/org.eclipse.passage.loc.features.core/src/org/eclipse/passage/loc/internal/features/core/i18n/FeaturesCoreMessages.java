/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Elena Parovyshnaya <elena.parovyshnaya@gmail.com> - initial API and implementation
 *     ArSysOp - ongoing support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.features.core.i18n;

import org.eclipse.osgi.util.NLS;

public class FeaturesCoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.features.core.i18n.FeaturesCoreMessages"; //$NON-NLS-1$
	public static String FeaturesSelectionCommandAdvisor_select_feature;
	public static String FeaturesSelectionCommandAdvisor_select_feature_set;
	public static String FeaturesSelectionCommandAdvisor_select_feature_version;
	public static String FeatureDomain_instance_duplication_message;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, FeaturesCoreMessages.class);
	}

	private FeaturesCoreMessages() {
	}
}
