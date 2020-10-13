/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.i18n;

import org.eclipse.osgi.util.NLS;

public class AccessMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages"; //$NON-NLS-1$
	public static String EquinoxPassage_no_framework;
	public static String EquinoxPassage_foreign_framework;
	public static String RequirementCapabilitiesFromManifest_ioe;
	public static String RequirementsFromBundle_no_capabilities;
	public static String RequirementsFromBundle_no_wiring;
	public static String RequirementsFromManifest_failure;
	public static String RequirementsFromManifest_no_manifest;
	public static String RequirementsFromManifest_reading_error;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, AccessMessages.class);
	}

	private AccessMessages() {
	}
}
