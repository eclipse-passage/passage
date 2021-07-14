/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.api.ZeroOrOne;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.widgets.Shell;

/**
 * Selects the root classifier according to {@link SelectRequest} using
 * {@link CreateRoot} and {@link SelectFromDialog}
 * 
 * @param <R> target type to be selected
 */
public final class SelectRoot<R> implements Supplier<Optional<R>> {

	private final SelectRequest<R> request;
	private final MandatoryService context;

	/**
	 * Constructs the new instance with given select request and context.
	 * 
	 * @param request the {@link SelectRequest} select to process
	 * @param context the {@link MandatoryService} to resolve services, must not be
	 *                <code>null</code>
	 */
	public SelectRoot(SelectRequest<R> request, MandatoryService context) {
		Objects.requireNonNull(request, WorkbenchMessages.SelectRoot_e_null_root_request);
		Objects.requireNonNull(context, WorkbenchMessages.SelectRoot_e_null_context);
		this.request = request;
		this.context = context;
	}

	@Override
	public final Optional<R> get() {
		return new ZeroOrOne<>(request.input()).choose(new CreateRoot<R>(context, request.domain(), request.target()),
				new SelectFromDialog<>(() -> context.get(Shell.class), request.appearance()));
	}

}
