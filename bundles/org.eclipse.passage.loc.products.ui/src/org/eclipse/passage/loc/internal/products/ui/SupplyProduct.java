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

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.eclipse.passage.loc.internal.workbench.SelectInner;

public class SupplyProduct implements InstanceSupply<ProductDescriptor> {

	private final MandatoryService context;

	public SupplyProduct(MandatoryService context) {
		this.context = context;
	}

	@Override
	public Optional<ProductDescriptor> supply() {
		return new SelectInner<ProductDescriptor, ProductLineDescriptor>(new SelectProduct(context).get(),
				new SelectProductLine(context).get(), context).get();
	}

}
