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
package org.eclipse.passage.loc.report.internal.core;

import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.eclipse.passage.loc.report.internal.core.i18n.ReportMessages;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;
import org.eclipse.passage.loc.yars.internal.api.ListMedia;
import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.eclipse.passage.loc.yars.internal.api.Unsafe;

/**
 * {@linkplain ListMedia} implementation done by {@code apache.commons.csv}
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
public final class Csv<D extends ExportData<D, DosHandleMedia<D>>> implements ListMedia<D> {
	private final CSVPrinter stream;

	/**
	 * We require an existing
	 */
	public Csv(ExistingFileStream target, String... header) throws ReportException {
		try {
			stream = //
					new CSVPrinter( //
							target.stream(), //
							CSVFormat.EXCEL //
									.withDelimiter(';')//
									.withHeader(header));
		} catch (IOException e) {
			throw new ReportException(ReportMessages.getString("Csv.action_printer_construction_failure"), e); //$NON-NLS-1$
		}
	}

	@Override
	public final void start() throws ReportException {
	}

	@Override
	public final void finish() throws ReportException {
		unsafeCall(stream::close, ReportMessages.getString("Csv.action_printer_closure_failure")); //$NON-NLS-1$
	}

	@Override
	public final void startNode(D node) {
	}

	@Override
	public final void finishNode(D node) throws ReportException {
		unsafeCall(stream::println, ReportMessages.getString("Csv.action_closing_row")); //$NON-NLS-1$
	}

	@Override
	public final void inner(String data, String name) throws ReportException {
		unsafeCall(() -> {
			stream.print(data);
		}, String.format(ReportMessages.getString("Csv.action_filling_cell"), data, name)); //$NON-NLS-1$
	}

	private void unsafeCall(Unsafe<IOException> unsafe, String happening) throws ReportException {
		try {
			unsafe.call();
		} catch (IOException e) {
			throw new ReportException(String.format(ReportMessages.getString("Csv.action_failed"), happening), e); //$NON-NLS-1$
		}
	}
}
