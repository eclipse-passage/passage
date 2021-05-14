/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.emf.i18n;

import org.eclipse.osgi.util.NLS;

public final class EmfMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.emf.i18n.EmfMessages"; //$NON-NLS-1$

	public static String BaseDomainRegistry_e_load_failed;

	public static String BaseDomainRegistry_e_load_workspace;
	public static String BaseDomainRegistry_ok_load;
	public static String BaseDomainRegistry_ok_unload;
	public static String LicensingEcore_inpur_header;
	public static String LicensingEcore_input_entry;
	public static String LicensingEcore_input_invalid;
	public static String ResourceLoadFailed_explanation;
	public static String ResourceSaveFailed_explanation;

	public static String XmiToEObject_failed_xmi_read;
	public static String XmiToEObject_unexpected_amount;
	public static String XmiToEObject_unexpected_type;
	public static String EObjectToBytes_failure;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EmfMessages.class);
	}

	private EmfMessages() {
	}
}
