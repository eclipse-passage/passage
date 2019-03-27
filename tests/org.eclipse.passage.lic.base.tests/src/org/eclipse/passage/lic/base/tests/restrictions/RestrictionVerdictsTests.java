/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base.tests.restrictions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.base.restrictions.BaseRestrictionVerdict;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;
import org.junit.Test;

public class RestrictionVerdictsTests {

	@Test
	public void testResolveLastVerdictsNegative() {
		assertNull(RestrictionVerdicts.resolveLastVerdict(null));
		assertNull(RestrictionVerdicts.resolveLastVerdict(new ArrayList<>()));
	}

	@Test
	public void testResolveLastVerdictsPositive() {
		BaseRestrictionVerdict error1 = RestrictionVerdicts.createConfigurationError(null, null);
		BaseRestrictionVerdict error2 = RestrictionVerdicts.createConfigurationError(null, null);
		List<RestrictionVerdict> verdicts = new ArrayList<RestrictionVerdict>();
		verdicts.add(error1);
		verdicts.add(error2);
		assertEquals(error2, RestrictionVerdicts.resolveLastVerdict(verdicts));
	}

}
