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
package org.eclipse.passage.lic.internal.base.conditions.evaluation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionPasringService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ParsedExpression;

/**
 * 
 */
@SuppressWarnings("restriction")
public final class AndsProtocolExpressionParseService implements ExpressionPasringService {

	private final ExpressionProtocol protocol = new ExpressionProtocol.Ands();

	@Override
	public ExpressionProtocol id() {
		return protocol;
	}

	/**
	 * Expect the incoming {@code expression} to be a semicolon-separated
	 * {@code key=value} pairs meaning {@code AND}-ed equality checks.
	 * <p>
	 * FIXME: contract: <no checks> situation must cause failure - cover it with a
	 * contract test
	 * </p>
	 */
	@Override
	public ParsedExpression parsed(String expression) throws ExpressionParsingException {
		Objects.requireNonNull(expression);
		Map<String, String> checks = new HashMap<>();
		// FIXME: ytbd: do further work: split and fill the map
		return new SimpleMapExpression(protocol, checks);
	}

}
