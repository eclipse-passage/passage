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
package org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;

public final class FakeParsedExpression implements ParsedExpression {

	private final ExpressionProtocol protocol;

	public FakeParsedExpression(ExpressionProtocol protocol) {
		this.protocol = protocol;
	}

	public FakeParsedExpression(String protocol) {
		this(new ExpressionProtocol.Of(protocol));
	}

	public FakeParsedExpression() {
		this(new ExpressionProtocol.Default());
	}

	@Override
	public ExpressionProtocol protocol() {
		return protocol;
	}

}
