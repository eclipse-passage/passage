/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.products.core.i18n;

import org.eclipse.osgi.util.NLS;

public class ConverterMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.products.core.i18n.ConverterMessages"; //$NON-NLS-1$
	public static String ConvertKeysReport_e_pair;
	public static String ConvertKeysReport_e_product;
	public static String ConvertKeysReport_e_reading;
	public static String ConvertKeysReport_e_scan;
	public static String ConvertKeysReport_e_storing;
	public static String ConvertKeysReport_no_locator;
	public static String ConvertKeysReport_success;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ConverterMessages.class);
	}

	private ConverterMessages() {
	}
}
