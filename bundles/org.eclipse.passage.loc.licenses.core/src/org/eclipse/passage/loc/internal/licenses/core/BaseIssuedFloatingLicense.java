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
package org.eclipse.passage.loc.internal.licenses.core;

import java.nio.file.Path;
import java.util.List;

import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;

public final class BaseIssuedFloatingLicense implements IssuedFloatingLicense {

	private final Path residence;
	private final List<Path> files;

	public BaseIssuedFloatingLicense(Path residence, List<Path> files) {
		this.residence = residence;
		this.files = files;
	}

	@Override
	public Path residence() {
		return residence;
	}

	@Override
	public List<Path> files() {
		return files;
	}

}
