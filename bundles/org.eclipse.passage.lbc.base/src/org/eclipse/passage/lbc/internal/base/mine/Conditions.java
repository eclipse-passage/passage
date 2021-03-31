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
package org.eclipse.passage.lbc.internal.base.mine;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lbc.internal.base.EncodedResponse;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.internal.api.PassageAction;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

public final class Conditions implements Supplier<NetResponse> {

	private final ProductUserRequest<RawRequest> data;
	private final Logger log = LogManager.getLogger(getClass());

	public Conditions(ProductUserRequest<RawRequest> data) {
		this.data = data;
	}

	@Override
	public NetResponse get() {
		log.debug(String.format("Mining conditions for product %s", data.product().get())); //$NON-NLS-1$
		ServiceInvocationResult<Collection<ConditionPack>> conditions = //
				new FloatingConditions(data.raw().state()::source, data.user().get())//
						.all(data.product().get());
		if (!conditions.data().isPresent()) {
			return new Failure.OperationFailed(//
					new PassageAction.Mine().name(), //
					new DiagnosticExplained(conditions.diagnostic()).get());
		}
		if (!new NoErrors().test(conditions.diagnostic())) {
			log.error(new DiagnosticExplained(conditions.diagnostic()).get());
		}
		return encodedPack(pack(conditions.data().get()));
	}

	private NetResponse encodedPack(LicensePack pack) {
		return new EncodedResponse<EObject>(pack, data).get();
	}

	private LicensePack pack(Collection<ConditionPack> conditions) {
		return new PersonalLicenseGenerated(//
				data.product().get(), //
				data.user().get(), //
				conditions.stream()//
						.flatMap(pack -> pack.conditions().stream())//
						.collect(Collectors.toList())//
		).get();
	}

}
