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
package org.eclipse.passage.lic.api.tests.fakes.requirements;

import org.eclipse.passage.lic.api.requirements.Feature;

public final class FakeFeature implements Feature {

	private final String identity;

	public FakeFeature(String identity) {
		this.identity = identity;
	}

	public FakeFeature() {
		this(Long.toHexString(System.currentTimeMillis()));
	}

	@Override
	public String name() {
		return String.format("Fake feature %s ", identity); //$NON-NLS-1$
	}

	@Override
	public String version() {
		return "0.0.7"; //$NON-NLS-1$
	}

	@Override
	public String identifier() {
		return String.format("fake_%s ", identity); //$NON-NLS-1$
	}

	@Override
	public String provider() {
		return "API tests"; //$NON-NLS-1$
	}

}
