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
package org.eclipse.passage.loc.yars.internal.api;

import java.util.function.Consumer;

public final class DOSHandleMedia<T> implements ListMedia<T> {

	private final ListMedia<T> delegate;
	private final Consumer<ReportException> dosHandler;

	public DOSHandleMedia(ListMedia<T> delegate, //
			Consumer<ReportException> dosHandler) {
		this.delegate = delegate;
		this.dosHandler = dosHandler;
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
