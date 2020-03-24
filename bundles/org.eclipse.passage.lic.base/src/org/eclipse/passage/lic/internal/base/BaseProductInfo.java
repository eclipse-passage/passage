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
package org.eclipse.passage.lic.internal.base;

import java.util.Map;
import java.util.function.Function;

public abstract class BaseProductInfo implements ProductInfo {

	private final String value;
	private final String meta;

	protected BaseProductInfo(String value, String meta) {
		this.value = value;
		this.meta = meta;
	}

	protected BaseProductInfo(Map<String, Object> values, String meta) {
		this(String.valueOf(values.get(meta)), meta);
	}

	protected BaseProductInfo(Function<String, String> retriever, String meta) {
		this(retriever.apply(meta), meta);
	}

	@Override
	public String get() {
		return value;
	}

	@Override
	public void write(Map<String, Object> target) {
		target.put(meta, value);
	}

	@Override
	public void write(StringBuilder target) {
		target //
				.append(meta) //
				.append("=") //$NON-NLS-1$
				.append(value);
	}

	public static final class Version extends BaseProductInfo {

		private static final String key = "licensing.product.version"; //$NON-NLS-1$

		public Version(String value) {
			super(value, key);
		}

		public Version(Map<String, Object> values) {
			super(values, key);
		}

		public Version(Function<String, String> retriever) {
			super(retriever, key);
		}

	}

	public static final class Identifier extends BaseProductInfo {

		private static final String key = "licensing.product.identifier";//$NON-NLS-1$

		public Identifier(String value) {
			super(value, key);
		}

		public Identifier(Map<String, Object> values) {
			super(values, key);
		}

		public Identifier(Function<String, String> retriever) {
			super(retriever, key);
		}
	}

}
