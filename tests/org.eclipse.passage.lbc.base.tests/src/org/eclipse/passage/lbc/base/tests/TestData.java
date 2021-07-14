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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;

public final class TestData {

	private final LicensedProduct product = new BaseLicensedProduct("anti-human-magic.product", "0.2.1"); //$NON-NLS-1$ //$NON-NLS-2$
	private final String feature = "anti-human-magic.product"; //$NON-NLS-1$
	private final User albert = new User("Albert_Rose@garden.ga", "os.family=*"); //$NON-NLS-1$ //$NON-NLS-2$
	private final User elder = new User("elder@magic.com", "os=win"); //$NON-NLS-1$ //$NON-NLS-2$

	void assertGrantIsValid(PersonalFeatureGrant grant) {
		assertEquals("hardware", grant.getUserAuthentication().getType()); //$NON-NLS-1$
		assertEquals("os.family=*", grant.getUserAuthentication().getExpression()); //$NON-NLS-1$
		assertEquals("anti-human-magic.product", grant.getFeature().getIdentifier()); //$NON-NLS-1$
		assertEquals("d05f5266-9340-48a5-8107-a9727a76d8ba#1", grant.getIdentifier()); //$NON-NLS-1$
		assertEquals("0.2.1", grant.getFeature().getVersionMatch().getVersion()); //$NON-NLS-1$
		assertEquals("compatible", grant.getFeature().getVersionMatch().getRule()); //$NON-NLS-1$
		assertEquals(1, grant.getCapacity());
		assertTrue(grant.getValid() instanceof ValidityPeriodClosed);
		assertEquals(expectedFrom(), ((ValidityPeriodClosed) grant.getValid()).getFrom());
		assertEquals(expectedUntil(), ((ValidityPeriodClosed) grant.getValid()).getUntil());
	}

	private Date expectedFrom() {
		return Date.from(//
				ZonedDateTime.of(2021, Month.APRIL.getValue(), 8, 0, 0, 0, 0, ZoneId.of("+0300")) //$NON-NLS-1$
						.toInstant());
	}

	private Date expectedUntil() {
		return Date.from(//
				ZonedDateTime.of(2021, Month.MAY.getValue(), 8, 0, 0, 0, 0, ZoneId.of("+0300")) //$NON-NLS-1$
						.toInstant());
	}

	public LicensedProduct product() {
		return product;
	}

	public String feature() {
		return feature;
	}

	public User albert() {
		return albert;
	}

	public User elder() {
		return elder;
	}

	final static class User {

		private final String id;
		private final String expression;

		private User(String id, String expression) {
			this.id = id;
			this.expression = expression;
		}

		public String id() {
			return id;
		}

		public String expression() {
			return expression;
		}

	}

}
