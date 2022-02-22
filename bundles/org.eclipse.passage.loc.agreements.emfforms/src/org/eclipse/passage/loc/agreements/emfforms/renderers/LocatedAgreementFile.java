/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.agreements.emfforms.renderers;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

final class LocatedAgreementFile implements Supplier<Optional<File>> {

	private final Shell shell;

	LocatedAgreementFile(Shell shell) {
		this.shell = shell;
	}

	LocatedAgreementFile() {
		this(Display.getDefault().getActiveShell());
	}

	@Override
	public Optional<File> get() {
		FileDialog dialog = new FileDialog(shell);
		List<AgreementFormat> formats = new AgreementFormat.Supported().get();
		dialog.setText("Point an agreement content file"); //$NON-NLS-1$
		dialog.setFilterExtensions(filters(formats, AgreementFormat::description));
		dialog.setFilterNames(filters(formats, AgreementFormat::description));
		return file(Optional.ofNullable(dialog.open()));
	}

	private String[] filters(List<AgreementFormat> all, Function<AgreementFormat, String> aspect) {
		return all.stream()//
				.map(aspect)//
				.collect(Collectors.toList())//
				.toArray(new String[0]);
	}

	private Optional<File> file(Optional<String> path) {
		if (path.isEmpty()) {
			return Optional.empty();
		}
		File file = new File(path.get());
		if (!file.exists()) {
			return Optional.empty();
		}
		if (!file.isFile()) {
			return Optional.empty();
		}
		return Optional.of(file);
	}

}
