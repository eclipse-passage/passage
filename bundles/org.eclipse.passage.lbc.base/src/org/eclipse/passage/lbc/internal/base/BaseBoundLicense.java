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

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;

/**
 * @since 1.0
 */
public final class BaseBoundLicense implements BoundLicense {

	private final ConditionIdentifier identifier;
	private final LicenseCapacity capacity;
	private final LicenseTaken taken;

	public BaseBoundLicense(ConditionIdentifier identifier, LicenseTaken taken, LicenseCapacity capacity) {
		Objects.requireNonNull(identifier, "BaseBoundLicense::identifier"); //$NON-NLS-1$
		Objects.requireNonNull(capacity, "BaseBoundLicense::capacity"); //$NON-NLS-1$
		Objects.requireNonNull(taken, "BaseBoundLicense::taken"); //$NON-NLS-1$
		this.identifier = identifier;
		this.capacity = capacity;
		this.taken = taken;
	}

	@Override
	public ConditionIdentifier identifier() {
		return identifier;
	}

	@Override
	public LicenseCapacity capacity() {
		return capacity;
	}

	@Override
	public LicenseTaken taken() {
		return taken;
	}

	@Override
	public boolean takeable() {
		return taken().get().get() < capacity().get().get();
	}

}
