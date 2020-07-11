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
package org.eclipse.passage.lic.internal.oshi.tobemoved;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironment;

@SuppressWarnings("restriction")
public final class HardwareEnvironment implements RuntimeEnvironment {

	private final EvaluationType type = new EvaluationType.Hardware();
	private final State state = new State();

	@Override
	public EvaluationType id() {
		return type;
	}

	@Override
	public boolean isAssuptionTrue(EnvironmentProperty property, String assumption) throws LicensingException {
		return state.hasValue(property, assumption);
	}

	@Override
	public String state() throws LicensingException {
		return new GlanceOfState(state).get();
	}

}
