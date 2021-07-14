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

import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.IssuerSignature;

/**
 * 
 * @since 2.1
 */
public final class BaseConditionOrigin implements ConditionOrigin {

	private final ConditionMiningTarget miner;
	private final String coordinates;
	private final IssuerSignature signature;

	public BaseConditionOrigin(ConditionMiningTarget miner, String coordinates) {
		this(miner, coordinates, new BaseIssuerSignature());
	}

	public BaseConditionOrigin(ConditionMiningTarget miner, String coordinates, Optional<IssuerSignature> signature) {
		this(miner, coordinates, signature.orElse(new BaseIssuerSignature()));
	}

	public BaseConditionOrigin(ConditionMiningTarget miner, String coordinates, IssuerSignature signature) {
		Objects.requireNonNull(miner, "BaseConditionOrigin::miner"); //$NON-NLS-1$
		Objects.requireNonNull(coordinates, "BaseConditionOrigin::coordinates"); //$NON-NLS-1$
		Objects.requireNonNull(signature, "BaseConditionOrigin::signature"); //$NON-NLS-1$
		this.miner = miner;
		this.coordinates = coordinates;
		this.signature = signature;
	}

	@Override
	public ConditionMiningTarget miner() {
		return miner;
	}

	@Override
	public String coordinates() {
		return coordinates;
	}

	@Override
	public IssuerSignature signature() {
		return signature;
	}

}
