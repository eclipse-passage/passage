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
package org.eclipse.passage.lbc.internal.base.tobemoved.mine;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.base.tobemoved.EObjectTransfer;
import org.eclipse.passage.lbc.internal.base.tobemoved.Failure;
import org.eclipse.passage.lic.floating.FloatingFileExtensions;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.base.conditions.mining.UserHomeResidentConditions;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

public final class Conditions implements Supplier<FloatingResponse> {

	private final LicensedProduct product;
	private final String user;

	public Conditions(LicensedProduct product, String user) {
		this.product = product;
		this.user = user;
	}

	@Override
	public FloatingResponse get() {
		ServiceInvocationResult<Collection<ConditionPack>> conditions = //
				new UserHomeResidentConditions(//
						new ReassemblingMiningEquipment(user), //
						new FloatingFileExtensions.FloatingLicenseAccessEncrypted()//
				).all(product);
		if (!conditions.data().isPresent()) {
			return new Failure.OperationFailed(//
					"mine", //$NON-NLS-1$
					new DiagnosticExplained(conditions.diagnostic()).get());
		}
		return new EObjectTransfer(pack(conditions.data().get()));
	}

	private LicensePack pack(Collection<ConditionPack> conditions) {
		return new PersonalLicenseGenerated(//
				product, //
				user, //
				conditions.stream()//
						.flatMap(pack -> pack.conditions().stream())//
						.collect(Collectors.toList())//
		).get();
	}

}