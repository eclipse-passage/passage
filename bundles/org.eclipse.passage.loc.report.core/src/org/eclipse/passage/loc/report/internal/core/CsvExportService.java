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

import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * <p>
 * OSGi {@code component} that implements the package central
 * {@linkplain ExportService} interface with {@code csv}-targeted export.
 * </p>
 * <p>
 * {@linkplain CustomerStorage} reference is intended to be injected by OSGi.
 * </p>
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
@Component
public final class CsvExportService implements ExportService {

	private CustomerStorage source;

	@Override
	public void exportCustomersForProducts(Set<String> products, Path target) throws ReportException {
		new ProductCustomersToCsv(source).export(products, target);

	}

	@Override
	@Reference
	public void installCustomerStorage(CustomerStorage storage) {
		source = storage;
	}

}
