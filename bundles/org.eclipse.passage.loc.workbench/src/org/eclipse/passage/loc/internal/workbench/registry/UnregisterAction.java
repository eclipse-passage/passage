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
package org.eclipse.passage.loc.internal.workbench.registry;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.jface.action.Action;
import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.widgets.Shell;

public final class UnregisterAction extends Action {

	private final LocDomainRegistryAccess access;
	private final Supplier<Object[]> selection;
	private final Supplier<Shell> shell;

	public UnregisterAction(LocDomainRegistryAccess access, Supplier<Object[]> selection, Supplier<Shell> shell) {
		super(WorkbenchMessages.UnregisterConfirmation_title);
		Objects.requireNonNull(access, "LocDomainRegistryAccess access"); //$NON-NLS-1$
		Objects.requireNonNull(selection, "Supplier<Object[]> selection"); //$NON-NLS-1$
		Objects.requireNonNull(shell, "Supplier<Shell> shell"); //$NON-NLS-1$
		this.access = access;
		this.selection = selection;
		this.shell = shell;
	}

	@Override
	public void run() {
		new UnregisterSelected(//
				new UnregisterUri(access), //
				new UnregisterConfirmation(shell))//
						.unregister(selection.get());
	}

}
