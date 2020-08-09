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
package org.eclipse.passage.lic.jface.dialogs.licensing;

import java.util.function.Supplier;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;

final class CopyToClipboard implements Runnable {

	private final Supplier<Shell> shell;
	private final Supplier<String> info;

	CopyToClipboard(Supplier<Shell> shell, Supplier<String> info) {
		this.shell = shell;
		this.info = info;
	}

	@Override
	public void run() {
		Clipboard clipboard = new Clipboard(shell.get().getDisplay());
		clipboard.setContents(//
				new Object[] { info.get() }, //
				new Transfer[] { TextTransfer.getInstance() });
		clipboard.dispose();
	}

}
