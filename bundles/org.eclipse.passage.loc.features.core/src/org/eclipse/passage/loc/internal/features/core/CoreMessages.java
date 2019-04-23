/*******************************************************************************
 * Copyright (c) 2019 ArSysOp and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Elena Parovyshnaya <elena.parovyshnaya@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.features.core;

import org.eclipse.osgi.util.NLS;

public class CoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.features.core.FeaturesCoreInternalMessages"; //$NON-NLS-1$
	public static String FeatureSetClassifierInitializer_feature_set;
	public static String FeatureSetClassifierInitializer_new_feature_set_messahe;
	public static String FeatureSetClassifierInitializer_new_feature_set_name;
	public static String FeatureSetClassifierInitializer_new_file_message;
	public static String FeaturesSelectionCommandAdvisor_select_feature;
	public static String FeaturesSelectionCommandAdvisor_select_feature_set;
	public static String FeaturesSelectionCommandAdvisor_select_feature_version;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, CoreMessages.class);
	}

	private CoreMessages() {
	}
}
