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

import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.eclipse.passage.loc.yars.internal.api.ListMedia;
import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.eclipse.passage.loc.yars.internal.api.Unsafe;

/**
 * FIXME doc
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class Csv implements ListMedia<ProductCustomer> {
	private final CSVPrinter stream;

	public Csv(ExistingFileStream target, String... header) throws ReportException {
		try {
			stream = //
					new CSVPrinter( //
							new PrintStream(target.stream()), //
							CSVFormat.EXCEL.withHeader(header));
		} catch (IOException e) {
			throw new ReportException(Messages.getString("Csv.action_printer_construction_failure"), e); //$NON-NLS-1$
		}
	}

	@Override
	public final void start() throws ReportException {
	}

	@Override
	public final void finish() throws ReportException {
		unsafeCall(stream::close, Messages.getString("Csv.action_printer_closure_failure")); //$NON-NLS-1$
	}

	@Override
	public final void startNode(ProductCustomer node) {
	}

	@Override
	public final void finishNode(ProductCustomer node) throws ReportException {
		unsafeCall(stream::println, Messages.getString("Csv.action_closing_row")); //$NON-NLS-1$
	}

	@Override
	public final void inner(String data, String name) throws ReportException {
		unsafeCall(() -> {
			stream.print(data);
		}, String.format(Messages.getString("Csv.action_filling_cell"), data, name)); //$NON-NLS-1$
	}

	private void unsafeCall(Unsafe<IOException> unsafe, String happening) throws ReportException {
		try {
			unsafe.call();
		} catch (IOException e) {
			throw new ReportException(String.format(Messages.getString("Csv.action_failed"), happening), e); //$NON-NLS-1$
		}
	}
}
