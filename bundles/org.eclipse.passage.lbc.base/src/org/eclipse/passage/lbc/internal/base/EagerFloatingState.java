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
package org.eclipse.passage.lbc.internal.base;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.base.acquire.AcquiredGrants;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.Grants;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

public final class EagerFloatingState implements FloatingState {

	private final Grants grants;

	public EagerFloatingState() {
		this(new LicensingFolder(new UserHomePath()));
	}

	public EagerFloatingState(Supplier<Path> source) {
		this.grants = new AcquiredGrants(source);
	}

	@Override
	public Grants grants() {
		return grants;
	}

}
