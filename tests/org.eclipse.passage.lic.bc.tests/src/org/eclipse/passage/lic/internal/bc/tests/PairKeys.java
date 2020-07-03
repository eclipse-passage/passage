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
package org.eclipse.passage.lic.internal.bc.tests;

import java.io.IOException;
import java.nio.file.Path;

final class PairKeys extends PairInfo<Path> {

	PairKeys(Path first, Path second) throws IOException {
		super(first, second);
	}

	@Override
	protected Path info(Path file) throws IOException {
		return file;
	}

}
