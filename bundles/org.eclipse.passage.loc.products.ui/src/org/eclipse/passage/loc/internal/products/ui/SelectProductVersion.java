/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further evolution
 *******************************************************************************/
package org.eclipse.passage.loc.internal.products.ui;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.ui.i18n.ProductsUiMessages;
import org.eclipse.passage.loc.internal.workbench.SelectRequest;
import org.eclipse.passage.loc.internal.workbench.SupplySelectRequest;
import org.eclipse.passage.loc.jface.dialogs.Appearance;

/**
 * Selects or creates {@link ProductVersionDescriptor}. Will return either
 * {@link Optional} with selected/created license plan or
 * {@link Optional#empty()}
 * 
 */
public final class SelectProductVersion extends SupplySelectRequest<ProductVersion> {

	public SelectProductVersion(MandatoryService context) {
		this(context, Optional.empty());
	}

	public SelectProductVersion(MandatoryService context, Optional<ProductVersion> selection) {
		super(context, selection);
	}

	@Override
	public SelectRequest<ProductVersion> get() {
		return new SelectRequest<>(ProductVersion.class, domain(), input(), () -> initial, appearance());
	}

	private Supplier<Iterable<ProductVersion>> input() {
		return () -> StreamSupport.stream(context.get(ProductRegistry.class).productVersions().spliterator(), false)//
				.collect(Collectors.toList());
	}

	private Appearance appearance() {
		return new Appearance(ProductsUiMessages.ProductsUi_select_product_version, //
				() -> LicensingImages.getImage(ProductsPackage.eINSTANCE.getProductVersion().getName()), labels());
	}

	private String domain() {
		return ProductsPackage.eNAME;
	}

}
