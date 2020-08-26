/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.products;

import static org.eclipse.passage.loc.internal.api.LicensingEvents.CREATE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.DELETE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.READ;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.UPDATE;

/**
 * Product registry events and event topic definitions.
 *
 * @since 0.4.0
 */
public final class ProductRegistryEvents {

	/**
	 * Base name of all Products events
	 */
	public static final String PRODUCTS_TOPIC_BASE = "org/eclipse/passage/lic/products/registry"; //$NON-NLS-1$

	/**
	 * Base name of all Product Line events
	 */
	public static final String PRODUCT_LINE_TOPIC_BASE = PRODUCTS_TOPIC_BASE + TOPIC_SEP + "ProductLine"; //$NON-NLS-1$

	/**
	 * Product Line <code>create</code> event
	 */
	public static final String PRODUCT_LINE_CREATE = PRODUCT_LINE_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * Product Line <code>read</code> event
	 */
	public static final String PRODUCT_LINE_READ = PRODUCT_LINE_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * Product Line <code>update</code> event
	 */
	public static final String PRODUCT_LINE_UPDATE = PRODUCT_LINE_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * Product Line <code>delete</code> event
	 */
	public static final String PRODUCT_LINE_DELETE = PRODUCT_LINE_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all Product events
	 */
	public static final String PRODUCT_TOPIC_BASE = PRODUCTS_TOPIC_BASE + TOPIC_SEP + "Product"; //$NON-NLS-1$

	/**
	 * Product <code>create</code> event
	 */
	public static final String PRODUCT_CREATE = PRODUCT_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * Product <code>read</code> event
	 */
	public static final String PRODUCT_READ = PRODUCT_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * Product <code>update</code> event
	 */
	public static final String PRODUCT_UPDATE = PRODUCT_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * Product <code>delete</code> event
	 */
	public static final String PRODUCT_DELETE = PRODUCT_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all Product Version events
	 */
	public static final String PRODUCT_VERSION_TOPIC_BASE = PRODUCTS_TOPIC_BASE + TOPIC_SEP + "ProductVersion"; //$NON-NLS-1$

	/**
	 * Product Version <code>create</code> event
	 */
	public static final String PRODUCT_VERSION_CREATE = PRODUCT_VERSION_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * Product Version <code>read</code> event
	 */
	public static final String PRODUCT_VERSION_READ = PRODUCT_VERSION_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * Product Version <code>update</code> event
	 */
	public static final String PRODUCT_VERSION_UPDATE = PRODUCT_VERSION_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * Product Version <code>delete</code> event
	 */
	public static final String PRODUCT_VERSION_DELETE = PRODUCT_VERSION_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all Product Version Feature events
	 */
	public static final String PRODUCT_VERSION_FEATURE_TOPIC_BASE = PRODUCTS_TOPIC_BASE + TOPIC_SEP
			+ "ProductVersionFeature"; //$NON-NLS-1$

	/**
	 * Product Version Feature <code>create</code> event
	 */
	public static final String PRODUCT_VERSION_FEATURE_CREATE = PRODUCT_VERSION_FEATURE_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * Product Version Feature <code>read</code> event
	 */
	public static final String PRODUCT_VERSION_FEATURE_READ = PRODUCT_VERSION_FEATURE_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * Product Version Feature <code>update</code> event
	 */
	public static final String PRODUCT_VERSION_FEATURE_UPDATE = PRODUCT_VERSION_FEATURE_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * Product Version Feature <code>delete</code> event
	 */
	public static final String PRODUCT_VERSION_FEATURE_DELETE = PRODUCT_VERSION_FEATURE_TOPIC_BASE + TOPIC_SEP + DELETE;

	private ProductRegistryEvents() {
		// block
	}

}
