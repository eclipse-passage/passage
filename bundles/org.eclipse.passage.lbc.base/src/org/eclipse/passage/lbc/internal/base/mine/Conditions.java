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
package org.eclipse.passage.lbc.internal.base.mine;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lbc.internal.base.EObjectTransfer;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lic.floating.FloatingFileExtensions;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.base.conditions.mining.LocalConditions;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

public final class Conditions implements Supplier<NetResponse> {

	private final ProductUserRequest data;
	private final Supplier<Path> source;
	private final Logger log = LogManager.getLogger(getClass());

	public Conditions(ProductUserRequest data, Supplier<Path> base) {
		this.data = data;
		this.source = base;
	}

	public Conditions(ProductUserRequest data) {
		this(data, new LicensingFolder(new UserHomePath()));
	}

	@Override
	public NetResponse get() {
		log.debug(String.format("Mining conditions for product %s", data.product().get())); //$NON-NLS-1$
		ServiceInvocationResult<Collection<ConditionPack>> conditions = //
				new Miner(source, data.user().get())//
						.all(data.product().get());
		if (!conditions.data().isPresent()) {
			return new Failure.OperationFailed(//
					new ConditionAction.Mine().name(), //
					new DiagnosticExplained(conditions.diagnostic()).get());
		}
		if (!new NoErrors().test(conditions.diagnostic())) {
			log.error(new DiagnosticExplained(conditions.diagnostic()).get());
		}
		return new EObjectTransfer(pack(conditions.data().get()));
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

	private final class Miner extends LocalConditions {

		private final Supplier<Path> base;

		Miner(Supplier<Path> base, String user) {
			super(//
					new ConditionMiningTarget.Local().child("floating-server"), //$NON-NLS-1$
					new ReassemblingMiningEquipment(user, base), //
					new FloatingFileExtensions.FloatingLicenseEncrypted());
			this.base = base;
		}

		@Override
		protected Supplier<Path> base(LicensedProduct product) {
			return new PathFromLicensedProduct(base, product);
		}

	}

}
