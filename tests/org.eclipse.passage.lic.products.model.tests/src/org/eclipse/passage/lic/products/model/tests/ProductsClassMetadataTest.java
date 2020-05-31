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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.products.model.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.products.model.ProductsClassMetadata;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.junit.Test;

public class ProductsClassMetadataTest {

	private final ProductsClassMetadata metadata;

	public ProductsClassMetadataTest() {
		metadata = new ProductsClassMetadata();
	}

	@Test
	public void productLineMetadata() {
		assertTrue(metadata.find(ProductLineDescriptor.class).isPresent());
		assertTrue(metadata.find(ProductLine.class).isPresent());
	}

	@Test
	public void productMetadata() {
		assertTrue(metadata.find(ProductDescriptor.class).isPresent());
		assertTrue(metadata.find(Product.class).isPresent());
	}

	@Test
	public void productVersionMetadata() {
		assertTrue(metadata.find(ProductVersionDescriptor.class).isPresent());
		assertTrue(metadata.find(ProductVersion.class).isPresent());
	}
}
