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
package org.eclipse.passage.lic.internal.api.conditions.mining;

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.registry.ServiceId;

/**
 * String-valued {@linkplain ServiceId} with mime content type semantics
 */
public final class ContentType implements ServiceId {

	private final String type;

	public ContentType(String type) {
		Objects.requireNonNull(type, "ContentType::type"); //$NON-NLS-1$
		this.type = type;
	}

	@Override
	public boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		return type.equals(((ContentType) object).contentType());
	}

	public String contentType() {
		return type;
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}

	@Override
	public String toString() {
		return type;
	}

}
