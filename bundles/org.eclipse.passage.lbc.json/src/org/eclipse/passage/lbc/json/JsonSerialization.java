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
package org.eclipse.passage.lbc.json;

import org.eclipse.passage.lbc.internal.api.persistence.Serialization;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

import com.fasterxml.jackson.core.JsonProcessingException;

@SuppressWarnings("restriction")
public final class JsonSerialization<T> implements Serialization<T> {

	@Override
	public ServiceInvocationResult<String> apply(ServiceInvocationResult<T> result) {
		try {
			return new BaseServiceInvocationResult<>(
					new LbcJsonObjectMapper().get().writeValueAsString(result.data().get()));
		} catch (JsonProcessingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new SerializationFailure(), e.getMessage()));
		}
	}

}
