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
package org.eclipse.passage.lic.internal.hc.i18n;

import org.eclipse.osgi.util.NLS;

public final class AccessMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.hc.i18n.AccessMessages"; //$NON-NLS-1$

	public static String AccessPacks_failed_on_file;
	public static String AccessPacks_files_gaining_failed;
	public static String AccessPacks_insufficient_configuration;
	public static String AccessPacks_no_key_keeper;
	public static String AccessPacks_no_stream_codec;

	public static String EObjectFromXmiResponse_unexpected_content_type;

	public static String RemoteService_no_server;

	public static String RequestParameters_encoding_failed;

	public static String Request_failed_to_compose_url;

	public static String HttpClient_failure;

	static {
		NLS.initializeMessages(BUNDLE_NAME, AccessMessages.class);
	}

	private AccessMessages() {
	}
}
