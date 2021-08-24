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
package org.eclipse.passage.seal.demo.tests.access;

import java.nio.file.Path;

import org.eclipse.passage.lic.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.BaseFramework;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.execute.DirectedAccessCycleConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

final class TestFramework extends BaseFramework {

	@Override
	protected LicensedProduct productRead() {
		return new BaseLicensedProduct("anti-human-magic.product", "0.2.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private Bundle bundle() {
		return FrameworkUtil.getBundle(TestFramework.class);
	}

	private Path source() {
		return new TestLicFolder().get();
	}

	@Override
	public AccessCycleConfiguration accessCycleConfiguration() {
		return new DirectedAccessCycleConfiguration(this::product, source(), this::bundle);
	}

}
