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
package org.eclipse.passage.lic.oshi;

import java.util.Objects;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;

import oshi.SystemInfo;

/**
 * <p>
 * It's extremely important to have not more then one instance of this service
 * at runtime (guaranteed by {@code Framework}).
 * </p>
 * 
 * @see State
 * @since 1.1
 */

public final class HardwareEnvironment implements RuntimeEnvironment {

	private final EvaluationType type = new EvaluationType.Hardware();

	@Override
	public EvaluationType id() {
		return type;
	}

	@Override
	public boolean isAssuptionTrue(EnvironmentProperty property, String assumption) throws LicensingException {
		Objects.requireNonNull(property, "HardwareEnvironment::isAssuptionTrue::property"); //$NON-NLS-1$
		Objects.requireNonNull(assumption, "HardwareEnvironment::isAssuptionTrue::assumption"); //$NON-NLS-1$
		return freshState().hasValue(property, assumption);
	}

	@Override
	public String state() throws LicensingException {
		return new GlanceOfState(freshState()).get();
	}

	/**
	 * <p>
	 * It's extremely important to prevent any concurrent access to the
	 * {@linkplain State} reading. We synchronize it by key {@code OSHI} class
	 * ({@linkplain SystemInfo}) lock to be sure we have exclusive access to it
	 * until the hardware scanning is over.
	 * </p>
	 * <p>
	 * Behind all the benefits of such a sync lock choice, it's still a foreign
	 * class. Just beware, in case of any threading troubles revise.
	 * </p>
	 */
	private State freshState() throws LicensingException {
		State state;
		synchronized (SystemInfo.class) {
			state = new State();
		}
		return state;
	}

}
