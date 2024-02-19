/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.swt.widgets.Composite;

/**
 * Represents sources for license files
 */
public interface LicenseFilesControl {

	void install(Composite parent, Consumer<List<Path>> onLicenses);

}
