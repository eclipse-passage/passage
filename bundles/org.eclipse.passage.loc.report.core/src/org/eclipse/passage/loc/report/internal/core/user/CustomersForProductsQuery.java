/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core.user;

import org.eclipse.passage.loc.report.internal.core.i18n.CustomersReportMessages;
import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.Query;

/**
 * <p>
 * {@code YARS}'s {@linkplain Query} defines <i>get info of all customers that
 * can interested in any of the given products</i>.
 * </p>
 * 
 * <p>
 * Source of information is {@linkplain CustomerStorage}.
 * </p>
 * <p>
 * Unit of output information is {@linkplain ProductCustomer}.
 * </p>
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class CustomersForProductsQuery implements Query<CustomerStorage, ProductCustomer, ProductNames> {

	@Override
	public String id() {
		return "CUSTOMERS_FOR_PRODUCTS"; //$NON-NLS-1$
	}

	@Override
	public String description() {
		return CustomersReportMessages.getString("CustomersForProductsQuery.query_description"); //$NON-NLS-1$
	}

	/**
	 * Creates a lazy fetcher for the information of interest. The fetcher to be
	 * created is parameterized with an instance of {@linkplain ProductNames}.
	 * 
	 * @param storage    the source of information
	 * @param properties fetcher settings to be used on a source querying
	 * 
	 * @since 0.1
	 */
	@Override
	public FetchedData<CustomerStorage, ProductCustomer> fetch(CustomerStorage storage, ProductNames properties) {
		return new CustomersFetch(storage, properties.products());
	}

}
