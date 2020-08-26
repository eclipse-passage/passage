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
package org.eclipse.passage.lbc.internal.api.persistence;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @since 1.0
 */
public abstract class PersistableLicense implements Supplier<BoundLicense> {

	private final BoundLicense license;

	public PersistableLicense(BoundLicense license) {
		Objects.requireNonNull(license, "PersistableLicense::license"); //$NON-NLS-1$
		this.license = license;
	}

	@Override
	public final BoundLicense get() {
		return license;
	}

	public abstract void save() throws IOException;

	public abstract boolean takeOne();

	public abstract boolean releaseOne();

}
