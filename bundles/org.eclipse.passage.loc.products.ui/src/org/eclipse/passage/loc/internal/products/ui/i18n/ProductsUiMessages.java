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
package org.eclipse.passage.loc.internal.products.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class ProductsUiMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.products.ui.i18n.ProductsUiMessages"; //$NON-NLS-1$
	public static String ProductExportHandler_message_error;
	public static String ProductExportHandler_title_error;
	public static String ProductExportHandler_title_ok;
	public static String ProductsUi_select_product;
	public static String ProductsUi_select_product_line;
	public static String ProductsUi_select_product_version;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ProductsUiMessages.class);
	}

	private ProductsUiMessages() {
	}
}
