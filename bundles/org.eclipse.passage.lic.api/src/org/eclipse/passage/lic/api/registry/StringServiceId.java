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
package org.eclipse.passage.lic.api.registry;

import java.util.Objects;

/**
 * Base {@linkplain ServiceId} service implementation represents a single
 * {@code not null} string.
 * @since 2.1
 */
public final class StringServiceId implements ServiceId {

	private final String id;

	public StringServiceId(String id) {
		Objects.requireNonNull(id);
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		return id.equals(((StringServiceId) object).id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return id;
	}

}
