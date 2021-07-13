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
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.UserHomePath;

public final class EagerFloatingState implements FloatingState {

	private final Grants grants;
	private final Path source;

	public EagerFloatingState() {
		this(new LicensingFolder(new UserHomePath()));
	}

	public EagerFloatingState(Supplier<Path> source) {
		this(new AcquiredGrants(source), source.get());
	}

	public EagerFloatingState(Grants grants, Path source) {
		this.grants = grants;
		this.source = source;
	}

	@Override
	public Grants grants() {
		return grants;
	}

	@Override
	public Path source() {
		return source;
	}

}
