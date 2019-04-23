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
package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.osgi.util.NLS;

public class EquinoxMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.equinox.EquinoxMessages"; //$NON-NLS-1$

	public static String ApplicationConfigurations_contact_defaults;

	public static String EquinoxPaths_uri_retrieval_error;
	public static String EquinoxRestrictions_feature_is_licensed;
	public static String EquinoxRestrictions_feature_is_not_licensed;
	public static String EquinoxRestrictions_title;

	public static String ComponentConfigurationResolver_error_invalid_bundle_context;
	public static String ComponentConfigurationResolver_error_invalid_component_rt;
	public static String BundleCapabilityResolver_error;
	public static String BundleCapabilityResolver_error_bundle_context;
	public static String EquinoxRestrictionExecutorRegistry_error_name;
	public static String EquinoxRestrictionExecutorRegistry_error_title;
	public static String EquinoxRestrictionExecutorRegistry_fatal_name;
	public static String EquinoxRestrictionExecutorRegistry_fatal_title;
	public static String EquinoxRestrictionExecutorRegistry_info_name;
	public static String EquinoxRestrictionExecutorRegistry_info_title;
	public static String EquinoxRestrictionExecutorRegistry_warning_name;
	public static String EquinoxRestrictionExecutorRegistry_warning_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EquinoxMessages.class);
	}

	private EquinoxMessages() {
	}
}
