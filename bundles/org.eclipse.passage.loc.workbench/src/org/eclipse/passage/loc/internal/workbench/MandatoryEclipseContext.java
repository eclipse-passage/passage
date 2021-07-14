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

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;

/**
 * 
 * Uses inner {@link IEclipseContext} to retrieve service instance, fails if
 * inner context has no instance for a given type
 *
 */
public final class MandatoryEclipseContext implements MandatoryService, Supplier<IEclipseContext> {

	private final IEclipseContext context;

	/**
	 * Creates a new instance using given {@link IEclipseContext}
	 * 
	 * @param context an {@link IEclipseContext} to delegate service resolution to
	 */
	public MandatoryEclipseContext(IEclipseContext context) {
		Objects.requireNonNull(context, WorkbenchMessages.MandatoryEclipseContext_e_null_context);
		this.context = context;
	}

	@Override
	public <T> T get(Class<T> type) {
		return Optional.ofNullable(context.get(type)).get();
	}

	@Override
	public IEclipseContext get() {
		return context;
	}

}
