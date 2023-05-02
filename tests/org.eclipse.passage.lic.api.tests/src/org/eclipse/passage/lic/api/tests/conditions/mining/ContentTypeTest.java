/*******************************************************************************
 * Copyright (c) 2020, 2023 ArSysOp
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
package org.eclipse.passage.lic.api.tests.conditions.mining;

import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.api.tests.registry.ServiceIdContractTest;
import org.junit.Test;

public final class ContentTypeTest extends ServiceIdContractTest {

	@Test(expected = NullPointerException.class)
	public void typeIsMandatory() {
		new ContentType.Of(null);
	}

	@Override
	protected ServiceId ofSameData() {
		return new ContentType.Of("same-id-value"); //$NON-NLS-1$
	}

}
