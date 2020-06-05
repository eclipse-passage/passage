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

import java.nio.file.Path;
import java.util.Set;

import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

/**
 * <p>
 * Central interface of the package: here an export developer finds proper
 * reporting action.
 * </p>
 * 
 * @since 0.2
 */
@SuppressWarnings("restriction")
public interface CustomerExportService {

	/**
	 * Command to export agreed information about all customers interested in the
	 * given {@code products} to the file pointed by the given {@code path}.
	 * 
	 * @param products set of {@code product} identifiers of interest
	 * @param target   output file with exported data
	 * @param progress tracker implementation to keep an eye on actual export
	 *                 process.
	 * @see Progress
	 * @see Progress.Inane
	 * @since 0.1
	 */
	void exportCustomersForProducts(Set<String> products, Path target, Progress<ProductCustomer> progress)
			throws ReportException;

}
