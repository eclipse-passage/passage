/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.products.ui;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.ui.i18n.ProductsUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SupplySelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Selects or creates {@link ProductDescriptor}. Will return either
 * {@link Optional} with selected/created license plan or
 * {@link Optional#empty()}
 * 
 */
public final class SelectProduct extends SupplySelectRequest<ProductDescriptor> {

	public SelectProduct(MandatoryService context) {
		super(context);
	}

	@Override
	public SelectRequest<ProductDescriptor> get() {
		return new SelectRequest<>(ProductDescriptor.class, domain(), input(), appearance());
	}

	private Supplier<Iterable<ProductDescriptor>> input() {
		return () -> StreamSupport.stream(context.get(ProductRegistry.class).getProducts().spliterator(), false)//
				.collect(Collectors.toList());
	}

	private Appearance appearance() {
		return new Appearance(ProductsUiMessages.ProductsUi_select_product, //
				() -> LicensingImages.getImage(ProductsPackage.eINSTANCE.getProduct().getName()), labels());
	}

	private String domain() {
		return ProductsPackage.eNAME;
	}

}
