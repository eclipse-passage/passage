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
package org.eclipse.passage.lic.internal.base.tests.version.contract;

import org.eclipse.passage.lic.api.tests.version.VersionContractTest;
import org.eclipse.passage.lic.api.version.Version;
import org.eclipse.passage.lic.base.version.SafeVersion;

@SuppressWarnings("restriction")
public final class SafeVersionContractTest extends VersionContractTest {

	@Override
	protected Version fromString(String source) {
		return new SafeVersion(source);
	}

}
