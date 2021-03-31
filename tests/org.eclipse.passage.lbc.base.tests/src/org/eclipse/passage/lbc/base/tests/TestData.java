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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.MD5Hashes;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;

public final class TestData {

	private final LicensedProduct product = new BaseLicensedProduct("anti-human-magic.product", "0.2.1"); //$NON-NLS-1$ //$NON-NLS-2$
	private final String feature = "prince-to-frog"; //$NON-NLS-1$
	private final User albert = new User("Albert_Rose@garden.ga", "os.hwdisk=*777*"); //$NON-NLS-1$ //$NON-NLS-2$
	private final User elder = new User("elder@magic.com", "os=win"); //$NON-NLS-1$ //$NON-NLS-2$

	void assertGrantIsValid(LicenseGrant grant) {
		assertEquals("hardware", grant.getConditionType()); //$NON-NLS-1$
		assertEquals("os.hwdisk=*777*", grant.getConditionExpression()); //$NON-NLS-1$
		assertEquals("prince-to-frog", grant.getFeatureIdentifier()); //$NON-NLS-1$
		assertEquals("d2b83215-b65d-4031-a8c8-a10421d56260#0", grant.getIdentifier()); //$NON-NLS-1$
		assertEquals("0.1.0", grant.getMatchVersion()); //$NON-NLS-1$
		assertEquals("compatible", grant.getMatchRule()); //$NON-NLS-1$
		assertEquals(1, grant.getCapacity());
		assertEquals(expectedFrom(), grant.getValidFrom());
		assertEquals(expectedUntil(), grant.getValidUntil());
	}

	private Date expectedFrom() {
		return Date.from(//
				ZonedDateTime.of(2020, Month.NOVEMBER.getValue(), 9, 0, 0, 0, 0, ZoneId.of("+0300")) //$NON-NLS-1$
						.toInstant());
	}

	private Date expectedUntil() {
		return new Date(expectedFrom().getTime() + 60 * 60 * 1000);
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

	@SuppressWarnings("restriction")
	public String hash() {
		return new MD5Hashes().id().toString();
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
