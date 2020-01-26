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
package org.eclipse.passage.lic.products;

/**
 * <p>
 * A <code>"Product"</code> corresponds to the to the ready-to-use item in your
 * offering. The <code>"identifier"</code> attribute of the
 * <code>"Product"</code> is important for the
 * <code>"Licensing Configuration"</code>.
 * <p>
 *
 */
public interface ProductDescriptor {

	/**
	 * Returns the identifier of this product. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the name of this product. This is the value of its
	 * <code>"name"</code> attribute.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Returns the description of this product. This is the value of its
	 * <code>"description"</code> attribute.
	 *
	 * @return the description
	 */
	String getDescription();

	ProductLineDescriptor getProductLine();

	Iterable<? extends ProductVersionDescriptor> getProductVersions();

}
