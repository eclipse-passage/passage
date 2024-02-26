/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.workbench;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.api.ZeroOrMany;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.widgets.Shell;

/**
 * Selects a subset of root classifiers according to {@link SelectRequest} using
 * {@link CreateRoot} and {@link SelectSeveralFromDialog}
 * 
 * @param <R> target type to be selected
 */
public final class SelectRoots<R> implements Supplier<Collection<R>> {

	private final SelectRequest<R> request;
	private final MandatoryService context;

	/**
	 * Constructs the new instance with given select request and context.
	 * 
	 * @param request the {@link SelectRequest} select to process
	 * @param context the {@link MandatoryService} to resolve services, must not be
	 *                <code>null</code>
	 */
	public SelectRoots(SelectRequest<R> request, MandatoryService context) {
		Objects.requireNonNull(request, WorkbenchMessages.SelectRoot_e_null_root_request);
		Objects.requireNonNull(context, WorkbenchMessages.SelectRoot_e_null_context);
		this.request = request;
		this.context = context;
	}

	@Override
	public final Collection<R> get() {
		return new ZeroOrMany<>(() -> collection(request.input())//
		).choose(//
				new CreateRoot<R>(context, request.domain(), request.target()), //
				new SelectSeveralFromDialog<R>(//
						() -> context.get(Shell.class), //
						request.appearance(), //
						collection(request.initial()))//
		);
	}

	private Collection<R> collection(Supplier<Iterable<R>> iterable) {
		return StreamSupport.stream(iterable.get().spliterator(), false)//
				.collect(Collectors.toList());
	}

}
