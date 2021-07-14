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
package org.eclipse.passage.lic.internal.base.tests.version;

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.lic.base.version.DefaultVersion;
import org.eclipse.passage.lic.base.version.SafeVersion;
import org.junit.Test;

// reflects requirements of LicensingVersion::testToVersionValue
public final class SafeVersionTest {

	@Test
	public void unproperSourceBecomesDefaultVersion() {
		assertEquals(new DefaultVersion(), new SafeVersion(this));
	}

	@Test
	public void unparsableSourceBecomesDefaultVersion() {
		assertEquals(new DefaultVersion(), new SafeVersion(this.toString())); // $NON-NLS-1$
	}

	@Test
	public void emptySourceBecomesDefaultVersion() {
		assertEquals(new DefaultVersion(), new SafeVersion("")); //$NON-NLS-1$
	}

	@Test
	public void lesserMissingSegmentsBecomeZeros() {
		assertEquals("1.0.0", new SafeVersion("1").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void lastMissingSegmentBecomesZero() {
		assertEquals("1.1.0", new SafeVersion("1.1").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void lesserUnparsableSegmentsBecomeZeros() {
		assertEquals("1.0.0", new SafeVersion("1.x.y").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void lastUnparsableSegmentBecomesZero() {
		assertEquals("1.1.0", new SafeVersion("1.1.x").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void validVerionBecomesItself() {
		assertEquals("1.2.3", new SafeVersion("1.2.3").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void qualifierCanTakeAnyForm() {
		assertEquals("1.2.3.hot-fix", new SafeVersion("1.2.3.hot-fix").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void eachSegmentIsTrimmed() {
		assertEquals("1.2.3.hot\tfix", new SafeVersion("\t1 . 2\t. 3 .hot\tfix\t").value()); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void defaultVersionCannotHaveQualifier() {
		assertEquals(new DefaultVersion(), new SafeVersion("0.0.0.no-way")); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() {
		new SafeVersion(null);
	}

}
