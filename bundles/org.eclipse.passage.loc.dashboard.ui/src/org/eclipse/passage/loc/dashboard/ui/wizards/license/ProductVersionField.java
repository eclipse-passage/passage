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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.util.Optional;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.products.ui.SelectProduct;
import org.eclipse.passage.loc.internal.products.ui.SelectProductVersion;
import org.eclipse.passage.loc.internal.workbench.SelectInner;
import org.eclipse.swt.widgets.Text;

public final class ProductVersionField extends SelectableField<ProductVersionDescriptor> {

	ProductVersionField(Optional<ProductVersionDescriptor> product, Runnable modified, LabelProvider labels,
			MandatoryService context) {
		super(product, modified, labels, context);
	}

	@Override
	protected String label() {
		return IssueLicensePageMessages.IssueLicenseRequestPage_lbl_product_version;
	}

	@Override
	protected String errorText() {
		return IssueLicensePageMessages.IssueLicenseRequestPage_e_no_product_version;
	}

	@Override
	protected Optional<ProductVersionDescriptor> select(Text control) {
		return new SelectInner<ProductVersionDescriptor, ProductDescriptor>(//
				new SelectProductVersion(context).get(), //
				new SelectProduct(context).get(), //
				context//
		).get();
	}

}
