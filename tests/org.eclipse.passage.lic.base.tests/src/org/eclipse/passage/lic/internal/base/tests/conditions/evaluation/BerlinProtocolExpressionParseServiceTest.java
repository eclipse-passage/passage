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
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingService;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;
import org.eclipse.passage.lic.api.tests.conditions.evaluation.ExpressionParsingServiceContractTest;
import org.eclipse.passage.lic.base.conditions.evaluation.BerlinProtocolExpressionParseService;
import org.eclipse.passage.lic.base.conditions.evaluation.SimpleMapExpression;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BerlinProtocolExpressionParseServiceTest extends ExpressionParsingServiceContractTest {

	@Test
	public void parsesValidData() {
		try {
			parser().parsed("a=b"); //$NON-NLS-1$
		} catch (ExpressionParsingException e) {
			fail("Is not expected to fial on valid data"); //$NON-NLS-1$
		}
	}

	@Test
	public void parsingIsAccurate() throws ExpressionParsingException {
		SimpleMapExpression parsed = (SimpleMapExpression) parser().parsed("k1=v1;k2=*;"); //$NON-NLS-1$
		assertEquals("v1", parsed.expected("k1")); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals("*", parsed.expected("k2")); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test(expected = ExpressionParsingException.class)
	public void evenSingleCorruptedSegmentFailsParsing() throws ExpressionParsingException {
		parser().parsed("k1=v1;k2;k3=v3"); //$NON-NLS-1$
	}

	@Test(expected = ExpressionParsingException.class)
	public void valueOnlySegmentIsCorrupted() throws ExpressionParsingException {
		parser().parsed("k1=v1;=v2;k3=v3"); //$NON-NLS-1$
	}

	@Test(expected = ExpressionParsingException.class)
	public void mediatorOnlySegmentIsCorrupted() throws ExpressionParsingException {
		parser().parsed("k1=v1;=;k3=v3"); //$NON-NLS-1$
	}

	@Test
	public void allowsWhiteSpaces() throws ExpressionParsingException {
		SimpleMapExpression parsed = (SimpleMapExpression) parser().parsed("k 1=v 1"); //$NON-NLS-1$
		assertEquals("v 1", parsed.expected("k 1")); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void keysAndValuesAreTrimmed() throws ExpressionParsingException {
		SimpleMapExpression parsed = (SimpleMapExpression) parser().parsed(" k\n= v\t"); //$NON-NLS-1$
		assertEquals("v", parsed.expected("k")); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Override
	protected ExpressionParsingService parser() {
		return new BerlinProtocolExpressionParseService();
	}

	@Override
	protected String validExpression() {
		return "a=b;c=*;d=1 2"; //$NON-NLS-1$
	}

	@Override
	protected Class<? extends ParsedExpression> resultType() {
		return SimpleMapExpression.class;
	}

}
