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
package org.eclipse.passage.lic.api.tests.fakes;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;

@SuppressWarnings("restriction")
public final class FakeMinedConditions implements MinedConditions {

	@Override
	public StringServiceId id() {
		return new StringServiceId("fake-mining-service"); //$NON-NLS-1$
	}

	@Override
	public Collection<Condition> all() throws ConditionMiningException {
		return Collections.emptySet();
	}

}
