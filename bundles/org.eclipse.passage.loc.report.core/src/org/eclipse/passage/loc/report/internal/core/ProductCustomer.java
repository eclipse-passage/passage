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

import org.eclipse.passage.loc.yars.internal.api.DOSHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;

/**
 * FIXME
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class ProductCustomer implements ExportData<ProductCustomer, DOSHandleMedia<ProductCustomer>> {

	private final String name;
	private final String email;

	public ProductCustomer(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public void write(DOSHandleMedia<ProductCustomer> media) {
		media.inner(email, "email"); //$NON-NLS-1$
		media.inner(name, "name"); //$NON-NLS-1$
	}

}
