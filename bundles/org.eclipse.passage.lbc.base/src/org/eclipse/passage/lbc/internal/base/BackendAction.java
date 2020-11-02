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
package org.eclipse.passage.lbc.internal.base;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @since 1.0
 */
public abstract class BackendAction implements Supplier<String> {

	private final String name;

	protected BackendAction(String name) {
		Objects.requireNonNull("BackendAction::name"); //$NON-NLS-1$
		this.name = name;
	}

	@Override
	public final String get() {
		return name;
	}

	@Override
	public final int hashCode() {
		return name.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		if (!BackendAction.class.isInstance(obj)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		return name.equals(((BackendAction) obj).get());
	}

	public static final class Acquire extends BackendAction {

		public Acquire() {
			super("acquire"); //$NON-NLS-1$
		}

	}

	public static final class Release extends BackendAction {

		public Release() {
			super("release"); //$NON-NLS-1$
		}

	}

	public static final class CanTake extends BackendAction {

		public CanTake() {
			super("can-take"); //$NON-NLS-1$
		}

	}

	public static final class Of extends BackendAction {

		// FIXME: throw UnknownActionException
		public Of(Function<String, String> retrieve) {
			super(retrieve.apply("action")); //$NON-NLS-1$
		}

	}

}
