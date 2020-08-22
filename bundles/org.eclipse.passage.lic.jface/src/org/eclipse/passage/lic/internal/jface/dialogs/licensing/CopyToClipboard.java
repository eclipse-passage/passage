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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;

final class CopyToClipboard implements Runnable {

	private final Supplier<Shell> shell;
	private final List<Supplier<String>> info;

	@SafeVarargs
	CopyToClipboard(Supplier<Shell> shell, Supplier<String>... sources) {
		this.shell = shell;
		this.info = Arrays.asList(sources);
	}

	@Override
	public void run() {
		Clipboard clipboard = new Clipboard(shell.get().getDisplay());
		clipboard.setContents(//
				new Object[] { output() }, //
				new Transfer[] { TextTransfer.getInstance() });
		clipboard.dispose();
	}

	private String output() {
		StringBuilder out = new StringBuilder();
		info.forEach(data -> out//
				.append("---------------------\r\n")//$NON-NLS-1$
				.append(data.get())//
				.append("\r\n")//$NON-NLS-1$
		);
		return out.toString();
	}
}
