/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import java.nio.file.Paths;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.base.tests.TestData;
import org.eclipse.passage.lic.hc.remote.RequestContext;
import org.eclipse.passage.lic.hc.remote.impl.BaseRequestContext;

class TestRequestContext implements Supplier<RequestContext> {

	private final TestData data;

	TestRequestContext(TestData data) {
		this.data = data;
	}

	@Override
	public RequestContext get() {
		return new BaseRequestContext(data.product(), existingHash());//
	}

	private String existingHash() {
		return new TestEquipment(data.product(), () -> Paths.get("any")) //$NON-NLS-1$
				.get().hashes().get().services().iterator().next().id().toString();
	}

}
