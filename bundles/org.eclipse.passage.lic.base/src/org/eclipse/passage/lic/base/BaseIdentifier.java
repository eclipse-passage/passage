/*******************************************************************************
 * Copyright (c) 2024, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation; further evolution
 *******************************************************************************/
package org.eclipse.passage.lic.base;

import java.io.Serializable;
import java.util.Objects;

abstract class BaseIdentifier implements Serializable {

	private static final long serialVersionUID = -3651140617983127071L;
	private final String identifier;

	protected BaseIdentifier(String identifier) {
		this.identifier = Objects.requireNonNull(identifier);
	}

	protected final String value() {
		return identifier;
	}

	@Override
	public final int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		if (!(obj instanceof BaseIdentifier id)) {
			return false;
		}
		return id.value().equals(identifier);
	}

	@Override
	public final String toString() {
		return identifier;
	}

}
