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
public final class SelectInner<I, R> implements Supplier<Optional<I>> {

	private final SelectRequest<I> inner;
	private final SelectRequest<R> root;
	private final IEclipseContext context;

	/**
	 * Constructs the new instance with given select request and context.
	 * 
	 * @param inner   the {@link SelectRequest} to process for the classifier
	 * @param root    the {@link SelectRequest} to process for the root classifier
	 * @param context the {@link IEclipseContext} to resolve services, must not be
	 *                <code>null</code>
	 */
	public SelectInner(SelectRequest<I> inner, SelectRequest<R> root, IEclipseContext context) {
		Objects.requireNonNull(inner, WorkbenchMessages.SelectRoot_e_null_request);
		Objects.requireNonNull(context, WorkbenchMessages.SelectRoot_e_null_context);
		this.inner = inner;
		this.root = root;
		this.context = context;
	}

	@Override
	public final Optional<I> get() {
		return new ZeroOneMany<>(inner.input()).choose(
				new CreateInner<I, R>(context, root.domain(), inner.target(), root),
				new SelectFromDialog<>(() -> context.get(Shell.class), inner.appearance()));
	}

}
