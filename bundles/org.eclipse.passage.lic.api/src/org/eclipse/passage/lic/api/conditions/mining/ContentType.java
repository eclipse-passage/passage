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
package org.eclipse.passage.lic.api.conditions.mining;

import java.util.Objects;

import org.eclipse.passage.lic.api.registry.ServiceId;

/**
 * FIXME: move to upper package
 * 
 * String-valued {@linkplain ServiceId} with mime content type semantics
 * 
 * @since 2.1
 */
public abstract class ContentType implements ServiceId {

	private final String type;

	public ContentType(String type) {
		Objects.requireNonNull(type, "ContentType::type"); //$NON-NLS-1$
		this.type = truncate(type);
	}

	private String truncate(String value) {
		int semicolon = value.indexOf(';');
		return semicolon < 0 ? value : value.substring(0, semicolon);
	}

	@Override
	public final boolean equals(Object object) {
		if (!ContentType.class.isInstance(object)) {
			return false;
		}
		return type.equals(((ContentType) object).contentType());
	}

	public final String contentType() {
		return type;
	}

	@Override
	public final int hashCode() {
		return type.hashCode();
	}

	@Override
	public final String toString() {
		return type;
	}

	public static final class Json extends ContentType {

		public Json() {
			super("application/json"); //$NON-NLS-1$
		}

	}

	public static final class Xml extends ContentType {

		public Xml() {
			super("text/xml"); //$NON-NLS-1$
		}

	}

	public static final class Of extends ContentType {

		public Of(String type) {
			super(type.toLowerCase());
		}

	}
}
