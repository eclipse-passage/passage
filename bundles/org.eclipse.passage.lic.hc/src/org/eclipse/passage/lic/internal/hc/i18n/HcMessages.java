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
package org.eclipse.passage.lic.internal.hc.i18n;

import org.eclipse.osgi.util.NLS;

public class HcMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.hc.i18n.HcMessages"; //$NON-NLS-1$

	public static String DecryptedConditions_no_transport_for_content_type;

	public static String DecryptedConditions_reading_error;

	public static String HttpClient_final_error_message;
	public static String HttpClient_not_ok_response;

	public static String LicensingServerCoordinates_settings_not_found;

	static {
		NLS.initializeMessages(BUNDLE_NAME, HcMessages.class);
	}
}
