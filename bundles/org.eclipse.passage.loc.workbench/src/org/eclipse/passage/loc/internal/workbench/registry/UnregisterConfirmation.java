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

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.widgets.Shell;

public final class UnregisterConfirmation implements Predicate<List<URI>> {

	private final Supplier<Shell> shell;

	public UnregisterConfirmation(Supplier<Shell> shell) {
		Objects.requireNonNull(shell, "Supplier<Shell> shell"); //$NON-NLS-1$
		this.shell = shell;
	}

	@Override
	public boolean test(List<URI> uris) {
		return MessageDialog.openConfirm(shell.get(), WorkbenchMessages.UnregisterConfirmation_title,
				MessageFormat.format(WorkbenchMessages.UnregisterConfirmation_message, uris.size()));
	}

}
