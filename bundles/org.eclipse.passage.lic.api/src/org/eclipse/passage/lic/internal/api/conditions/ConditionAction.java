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
package org.eclipse.passage.lic.internal.api.conditions;

import java.util.Objects;

//FIXME: evolution demands for renaming
public abstract class ConditionAction {

	private final String name;

	protected ConditionAction(String name) {
		Objects.requireNonNull(name, "ConditionAction::name"); //$NON-NLS-1$
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!ConditionAction.class.isInstance(obj)) {
			return false;
		}
		return name.equals(((ConditionAction) obj).name());
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

	public static final class Mine extends ConditionAction {

		public Mine() {
			super("mine"); //$NON-NLS-1$
		}

	}

	public static final class Acquire extends ConditionAction {

		public Acquire() {
			super("acquire"); //$NON-NLS-1$
		}

	}

	public static final class Release extends ConditionAction {

		public Release() {
			super("release"); //$NON-NLS-1$
		}

	}

	public static final class Of extends ConditionAction {

		public Of(String name) {
			super(name);
		}

	}

}
