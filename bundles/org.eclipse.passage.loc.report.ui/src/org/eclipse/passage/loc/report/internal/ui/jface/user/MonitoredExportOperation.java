/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.passage.loc.report.internal.core.user.CustomerExportService;
import org.eclipse.passage.loc.report.internal.core.user.ProductCustomer;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportCustomersWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.VisibleProgress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

@SuppressWarnings("restriction")
final class MonitoredExportOperation implements IRunnableWithProgress {

	private final CustomerExportService service;
	private final Set<String> products;
	private final Path file;

	MonitoredExportOperation(CustomerExportService service, Set<String> products, Path file) {
		this.service = service;
		this.products = products;
		this.file = file;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			service.exportCustomersForProducts(//
					products, //
					file, //
					new VisibleProgress<ProductCustomer>(//
							monitor, //
							ExportCustomersWizardMessages.VisibleProgress_task//
					)//
			);
		} catch (ReportException e) {
			throw new InvocationTargetException(e);
		}
	}

}
