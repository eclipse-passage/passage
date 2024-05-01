/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.api.ProductVersionFeature;

public interface ProductRegistry {

	Collection<ProductLine> productLines();

	Optional<ProductLine> productLine(String id);

	Collection<Product> products();

	Optional<Product> product(String id);

	Collection<ProductVersion> productVersions();

	Optional<ProductVersion> productVersion(String productId, String version);

	Collection<ProductVersionFeature> productVersionFeatures();

}
