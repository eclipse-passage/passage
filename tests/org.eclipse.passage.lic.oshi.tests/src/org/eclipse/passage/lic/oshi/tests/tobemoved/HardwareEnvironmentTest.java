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
package org.eclipse.passage.lic.oshi.tests.tobemoved;

import org.eclipse.passage.lic.api.tests.inspection.RuntimeEnvironmentContractTest;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.internal.base.inspection.hardware.OS;
import org.eclipse.passage.lic.internal.base.inspection.hardware.OS.Family;
import org.eclipse.passage.lic.internal.oshi.tobemoved.HardwareEnvironment;

@SuppressWarnings("restriction")
public final class HardwareEnvironmentTest extends RuntimeEnvironmentContractTest {

	@Override
	protected RuntimeEnvironment environment() {
		return new HardwareEnvironment();
	}

	@Override
	protected EvaluationType expectedEvaluationType() {
		return new EvaluationType.Hardware();
	}

	@Override
	protected String invalidPropertyValue() {
		return "not-existing-operating-system"; //$NON-NLS-1$
	}

	@Override
	protected Family property() {
		return new OS.Family();
	}

}
