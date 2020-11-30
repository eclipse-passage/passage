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

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationExpression;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationType;

final class ServerAuthenticationInstructions implements Supplier<Optional<EvaluationInstructions>> {

	private final RawRequest data;

	ServerAuthenticationInstructions(RawRequest data) {
		this.data = data;
	}

	@Override
	public Optional<EvaluationInstructions> get() {
		Optional<String> type = new ServerAuthenticationType(data::parameter).get();
		Optional<String> expression = new ServerAuthenticationExpression(data::parameter).get();
		if (!type.isPresent() || !expression.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(new BaseEvaluationInstructions(//
				new EvaluationType.Of(type.get()), //
				expression.get()));
	}

}