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
package org.eclipse.passage.loc.yars.internal.api;

import java.util.function.Consumer;

/**
 * <p>
 * Exporting a data to a format can cause wide variety of errors, which are
 * covered with {@code ReportException} the original {@linkplain ListMedia}
 * interface.
 * </p>
 * <p>
 * This <i>denial of service handling</i> decorator of {@code media} redirects
 * all errors to the {@code dosHandler} configured and, if possible, proceeds
 * the work.
 * </p>
 * 
 * @param <T> type of entries for export
 * @since 0.1
 */
public final class DosHandleMedia<T> implements ListMedia<T> {

	private final ListMedia<T> delegate;
	private final Consumer<ReportException> dosHandler;

	/**
	 * Redirects actual export to the given {@code delegate} and forward all the
	 * errors appearing in the process to the given {@code dosHandler}
	 * 
	 * @param delegate
	 * @param dosHandler
	 */
	public DosHandleMedia(ListMedia<T> delegate, //
			Consumer<ReportException> dosHandler) {
		this.delegate = delegate;
		this.dosHandler = dosHandler;
	}

	/**
	 * Convenience ctor uses {@linkplain DefaultDosHandler} to take care of an error
	 * occurred during the export process, if any
	 * 
	 * @param delegate
	 */
	public DosHandleMedia(ListMedia<T> delegate) {
		this(delegate, new DefaultDosHandler());
	}

	@Override
	public final void start() {
		safeCall(delegate::start);
	}

	@Override
	public final void finish() {
		safeCall(delegate::finish);
	}

	@Override
	public final void startNode(T node) {
		safeCall(() -> delegate.startNode(node));
	}

	@Override
	public final void finishNode(T node) {
		safeCall(() -> delegate.finishNode(node));
	}

	@Override
	public final void inner(String data, String name) {
		safeCall(() -> delegate.inner(data, name));
	}

	private void safeCall(Unsafe<ReportException> unsafe) {
		try {
			unsafe.call();
		} catch (ReportException e) {
			dosHandler.accept(e);
		}
	}
}
