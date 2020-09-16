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

import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionParsingService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ParsedExpression;

/**
 * 
 */
@SuppressWarnings("restriction")
public final class MunichProtocolExpressionParseService implements ExpressionParsingService {

	private final ExpressionProtocol protocol = new ExpressionProtocol.Munich();

	@Override
	public ExpressionProtocol id() {
		return protocol;
	}

	/**
	 * Expect the incoming {@code expression} to be a pipe-separated strings, which
	 * are also expressions under other protocols. Meaning {@code OR}-ed results.
	 */
	@Override
	public ParsedExpression parsed(String expression) throws ExpressionParsingException {
		return new MunichExpression();
	}

}
