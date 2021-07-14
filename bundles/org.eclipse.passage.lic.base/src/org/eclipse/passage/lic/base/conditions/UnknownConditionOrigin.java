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
package org.eclipse.passage.lic.base.conditions;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.IssuerSignature;

/**
 * 
 * @since 2.1
 */
public final class UnknownConditionOrigin implements ConditionOrigin {

	private final ConditionMiningTarget target = new ConditionMiningTarget.Of("unknown"); //$NON-NLS-1$

	@Override
	public ConditionMiningTarget miner() {
		return target;
	}

	@Override
	public String coordinates() {
		return "none"; //$NON-NLS-1$
	}

	@Override
	public IssuerSignature signature() {
		return new BaseIssuerSignature();
	}

}
