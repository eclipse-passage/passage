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
package org.eclipse.passage.lic.base.conditions.evaluation;

import java.util.function.BinaryOperator;

import org.eclipse.passage.lic.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.base.SumOfCollections;

/**
 * @since 2.1
 */
public final class SumOfEmissions implements BinaryOperator<Emission> {

	@Override
	public Emission apply(Emission first, Emission second) {
		if (!first.conditionPack().equals(second.conditionPack())) {
			throw new IllegalArgumentException(
					"Pessimistic sum is not intended to be applied to emissions begotten by different condition packs"); //$NON-NLS-1$
		}
		return new Emission(//
				first.conditionPack(), //
				new SumOfCollections<Permission>().apply(first.permissions(), second.permissions()));
	}

}
