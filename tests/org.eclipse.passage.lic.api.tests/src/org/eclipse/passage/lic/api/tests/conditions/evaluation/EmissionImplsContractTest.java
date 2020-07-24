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
package org.eclipse.passage.lic.api.tests.conditions.evaluation;

import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeConditionPack;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.eclipse.passage.lic.api.tests.fakes.diagnostic.FakeFailureDiagnostic;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;

@SuppressWarnings("restriction")
public class EmissionImplsContractTest extends EmissionContractTest {

	@Override
	protected Emission failed() {
		return new Emission.Failed(new FakeConditionPack(), new FakeFailureDiagnostic());
	}

	@Override
	protected Emission successful() {
		return new Emission.Successful(new FakeConditionPack(), new FakePermission());
	}
}
