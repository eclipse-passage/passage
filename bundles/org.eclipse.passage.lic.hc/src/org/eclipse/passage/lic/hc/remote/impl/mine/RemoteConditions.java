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
package org.eclipse.passage.lic.hc.remote.impl.mine;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.hc.remote.impl.HttpClient;
import org.eclipse.passage.lic.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.hc.remote.impl.RemoteServiceData.Bulk;
import org.eclipse.passage.lic.hc.remote.impl.ServiceEvery;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

/**
 * 
 * @since 1.1
 */
public final class RemoteConditions<C extends Connection> //
		extends ServiceEvery<C, Collection<ConditionPack>, RemoteServiceData.Bulk> implements MinedConditions {

	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote();

	public RemoteConditions(Equipment equipment, Supplier<Client<C, Collection<ConditionPack>>> client,
			Supplier<Path> source) {
		super(equipment, client, source);
	}

	public RemoteConditions(Equipment equipment) {
		this(equipment, HttpClient::new, new LicensingFolder(new UserHomePath()));
	}

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product) {
		return request(new RemoteServiceData.Bulk(product));
	}

	@Override
	protected RemoteRequest<C> request(Bulk params, FloatingLicenseAccess access) {
		return new RemoteConditionsRequest<>(params.product(), access, equipment.hashes());
	}

	@Override
	protected ResponseHandler<Collection<ConditionPack>> handler(FloatingLicenseAccess access) {
		return new DecryptedConditions(equipment, access.getServer(), target);
	}

	@Override
	protected BinaryOperator<Collection<ConditionPack>> sum() {
		return new SumOfCollections<>();
	}

	@Override
	protected Collection<ConditionPack> noResult() {
		return Collections.emptyList();
	}

}
