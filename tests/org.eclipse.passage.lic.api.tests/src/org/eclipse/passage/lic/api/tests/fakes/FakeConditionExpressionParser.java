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
package org.eclipse.passage.lic.api.tests.fakes;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionPasringService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ParsedExpression;

@SuppressWarnings("restriction")
public final class FakeConditionExpressionParser implements ExpressionPasringService {

	@Override
	public ExpressionProtocol id() {
		return new ExpressionProtocol.Of("intruder"); //$NON-NLS-1$
	}

	@Override
	public ParsedExpression parsed(String expression) throws ExpressionParsingException {
		throw new UnsupportedOperationException();
	}

}
