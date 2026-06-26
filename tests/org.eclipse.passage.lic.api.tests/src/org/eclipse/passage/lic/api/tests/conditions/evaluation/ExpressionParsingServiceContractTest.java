/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.api.tests.conditions.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingService;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;
import org.junit.jupiter.api.Test;

public abstract class ExpressionParsingServiceContractTest {

	@Test
	public final void blankExpressionCausesFailure() throws ExpressionParsingException {
		assertThrows(ExpressionParsingException.class, () -> parser().parsed("\t")); //$NON-NLS-1$
	}

	@Test
	public final void prohibitsNullExpression() throws ExpressionParsingException {
		assertThrows(NullPointerException.class, () -> parser().parsed(null));
	}

	@Test
	public final void producesSameProtocolResult() {
		ExpressionParsingService parser = parser();
		try {
			assertEquals(parser.id(), parser.parsed(validExpression()).protocol());
		} catch (ExpressionParsingException e) {
			fail("Is not expected to fail on valid data"); //$NON-NLS-1$
		}
	}

	@Test
	public final void producesResultOfExpectedType() throws ExpressionParsingException {
		assertTrue(resultType().isInstance(parser().parsed(validExpression())));
	}

	protected abstract ExpressionParsingService parser();

	protected abstract String validExpression();

	protected abstract Class<? extends ParsedExpression> resultType();
}
