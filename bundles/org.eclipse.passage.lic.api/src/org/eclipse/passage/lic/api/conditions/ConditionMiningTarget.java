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
package org.eclipse.passage.lic.api.conditions;

import java.util.Objects;

import org.eclipse.passage.lic.api.registry.ServiceId;

/**
 * String-valued {@linkplain ServiceId} with mime content type semantics
 * 
 * @since 2.1
 */
public abstract class ConditionMiningTarget implements ServiceId {

	private final String target;

	public ConditionMiningTarget(String target) {
		Objects.requireNonNull(target, "ConditionMiningTarget::target"); //$NON-NLS-1$
		this.target = target;
	}

	@Override
	public boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		return target.equals(((ConditionMiningTarget) object).target());
	}

	public String target() {
		return target;
	}

	@Override
	public int hashCode() {
		return target.hashCode();
	}

	@Override
	public String toString() {
		return target;
	}

	public ConditionMiningTarget child(String subtarget) {
		Objects.requireNonNull(subtarget, "ConditionMiningTarget::child"); //$NON-NLS-1$
		return new Of(String.format("%s/%s", target, subtarget)); //$NON-NLS-1$
	}

	public static final class Local extends ConditionMiningTarget {

		public Local() {
			super("local-file-system"); //$NON-NLS-1$
		}

	}

	public static final class Remote extends ConditionMiningTarget {

		public Remote() {
			super("remote"); //$NON-NLS-1$
		}

	}

	public static final class Of extends ConditionMiningTarget {

		public Of(String target) {
			super(target.toLowerCase());
		}

	}
}
