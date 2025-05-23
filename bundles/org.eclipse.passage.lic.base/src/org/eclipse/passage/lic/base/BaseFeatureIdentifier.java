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

import java.util.Objects;

import org.eclipse.passage.lic.api.FeatureIdentifier;

/**
 * Base implementation for {@link FeatureIdentifier}
 * 
 * @since 4.0
 */
public final class BaseFeatureIdentifier implements FeatureIdentifier {

	private final String identifier;

	public BaseFeatureIdentifier(String identifier) {
		this.identifier = Objects.requireNonNull(identifier);
	}

	@Override
	public String identifier() {
		return identifier;
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FeatureIdentifier id)) {
			return false;
		}
		return id.identifier().equals(identifier);
	}

	@Override
	public String toString() {
		return identifier;
	}

}
