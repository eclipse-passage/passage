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
package org.eclipse.passage.lic.internal.equinox.i18n;

import org.eclipse.osgi.util.NLS;

public class EquinoxMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages"; //$NON-NLS-1$

	public static String ComponentRequirements_requirement_for_resource;
	public static String BundleKeyKeeper_failed_to_find_file;
	public static String BundleKeyKeeper_failed_to_open_stream;
	public static String BundleRequirements_no_context;
	public static String BundleVendor_unknown_vendor;
	public static String LicensedApplication_application_context_service_unregistered;
	public static String LicensedApplication_no_application_context_service_ref;
	public static String LicensedApplication_no_bundle_context;
	public static String ProductContacts_defaults;
	public static String RequirementsFromCapability_no_attributes;
	public static String RequirementsFromCapability_no_feature_id;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EquinoxMessages.class);
	}

	private EquinoxMessages() {
	}
}
