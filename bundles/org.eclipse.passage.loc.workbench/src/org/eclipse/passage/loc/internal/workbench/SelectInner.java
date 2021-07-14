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
public final class SelectInner<I, R> implements Supplier<Optional<I>> {

	private final SelectRequest<I> inner;
	private final SelectRequest<R> root;
	private final MandatoryService context;

	/**
	 * Constructs the new instance with given select request and context.
	 * 
	 * @param inner   the {@link SelectRequest} to process for the classifier
	 * @param root    the {@link SelectRequest} to process for the root classifier
	 * @param context the {@link MandatoryService} to resolve services, must not be
	 *                <code>null</code>
	 */
	public SelectInner(SelectRequest<I> inner, SelectRequest<R> root, MandatoryService context) {
		Objects.requireNonNull(inner, WorkbenchMessages.SelectRoot_e_null_inner_request);
		Objects.requireNonNull(root, WorkbenchMessages.SelectRoot_e_null_root_request);
		Objects.requireNonNull(context, WorkbenchMessages.SelectRoot_e_null_context);
		this.inner = inner;
		this.root = root;
		this.context = context;
	}

	@Override
	public final Optional<I> get() {
		return new ZeroOrOne<>(inner.input()).choose(
				new CreateInner<I, R>(context, root.domain(), inner.target(), root),
				new SelectFromDialog<>(() -> context.get(Shell.class), inner.appearance()));
	}

}
