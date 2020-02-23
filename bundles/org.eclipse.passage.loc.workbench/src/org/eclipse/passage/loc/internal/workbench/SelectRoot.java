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
package org.eclipse.passage.loc.internal.workbench;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.loc.internal.api.ZeroOneMany;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.widgets.Shell;

/**
 * Selects the root classifier according to {@link SelectRequest} using
 * {@link CreateRoot} and {@link SelectFromDialog}
 * 
 * @since 0.6
 *
 * @param <R> target type to be selected
 */
public final class SelectRoot<R> implements Supplier<Optional<R>> {

	private final SelectRequest<R> request;
	private final IEclipseContext context;

	/**
	 * Constructs the new instance with given select request and context.
	 * 
	 * @param request the {@link SelectRequest} select to process
	 * @param context the {@link IEclipseContext} to resolve services, must not be
	 *                <code>null</code>
	 */
	public SelectRoot(SelectRequest<R> request, IEclipseContext context) {
		Objects.requireNonNull(request, WorkbenchMessages.SelectRoot_e_null_request);
		Objects.requireNonNull(context, WorkbenchMessages.SelectRoot_e_null_context);
		this.request = request;
		this.context = context;
	}

	@Override
	public final Optional<R> get() {
		return new ZeroOneMany<>(request.input()).choose(new CreateRoot<R>(context, request.domain(), request.target()),
				new SelectFromDialog<>(() -> context.get(Shell.class), request.appearance()));
	}

}
