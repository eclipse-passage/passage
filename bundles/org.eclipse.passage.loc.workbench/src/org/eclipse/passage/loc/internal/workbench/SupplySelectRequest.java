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

import java.util.function.Supplier;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.loc.workbench.viewers.DomainRegistryLabelProvider;

public abstract class SupplySelectRequest<C> implements Supplier<SelectRequest<C>> {

	protected final IEclipseContext context;

	public SupplySelectRequest(IEclipseContext context) {
		this.context = context;
	}

	protected LabelProvider labels() {
		return new DomainRegistryLabelProvider(
				context.get(ComposedAdapterFactoryProvider.class).getComposedAdapterFactory());
	}
}
