/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.access;

abstract class BaseOption<D extends Enum<?>> implements Option<D> {

	private final char key;
	private final String name;
	private final String description;
	protected final Interaction.Smart interaction;

	BaseOption(char key, String name, String description, Interaction.Smart interaction) {
		this.key = key;
		this.name = name;
		this.description = description;
		this.interaction = interaction;
	}

	@Override
	public String documentation() {
		return String.format("%s (%s): %s", key, name, description); //$NON-NLS-1$
	}

	@Override
	public char key() {
		return key;
	}

}
