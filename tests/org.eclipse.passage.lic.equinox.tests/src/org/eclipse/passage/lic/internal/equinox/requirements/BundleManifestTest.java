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
package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public final class BundleManifestTest {

	@Test
	public void readManifest() {
		try {
			assertFalse(new BundleManifest(bundle()).get().isEmpty());
		} catch (LicensingException e) {
			fail("Manifest reading failed"); //$NON-NLS-1$
		}
	}

	private Bundle bundle() {
		return FrameworkUtil.getBundle(getClass());
	}

}
