/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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

public class ProductsCoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages"; //$NON-NLS-1$
	public static String ProductOperatorServiceImpl_e_export_error;
	public static String ProductOperatorServiceImpl_e_invalid_product_version;
	public static String ProductOperatorServiceImpl_e_key_already_defined;
	public static String ProductOperatorServiceImpl_e_unable_to_create_keys;
	public static String ProductOperatorServiceImpl_ok_keys_exported;
	public static String ProductsSelectionCommandAdvisor_select_product;
	public static String ProductsSelectionCommandAdvisor_select_product_line;
	public static String ProductsSelectionCommandAdvisor_select_product_version;
	public static String ProductsSelectionCommandAdvisor_select_product_version_feature;
	public static String ProductDomain_instance_duplication_message;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ProductsCoreMessages.class);
	}

	private ProductsCoreMessages() {
	}
}
