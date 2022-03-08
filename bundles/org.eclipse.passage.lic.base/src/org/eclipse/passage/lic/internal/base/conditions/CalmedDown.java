/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;

/**
 * <p>
 * Collecting conditions from set of licenses, dedicated for different parties
 * (product or its libraries), is accompanied by multiple <i>cannot read this
 * license</i> denials, as practically every license is tried against every
 * party.
 * </p>
 * <p>
 * These denials are to be treated as <i>bearable</i> as do not contain signs of
 * failure.
 * </p>
 */
final class CalmedDown implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final ServiceInvocationResult<Collection<ConditionPack>> original;

	CalmedDown(ServiceInvocationResult<Collection<ConditionPack>> original) {
		this.original = original;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> get() {
		TroubleCode morsel = new ServiceFailedOnMorsel();
		List<Trouble> calmed = original.diagnostic().severe().stream()//
				.filter(trouble -> trouble.code().equals(morsel))//
				.collect(Collectors.toList());
		return new BaseServiceInvocationResult<>(//
				new BaseDiagnostic(severe(calmed), bearable(calmed)), //
				original.data());
	}

	private List<Trouble> severe(List<Trouble> calmed) {
		List<Trouble> severe = new ArrayList<>(original.diagnostic().severe());
		severe.removeAll(calmed);
		return severe;
	}

	private List<Trouble> bearable(List<Trouble> calmed) {
		List<Trouble> bearable = new ArrayList<>(original.diagnostic().bearable());
		bearable.addAll(calmed);
		return bearable;
	}

}
