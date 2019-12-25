/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core;

import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.Query;

/**
 * FIXME
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
public final class CustomersForProductsQuery implements Query<CustomerBase, ProductCustomer, ProductNames> {

	@Override
	public String id() {
		return "CUSTOMERS_FOR_PRODUCTS"; //$NON-NLS-1$
	}

	@Override
	public String description() {
		return Messages.getString("CustomersForProductsQuery.query_description"); //$NON-NLS-1$
	}

	@Override
	public FetchedData<CustomerBase, ProductCustomer> fetch(CustomerBase storage, ProductNames properties) {
		return new CustomersFetch(storage, properties.products());
	}

}
