/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;
import org.eclipse.passage.loc.yars.internal.api.Progress;

/**
 * <p>
 * Internal unit of information fetched during
 * {@linkplain CustomerExportService#exportCustomersForProducts(java.util.Set, java.nio.file.Path)}
 * command execution.
 * </p>
 * 
 * <p>
 * Encapsulates agreed data to be exported. Currently it's hardcoded
 * {@code email} and {@code name}, but a portion of flexibility should most
 * probably appear here.
 * </p>
 * 
 * <p>
 * Represents an <i>exportable</i> unit (implements {@linkplain ExportData}
 * interface).
 * </p>
 * 
 * @since 0.2
 */
@SuppressWarnings("restriction")
public final class ProductCustomer implements ExportData<ProductCustomer, DosHandleMedia<ProductCustomer>> {

	private final String name;
	private final String contact;
	private final Usage usage;

	public ProductCustomer(UserDescriptor user) {
		this(user.getContact().getName(), user.getContact().getEmail(), Usage.personal);
	}

	public ProductCustomer(UserOriginDescriptor company) {
		this(company.getName(), "", Usage.company); //$NON-NLS-1$
	}

	public ProductCustomer(String name, String contact, Usage usage) {
		this.name = name;
		this.contact = contact;
		this.usage = usage;
	}

	@Override
	public void write(DosHandleMedia<ProductCustomer> media, Progress<ProductCustomer> progress) {
		media.inner(name, "name"); //$NON-NLS-1$
		media.inner(usage.name(), "usage"); //$NON-NLS-1$
		media.inner(contact, "contact"); //$NON-NLS-1$
	}

	@Override
	public String toString() {
		return String.format("%s [%s] (%s)", name, usage.name(), contact); //$NON-NLS-1$
	}

	public static enum Usage {
		personal, company
	}
}
