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

public final class IssuingMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.products.core.i18n.IssuingMessages"; //$NON-NLS-1$
	public static String ProductKeys_keys_no_service;
	public static String ProductKeys_keys_no_storage_for_product;
	public static String ProductKeys_keys_reading_failed;

	static {
		NLS.initializeMessages(BUNDLE_NAME, IssuingMessages.class);
	}

	private IssuingMessages() {
	}
}
