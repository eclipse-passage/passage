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
package org.eclipse.passage.lic.internal.base.conditions.evaluation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;

@SuppressWarnings("restriction")
public final class SumOfEmissions implements BinaryOperator<Emission> {

	@Override
	public Emission apply(Emission first, Emission second) {
		if (!first.conditionPack().equals(second.conditionPack())) {
			throw new IllegalArgumentException(
					"Pessimistic sum is not intended to be applied to emissions begotten by different condition packs"); //$NON-NLS-1$
		}
		return (!first.successful() || !second.successful()) //
				? new Emission.Failed(first.conditionPack(), sumDiagnostic(first, second))//
				: new Emission.Successful(first.conditionPack(), sumPermissions(first, second));
	}

	private Diagnostic sumDiagnostic(Emission first, Emission second) {
		return new BaseDiagnostic(first, second);
	}

	private Collection<Permission> sumPermissions(Emission first, Emission second) {
		Objects.requireNonNull(first);
		Objects.requireNonNull(second);
		return Arrays.asList(first.permissions(), second.permissions()).stream()//
				.flatMap(Collection::stream) //
				.collect(Collectors.toList());
	}

}
