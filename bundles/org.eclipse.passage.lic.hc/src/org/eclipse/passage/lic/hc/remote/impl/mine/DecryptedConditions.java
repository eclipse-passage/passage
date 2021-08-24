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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport.Data;
import org.eclipse.passage.lic.base.conditions.BaseConditionOrigin;
import org.eclipse.passage.lic.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.hc.remote.RequestContext;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.hc.remote.impl.ResultsTransfered;
import org.eclipse.passage.lic.internal.hc.i18n.MineMessages;
import org.eclipse.passage.lic.internal.net.io.SafePayload;
import org.eclipse.passage.lic.licenses.model.api.FloatingServerConnection;

final class DecryptedConditions implements ResponseHandler<Collection<ConditionPack>> {

	private final FloatingServerConnection coordinates;
	private final ConditionMiningTarget target;
	private final Equipment equipment;

	DecryptedConditions(Equipment equipment, FloatingServerConnection coordinates, ConditionMiningTarget target) {
		this.equipment = equipment;
		this.coordinates = coordinates;
		this.target = target;
	}

	/**
	 * FIXME: we treat all remote conditions to be of the same origin until
	 * {@linkplain ConditionTransport} evolves to support condition packs #568621
	 */
	@Override
	public Collection<ConditionPack> read(ResultsTransfered results, RequestContext context) throws LicensingException {
		byte[] safe = safeData(results, context);
		try (ByteArrayInputStream stream = new ByteArrayInputStream(safe)) {
			Data data = equipment.transport(results.contentType()).read(stream);
			return Collections.singleton(//
					new BaseConditionPack(//
							new BaseConditionOrigin(target, source(), data.signature()), //
							data.conditions(), //
							data.agreements()//
					));
		} catch (IOException e) {
			throw new LicensingException(MineMessages.DecryptedConditions_reading_error, e);
		}
	}

	private byte[] safeData(ResultsTransfered results, RequestContext context) throws LicensingException {
		return new SafePayload(//
				equipment.keeper(context.product()), //
				equipment.hash(context.hash())//
		).decode(results.data());
	}

	private String source() {
		return String.format("%s:%d", coordinates.getIp(), coordinates.getPort());//$NON-NLS-1$
	}

}
