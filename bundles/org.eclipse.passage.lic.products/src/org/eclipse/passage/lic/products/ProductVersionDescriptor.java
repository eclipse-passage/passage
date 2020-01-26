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
 * A <code>"Product Version"</code> corresponds to the binaries you are planning
 * to release or already released for the <code>"Product"</code>.It is
 * recommended to create the <code>"Product Version"</code> for each external
 * <code>"Product"</code> shipment.The <code>"version"</code> attribute of the
 * <code>"Product Version"</code> is important for the
 * <code>"Licensing Configuration"</code>.The 'Product Version' completes the
 * definition of the <code>"Licensing Configuration"</code> that will be checked
 * in the user environment.
 * <p>
 *
 */
public interface ProductVersionDescriptor {

	/**
	 * Returns the version of this product version. This is the value of its
	 * <code>"version"</code> attribute.
	 *
	 * @return the version
	 */
	String getVersion();

	/**
	 * Returns the name of this product version. This is the value of its
	 * <code>"name"</code> attribute.
	 *
	 * @return the name
	 * @since 0.5.0
	 */
	String getName();

	/**
	 * Returns the "what's new" of this product version. This is the value of its
	 * <code>"news"</code> attribute.
	 *
	 * @return the news
	 */
	String getNews();

	/**
	 * Returns the installation token (the path to the public key) of this product
	 * version. This is the value of its <code>"installationToken"</code> attribute.
	 *
	 * @return the installation token
	 */
	String getInstallationToken();

	/**
	 * Returns the secure token (the path to the private key) of this product
	 * version. This is the value of its <code>"secureToken"</code> attribute.
	 *
	 * @return the secure token
	 */
	String getSecureToken();

	ProductDescriptor getProduct();

	Iterable<? extends ProductVersionFeatureDescriptor> getProductVersionFeatures();

}
