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
package org.eclipse.passage.lic.api.tests.fakes.conditions;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

@SuppressWarnings("restriction")
public final class FakeConditionPack implements ConditionPack {

	@Override
	public String origin() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Condition> conditions() {
		throw new UnsupportedOperationException();
	}

}
