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
package org.eclipse.passage.loc.internal.features.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class FeatureUiMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.features.ui.i18n.FeatureUiMessages"; //$NON-NLS-1$
	public static String FeaturesUi_select_feature_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, FeatureUiMessages.class);
	}

	private FeatureUiMessages() {
	}
}
