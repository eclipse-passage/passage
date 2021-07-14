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
package org.eclipse.passage.lic.api;

import java.util.Objects;

/**
 * 
 * @since 2.1
 */
public abstract class PassageAction {

	private final String name;

	protected PassageAction(String name) {
		Objects.requireNonNull(name, "ConditionAction::name"); //$NON-NLS-1$
		this.name = name.toLowerCase();
	}

	@Override
	public boolean equals(Object obj) {
		if (!PassageAction.class.isInstance(obj)) {
			return false;
		}
		return name.equals(((PassageAction) obj).name());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public final String name() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static final class Mine extends PassageAction {

		public Mine() {
			super("mine"); //$NON-NLS-1$
		}

	}

	public static final class Acquire extends PassageAction {

		public Acquire() {
			super("acquire"); //$NON-NLS-1$
		}

	}

	public static final class Release extends PassageAction {

		public Release() {
			super("release"); //$NON-NLS-1$
		}

	}

	public static final class CanUse extends PassageAction {

		public CanUse() {
			super("canuse"); //$NON-NLS-1$
		}

	}

	public static final class Of extends PassageAction {

		public Of(String name) {
			super(name);
		}

	}

}
