/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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
package org.eclipse.passage.lic.base.io;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.passage.lic.api.LicensingException;

/**
 * Collects regular files of the given {@code extension} starting from the given
 * {@code base} path recursively. No particular order is guaranteed.
 *
 * @since 4.1
 */
public interface CollectedFiles {

	Collection<Path> get() throws LicensingException;

}
