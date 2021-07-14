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

import java.util.Objects;

import org.eclipse.passage.lic.api.version.Version;

/**
 * 
 * @since 2.1
 */
public abstract class BaseVersion implements Version {

	@Override
	public final int hashCode() {
		return Objects.hash(value());
	}

	@Override
	public final boolean equals(Object obj) {
		if (!Version.class.isInstance(obj)) {
			return false;
		}
		return value().equals(((Version) obj).value());
	}

	@Override
	public final String toString() {
		return value();
	}

}
