/*******************************************************************************
 * Copyright (c) 202 ArSysOp
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
package org.eclipse.passage.lbc.internal.base.api;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;

public final class FloatingStateFromGear implements Supplier<FloatingState> {

	private final Optional<Path> path;

	public FloatingStateFromGear(Path path) {
		this(Optional.of(path));
	}

	public FloatingStateFromGear(Optional<Path> path) {
		this.path = path;
	}

	@Override
	public FloatingState get() {
		Optional<FloatingState> mayBeState;
		try {
			mayBeState = new FlsGearAwre().withGear(this::state);
		} catch (LicensingException e) {
			e.printStackTrace();
			mayBeState = Optional.empty();
		}
		if (!mayBeState.isPresent()) {
			throw new IllegalStateException("FLS configuration error: Floating State is not supplied"); //$NON-NLS-1$
		}
		return mayBeState.get();
	}

	private Optional<FloatingState> state(FlsGear gear) {
		if (!path.isPresent()) {
			throw new IllegalStateException("FLS configuration error: storage path is not supplied"); //$NON-NLS-1$
		}
		return Optional.ofNullable(gear.state(path::get));
	}

}
