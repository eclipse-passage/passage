/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.base.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.junit.Test;

/**
 * {@linkplain LicensingResults} is going to be completely reworked from a
 * bundle of static function to a set of classes.
 * 
 */
public class LicensingResultsTest {
	@Test
	public void testBlankOk() {
		LicensingResult ok = createOk();
		assertBlankOkIsCorrect(ok);
		assertMessageIsBlank(ok);
		assertSourceIsNotBlank(ok);
	}

	@Test
	public void testInformativeOk() {
		String message = "informative"; //$NON-NLS-1$
		LicensingResult ok = createOk(message);
		assertBlankOkIsCorrect(ok);
		assertInformativeOkIsCorrect(ok, message);
		assertSourceIsNotBlank(ok);
	}

	@Test
	public void testSourcedOk() {
		String message = "highly informative"; //$NON-NLS-1$
		String source = "the blue"; //$NON-NLS-1$
		LicensingResult ok = createOk(message, source);
		assertBlankOkIsCorrect(ok);
		assertInformativeOkIsCorrect(ok, message);
		assertSourcedIsCorrect(ok, source);
	}

	private LicensingResult createOk() {
		return LicensingResults.createOK();
	}

	private LicensingResult createOk(String message) {
		return LicensingResults.createOK(message);
	}

	private LicensingResult createOk(String message, String source) {
		return LicensingResults.createOK(message, source);
	}

	private void assertBlankOkIsCorrect(LicensingResult ok) {
		assertNotNull("Creation failed", ok); //$NON-NLS-1$
		assertTrue("Creation is incorrect: OK expected", ok.getSeverity() == LicensingResult.OK); //$NON-NLS-1$
	}

	private void assertInformativeOkIsCorrect(LicensingResult ok, String message) {
		assertTrue("Creation is incorrect: unexpected message", message.equals(ok.getMessage())); //$NON-NLS-1$
	}

	private void assertSourcedIsCorrect(LicensingResult ok, String source) {
		assertTrue("Creation is incorrect: unexpected source", source.equals(ok.getSource())); //$NON-NLS-1$
	}

	private void assertMessageIsBlank(LicensingResult result) {
		assertTrue("Creation is incorrect: unexpected message", result.getMessage().length() == 0); //$NON-NLS-1$
	}

	private void assertSourceIsNotBlank(LicensingResult result) {
		assertNotNull("Creation is incorrect: source is null", result.getSource()); //$NON-NLS-1$
		assertTrue("Creation is incorrect: source is blank", result.getSource().length() != 0); //$NON-NLS-1$
	}

}
