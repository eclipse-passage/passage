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

import java.nio.file.Path;
import java.util.Set;

import org.eclipse.passage.loc.yars.internal.api.DOSHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.DefaultDOSHandler;
import org.eclipse.passage.loc.yars.internal.api.Export;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

/**
 * FIXME doc
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
public final class ProductCustomersToCsv {

	private final CustomerStorage source;

	public ProductCustomersToCsv(CustomerStorage storage) {
		this.source = storage;
	}

	public void export(Set<String> products, Path target) throws ReportException {
		new Export<CustomerStorage, ProductCustomer>(//
				new CustomersForProductsQuery()//
						.fetch(source, //
								new ProductNames(products)))//
										.write(new DOSHandleMedia<ProductCustomer>( //
												new Csv( //
														new ExistingFileStream(target), //
														"email", //$NON-NLS-1$
														"name"), //$NON-NLS-1$
												new DefaultDOSHandler()));

	}

}
