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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.loc.yars.internal.api.FetchParams;

/**
 * FIXME
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
public final class ProductNames implements FetchParams {

	private final Set<String> products;

	public ProductNames(Collection<String> products) {
		this.products = new HashSet<String>(products);
	}

	public ProductNames(String... products) {
		this(Arrays.asList(products));
	}

	public Set<String> products() {
		return products;
	}

}
