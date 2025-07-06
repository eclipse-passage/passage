/*******************************************************************************
 * Copyright (c) 2021, 2025 ArSysOp
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
package org.eclipse.passage.lic.cli;

abstract class BaseOption<K extends Option.Key, D extends Enum<?>> implements Option<K, D> {

	private final K key;
	private final String name;
	private final String description;
	protected final Interaction interaction;

	protected BaseOption(K key, String name, String description, Interaction interaction) {
		this.key = key;
		this.name = name;
		this.description = description;
		this.interaction = interaction;
	}

	@Override
	public String documentation() {
		return String.format("%s (%s): %s", key.symbol(), name, description); //$NON-NLS-1$
	}

	@Override
	public K key() {
		return key;
	}

}
