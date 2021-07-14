/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.version;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.version.SemanticVersion;

/**
 * Auxiliary unit operating over {@linkplain SemanticVersion} collects it's
 * numerical fields in a list.
 * 
 * @since 2.1
 */
public final class NumericalVersion implements Supplier<List<Integer>> {

	private final SemanticVersion version;

	public NumericalVersion(SemanticVersion version) {
		this.version = version;
	}

	@Override
	public List<Integer> get() {
		return Arrays.asList(version.major(), version.minor(), version.service());
	}

}
