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
package org.eclipse.passage.lbc.internal.base;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;

/**
 * @since 1.0
 */
public final class BaseLicensingRequest<R> implements BackendLicensingRequest {

	private final R request;
	private final BiFunction<R, String, String> parameter;
	private final Function<R, String> body;

	public BaseLicensingRequest(R request, BiFunction<R, String, String> parameter, Function<R, String> body) {
		this.request = request;
		this.parameter = parameter;
		this.body = body;
	}

	@Override
	public ContentType contentType() {
		return new ContentType.Of(parameter("licensing.content.type")); //$NON-NLS-1$
	}

	@Override
	public String body() {
		return body.apply(request);
	}

	@Override
	public String parameter(String key) {
		return parameter.apply(request, key);
	}

	@Override
	public Requester requester() {
		return new BaseRequester(parameter("process"), parameter("hardware"), parameter("feature")); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
	}

}
