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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * <p>
 * Base class collecting all the data gathered by an export wizard. Can tell if
 * the current state of the data is sufficient ({@code complete}) for export or
 * not.
 * </p>
 * <p>
 * Each export wizard is must get from a user the following information:
 * </p>
 * <ul>
 * <li>path to a directory where exported file is going to appear, and</li>
 * <li>if a user desires to open exported report right after the export is
 * done</li>
 * </ul>
 * <p>
 * To gather more data, extend the class, supply the data and the validation
 * part for it.
 * </p>
 */
public abstract class ExportWizardDecisions {

	private final Supplier<Path> target;
	private final Supplier<Boolean> open;

	protected ExportWizardDecisions(Supplier<Path> target, Supplier<Boolean> open) {
		this.target = target;
		this.open = open;
	}

	public final Path target() {
		return target.get();
	}

	public final boolean open() {
		return open.get();
	}

	public final boolean complete() {
		Path path = target();
		return Files.exists(path) && Files.isDirectory(path) && dataComplete();
	}

	protected abstract boolean dataComplete();

}
